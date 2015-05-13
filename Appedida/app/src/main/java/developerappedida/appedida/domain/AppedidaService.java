package developerappedida.appedida.domain;


import android.content.Context;
import android.util.Log;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.livroandroid.db.Session;
import developerappedida.appedida.AppedidaAplication;
import developerappedida.appedida.R;
import developerappedida.appedida.activity.BaseActivity;
import developerappedida.appedida.util.HttpHelper;

public class AppedidaService extends BaseActivity {

    private static final String TAG = AppedidaService.class.getSimpleName();
    private static List<Pedido> listaPedidos = new ArrayList<Pedido>();

    public static final String URL_SERVER = "http://appedida.com.br";

    public static void getListaDeProdutos(List<Pedido> listaPedidos) {
        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile"};


//        listaPedidos.add();

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
    }

    public static List<Pedido> getListaDeProdutos(Context context) {

        Pedido pedido_um = new Pedido(context.getString(R.string.batata_com_presunto), context.getString(R.string.reais) + " " + context.getString(R.string.dez_reais), false);
        Pedido pedido_dois = new Pedido(context.getString(R.string.batata_com_queijo), context.getString(R.string.reais) + " " + context.getString(R.string.dez_reais), false);
        Pedido pedido_tres = new Pedido(context.getString(R.string.batata_com_oregano), context.getString(R.string.reais) + " " + context.getString(R.string.dez_reais), false);
        Pedido pedido_quatro = new Pedido(context.getString(R.string.batata_com_batata_palha), context.getString(R.string.reais) + " " + context.getString(R.string.dez_reais), false);
        Pedido pedido_cinco = new Pedido(context.getString(R.string.batata_com_strogonoff), context.getString(R.string.reais) + " " + context.getString(R.string.dez_reais), false);
        Pedido pedido_seis = new Pedido(context.getString(R.string.batata_com_rucula), context.getString(R.string.reais) + " " + context.getString(R.string.dez_reais), false);


        listaPedidos.add(pedido_um);
        listaPedidos.add(pedido_dois);
        listaPedidos.add(pedido_tres);
        listaPedidos.add(pedido_quatro);
        listaPedidos.add(pedido_cinco);
        listaPedidos.add(pedido_seis);

        return listaPedidos;
    }


    public static Usuario getUser(Context context) {
        Session session = AppedidaAplication.getInstance().getSession(context);

        try {
            List<Usuario> list = session.findAll(Usuario.class);
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public static boolean saveUser(Context context, Usuario user) {
        Session session = AppedidaAplication.getInstance().getSession(context);

        try {
            List<Usuario> list = session.findAll(Usuario.class);
            if (list.size() == 0) {
                session.saveOrUpdate(user);
                Log.i(TAG, context.getString(R.string.usuario_salvo));
                return true;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        Log.e(TAG, context.getString(R.string.erro_ao_salvar_usuario));
        return false;
    }

    public static void getAllProdutos() throws IOException {

        HttpHelper http = getHttpHelper();

        Map<String, String> params = getHttpParams();
        http.doPost(URL_SERVER + "/appedidaWS/WS/Appedida.asmx/GetAllProduto", params);
        String json = http.getString();
        Log.i(TAG, "info: " + json);

    }

    private static Map<String, String> getHttpParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("form_name", "form");
        params.put("mode", "json");
        return params;
    }

    private static HttpHelper getHttpHelper() {
        HttpHelper http = new HttpHelper();
        http.addHeader("Content-Type", "application/x-www-form-urlencoded");
        return http;
    }

    public static boolean CreateUsuario() throws IOException {
        HttpHelper http = getHttpHelper();

        Map<String, String> params = getHttpParams();

        Usuario usuario = AppedidaAplication.getInstance().getUser();

        try {
            params.put("nome", usuario.getLogin());
            params.put("email", usuario.getLogin());
            params.put("senha", usuario.getLogin());
            params.put("cpf", usuario.getLogin());
            params.put("IsAdmin", usuario.isAdmin());

            http.doPost(URL_SERVER + "/appedidaWS/WS/Appedida.asmx/CreateUsuario ", params);
            String json = http.getString();
            Log.i(TAG, "info: " + json);

            if(json.contains("true")){
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
