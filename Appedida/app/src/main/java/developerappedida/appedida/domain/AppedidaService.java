package developerappedida.appedida.domain;


import android.content.Context;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private static List<Produto> listaPedidos = new ArrayList<Produto>();

    public static final String URL_SERVER = "http://appedida.com.br";


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

    public static List<Produto> getAllProdutos() throws IOException, JSONException {

        listaPedidos = new ArrayList<Produto>();

        HttpHelper http = getHttpHelper();

        Map<String, String> params = getHttpParams();
        http.doPost(URL_SERVER + "/appedidaWS/WS/Appedida.asmx/GetAllProduto", params);
        String json = http.getString();
        Log.i(TAG, "info: " + json);

        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject j = jsonArray.getJSONObject(i);
            String idProduto = j.getString("id_Produto");
            String descricao = j.getString("descricao");
            String valor = j.getString("valor");
            String data_Cadastro = j.getString("data_Cadastro");
            String nome = j.getString("nome");
            String id_foto = j.getString("id_foto");


            Produto p = new Produto(idProduto, descricao, valor, data_Cadastro, nome, id_foto);
            listaPedidos.add(p);

        }

        return listaPedidos;

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

            if (json.contains("true")) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean CreatePedido(List<Produto> listaProdutoSelecionados, String precoTotal) throws IOException {
        HttpHelper http = getHttpHelper();

        String listaConcatenada = concatenarListaDeProdutosSelecionados(listaProdutoSelecionados);

        Map<String, String> params = getHttpParams();

        Usuario usuario = AppedidaAplication.getInstance().getUser();

        try {
            params.put("idsProdutos", listaConcatenada);
            params.put("valor_Pedido", precoTotal);
            params.put("idUsuario", "15");

            http.doPost(URL_SERVER + "/appedidaWS/WS/Appedida.asmx/CreatePedido ", params);
            String json = http.getString();
            Log.i(TAG, "info: " + json);

            if (json.contains("true")) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static String concatenarListaDeProdutosSelecionados(List<Produto> listaProdutoSelecionados) {

        String concatenar = "";

        for(Produto d: listaProdutoSelecionados){
            concatenar += d.getId_Produto() + " |";
        }

        return concatenar;
    }
}
