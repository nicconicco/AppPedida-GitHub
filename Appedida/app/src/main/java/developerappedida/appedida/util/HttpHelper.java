package developerappedida.appedida.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.livroandroid.network.Http;
import br.livroandroid.rms.HttpBytes;
import br.livroandroid.utils.AndroidUtils;
import br.livroandroid.utils.IOUtils;
import br.livroandroid.utils.StringUtils;

public class HttpHelper extends AndroidUtils {

	private static final String TAG = "Http";
	public static boolean LOG_ON = false;
	public static boolean LOG_RESPONSE_ON = false;
	public static int TIMEOUT_CONNECT = 30000;
	public static int TIMEOUT_READ = 30000;

	public static final String CHARSET_UTF = "UTF-8";
	public static final String CHARSET_ISO = "ISO-8859-1";
	public static boolean PRINT_HEADERS = false;
	public static String CHARSET_DEFAULT = CHARSET_UTF;

	private HttpURLConnection conn;
	private InputStream in;
	private DataInputStream dataIn;
	private static boolean logBytesOn = false;
	private Context context;
	
	private boolean encode = false;
	/**
	 * Se informar um userAgent vai sobrescrever o nativo
	 * Caso contrario se setar esta flag vai fazer append
	 */
	public boolean appendUserAgent = false;
	private String userAgent;
	private String charset;
	private String contentType;
	
	private Map<String,String> headers;
	
	public HttpHelper() {
	}
	public HttpHelper(Context context) {
		this.context = context;
	}

	public String getString() throws IOException {
		try {
			if(in == null) {
				return null;
			}
			String s = charset != null ? IOUtils.toString(in, charset) : IOUtils.toString(in);
			if(LOG_RESPONSE_ON){
				Log.d(TAG, "\t< [" + s + "]");
			}
			return s;
		} finally {
			close();
		}
	}
	
	public byte[] getBytes() throws IOException {
		try {
			byte[] bytes = IOUtils.toByteArray(in);
			return bytes;
		} finally {
			close();
		}
	}
	
	public Bitmap getBitmap() throws IOException {
		try {
			byte[] bytes = getBytes();
			if(bytes != null) {
				Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
				return bitmap;
			}
		} finally {
			close();
		}
		return null;
	}
	
	public InputStream getInputStream() {
		return in;
	}
	
	public DataInputStream getDataInputStream() {
		dataIn = new DataInputStream(in);
		return dataIn;
	}

	public void doPost(String url, Map<String,String> params) throws IOException {
		String queryString = Http.getQueryString(params, encode ? CHARSET_DEFAULT : null);
		doPost(url, queryString,CHARSET_DEFAULT);
	}

	public void doPost(String url, Map<String,String> params, String charset) throws IOException {
		String queryString = Http.getQueryString(params, encode ? charset : null);
		doPost(url, queryString,charset);
	}

	public void doPost(String url, String params, String charset) throws IOException {
		byte[] bytes = params != null ? params.getBytes(charset) : null;
		this.charset = charset;
		if(LOG_ON){
			Log.d(TAG, "Http.doPost: " + url + "?" + params);
		}
		doPost(url, bytes);
	}
	
	public void doPost(String url, byte[] params) throws IOException {

		conn = openConnection(url);

		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);

		conn.connect();

		if(params != null) {
			//conn.setFixedLengthStreamingMode(params.length);

			OutputStream out = conn.getOutputStream();
			if(logBytesOn && params != null && context != null) {
				HttpBytes.logOut(context, params.length);
			}
			out.write(params);
			out.flush();
			out.close();
		}

		int responseCode = conn.getResponseCode();
		if(responseCode != 200) {
			Log.e(TAG, "Error URL [" + url + "], ResponseCode [" + responseCode + "], " + conn.getResponseMessage());
			throw new IOException("ResponseCode ["+responseCode+"]: " + conn.getResponseMessage());
		}

		this.in = conn.getInputStream();

		int available = in.available();
		if(context != null) {
			HttpBytes.logIn(context, available);
		}

		printHeaders();
	}

	public static Bitmap doGetBitmap(Context context, String url) throws IOException {
		InputStream in = null;
		try {
			if(LOG_ON){
				Log.d(TAG, ">> Http.doGetBitmap " + url);
			}
			URL u = new URL(url);
			in = u.openStream();
			Bitmap img = BitmapFactory.decodeStream(in);
			if(LOG_ON){
				Log.d(TAG, "<< Http.doGetBitmap: " + (img != null ? "w/h " + img.getWidth() + "/" + img.getHeight() + "" : "img nula"));
			}
			return img;
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					Log.e(TAG, e.getMessage(), e);
				}
			}
		}
	}

	public void doGet(String url) throws IOException {
		doGet(url, null, CHARSET_DEFAULT);
	}

	public void doGet(String url, String charset) throws IOException {
		doGet(url, null, charset);
	}

	public void doGet(String url, Map<String,String> params) throws IOException {
		doGet(url, params, CHARSET_DEFAULT);
	}

	public void doGet(String url, Map<String,String> params, String charset) throws IOException {
		String queryString = Http.getQueryString(params, encode ? charset : null);

		this.charset = charset;

		if(StringUtils.isNotEmpty(queryString)){
			int lengthEnviado = queryString.getBytes().length;
			if(context != null) {
				HttpBytes.logOut(context, lengthEnviado);
			}

			url += "?" + queryString;
		}

		if(LOG_ON) {
			Log.d(TAG, "Http.doGet: " + url);
		}

		conn = openConnection(url);

		conn.setRequestMethod("GET");
		conn.setDoOutput(true);
		conn.setDoInput(true);

		int responseCode = conn.getResponseCode();
		if(responseCode != 200) {
			Log.e(TAG, "Error URL [" + url + "], ResponseCode [" + responseCode + "], " + conn.getResponseMessage());
			throw new IOException("ResponseCode ["+responseCode+"]: " + conn.getResponseMessage());
		}

		this.in = conn.getInputStream();

		int available = in.available();
		if(context != null) {
			HttpBytes.logIn(context, available);
		}

		printHeaders();
	}

	private void printHeaders() {
		if(PRINT_HEADERS) {
			Log.i("http", "--- printKeys ---");
			Map<String, List<String>> headers = conn.getHeaderFields();
			if(headers != null) {
				Set<String> keys = headers.keySet();
				for (String key : keys) {
					Log.i("http", key + ": " + conn.getHeaderField(key));
				}
			}
			Log.i("http", "--- printKeys ---");
		}
	}

	public HttpURLConnection openConnection(String url) throws IOException {
		URL u = new URL(url);
		HttpURLConnection c = (HttpURLConnection) u.openConnection();
		c.setReadTimeout(getReadTimeout());
		c.setConnectTimeout(getConnectTimeout());
		if(StringUtils.isNotEmpty(contentType)) {
			Log.d(TAG, "contentType: " + contentType);
			c.setRequestProperty("Content-type", contentType);
		}
		if(headers != null) {
			Set<String> keySet = headers.keySet();
			for (String key : keySet) {
				String value = headers.get(key);
				c.setRequestProperty(key, value );
				Log.d(TAG, "addHeader " + key + ":" + value);
			}
		}
		if(userAgent != null) {
			String defaultUserAgent = c.getRequestProperty("user-agent");
			if(appendUserAgent && defaultUserAgent != null) {
				c.setRequestProperty ("user-agent", defaultUserAgent + userAgent);
			} else {
				c.setRequestProperty ("user-agent", userAgent);
			}
		}
		return c;
	}

	public void close() {
		try {
			if(in != null) {
				IOUtils.closeQuietly(in);
				in = null;
			}
			if(dataIn != null) {
				IOUtils.closeQuietly(dataIn);
				dataIn = null;
			}
			if(conn != null) {
				conn.disconnect();

				conn = null;
			}
		} catch (Exception e) {
			Log.e(TAG, "HttpConnectionImpl conn.disconnect(): " + e.getMessage());
		}
	}

	public static void setLogBytesOn(boolean on) {
		logBytesOn = true;
	}

	public byte[] doGetBytes(String url, Map<String, String> params) throws IOException {
		doGet(url, params);
		byte[] bytes = IOUtils.toByteArray(in);
		return bytes;
	}

	public void setEncode(boolean encode) {
		this.encode = encode;
	}

	public int getConnectTimeout() {
		return TIMEOUT_CONNECT;
	}

	public int getReadTimeout() {
		return TIMEOUT_READ;
	}

	public void setConnectTimeout(int timeout) {
		TIMEOUT_CONNECT = timeout ;
	}

	public void setReadTimeout(int timeout) {
		TIMEOUT_READ = timeout;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public static void setCharset(String charset) {
		CHARSET_DEFAULT = charset;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * "Authorization","3d2e4e1e-5ae3-445a-9ea0-3fb2ff7c8fc2"
	 *
	 * "accept","application/json"
	 *
	 * "Content-type"
	 *
	 * @param key
	 * @param value
	 */
	public void addHeader(String key, String value) {
		if(headers == null) {
			headers = new HashMap<String, String>();
		}
		headers.put(key, value);
	}

	public boolean ping(String url) throws IOException {
		return ping(url, 5000, 5000, true, "UTF-8");
	}

	public boolean ping(String url, int timeoutConnect,int timeRead, boolean parseResponse, String charset) throws IOException {

		if(LOG_ON) {
			Log.d(TAG, "Http.ping: " + url);
		}

		HttpURLConnection conn = openConnection(url);
		conn.setReadTimeout(timeRead);
		conn.setConnectTimeout(timeoutConnect);

		conn.setRequestMethod("GET");
		conn.setDoOutput(false);
		conn.setDoInput(parseResponse);

		int responseCode = conn.getResponseCode();
		if(responseCode != 200) {
			Log.e(TAG, "Error URL [" + url + "], ResponseCode [" + responseCode + "], " + conn.getResponseMessage());
			throw new IOException("ResponseCode ["+responseCode+"]: " + conn.getResponseMessage());
		}

		if(parseResponse) {

			InputStream in = conn.getInputStream();

			String s = charset != null ? IOUtils.toString(in, charset) : IOUtils.toString(in);
			
			IOUtils.closeQuietly(in);
			
			if(LOG_ON) {
				Log.d(TAG, "Http.ping: " + url);
			}
			
			try {
				if(conn != null) {
					conn.disconnect();
					conn = null;
				}
			} catch (Exception e) {
			}
			
			if(StringUtils.isEmpty(s)) {
				return false;
			} else {
				return true;
			}
		}

		return true;
	}
}