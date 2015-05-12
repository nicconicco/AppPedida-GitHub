//
//import android.app.Activity;
//import android.content.Context;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//
//import com.squareup.picasso.Callback;
//import com.squareup.picasso.Picasso;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.net.SocketTimeoutException;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import br.com.livetouch.pedidofacilv2.PedidoFacilApplication;
//import br.com.livetouch.pedidofacilv2.R;
//import br.com.livetouch.pedidofacilv2.adapter.NavDrawerMenuItem;
//import br.com.livetouch.pedidofacilv2.model.Request;
//import br.com.livetouch.pedidofacilv2.vo.BoletoVO;
//import br.com.livetouch.pedidofacilv2.vo.CatalogoFiltro;
//import br.com.livetouch.pedidofacilv2.vo.ComboVO;
//import br.com.livetouch.pedidofacilv2.vo.EnderecoVO;
//import br.com.livetouch.pedidofacilv2.vo.FaixasPagamento;
//import br.com.livetouch.pedidofacilv2.vo.FormaPagamento;
//import br.com.livetouch.pedidofacilv2.vo.MenuVO;
//import br.com.livetouch.pedidofacilv2.vo.NotaVO;
//import br.com.livetouch.pedidofacilv2.vo.PedidoVO;
//import br.com.livetouch.pedidofacilv2.vo.ProdutoVO;
//import br.com.livetouch.pedidofacilv2.vo.TelefoneVO;
//import br.com.livetouch.pedidofacilv2.vo.UsuarioVO;
//import br.livroandroid.LivroAndroid;
//import br.livroandroid.exception.DomainException;
//import br.livroandroid.network.HttpClientImpl;
//import br.livroandroid.network.HttpHelper;
//import br.livroandroid.utils.FileUtils;
//import br.livroandroid.utils.StringUtils;
//
///**
// * Created by Felipe on 03/03/15.
// */
//public class PedidoFacilService {
//
//    private static boolean FAKE_ON = false;
//
////    private static String URL_SERVER = "http://192.168.25.146:8080/PedidoFacilV2";
//    private static String URL_SERVER = "http://livetouchdev.com.br:8080/PedidoFacilV2";
////    private static String URL_SERVER = "http://pedidofacilv2.livetouchdev.com.br";
//
//    public static List<MenuVO> getHomeMenu(Context context) {
//        List<MenuVO> retorno = new ArrayList<MenuVO>();
//        retorno.add(new MenuVO(context.getString(R.string.pedido), context.getString(R.string.fazer_pedido), context.getString(R.string.fazer_pedido_subtitulo)));
//        retorno.add(new MenuVO(context.getString(R.string.pedidos), context.getString(R.string.meus_pedidos), context.getString(R.string.meus_pedidos_subtitulo)));
//        retorno.add(new MenuVO(context.getString(R.string.promocao), context.getString(R.string.promocoes), context.getString(R.string.promocoes_subtitulo)));
//        retorno.add(new MenuVO(context.getString(R.string.dados), context.getString(R.string.meus_dados), context.getString(R.string.meus_dados_subtitulo)));
//        return retorno;
//    }
//
//    public static synchronized UsuarioVO login(Context context, String cnpj, String senha) throws Exception {
//        String json = null;
//        UsuarioVO usuario = new UsuarioVO();
//
//        if (FAKE_ON) {
//            json = FileUtils.getFileAssetsString(context, "login.json", "UTF-8");
//        } else {
//            HttpHelper http = getHttpHelper();
//            Map<String, String> params = getHttpParams(context);
//            try {
//                params.put("cnpj", cnpj);
//                params.put("senha", senha);
//                http.doPost(URL_SERVER + "/ws/login.htm", params);
//                json = http.getString();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (json != null && !StringUtils.contains("NOK", json)) {
//            JSONObject jObj = new JSONObject(json);
//            usuario.idUsuário = String.valueOf(jObj.getInt("id"));
//            usuario.formaPagamento = jObj.getInt("formaPagamento");
//            usuario.codPdv = jObj.getString("codPdv");
//            usuario.codEmpresa = jObj.getString("codEmpresa");
//            usuario.codFilial = jObj.getString("codFilial");
//            usuario.cnpj = jObj.getString("cnpj");
//            usuario.razaoSocial = jObj.getString("razaoSocial");
//            usuario.nomeFantasia = jObj.getString("nomeFantasia");
//            usuario.tipoPdv =jObj.getString("tipoPdv");
//            usuario.codCorporativo = jObj.getString("codCorporativo");
//            usuario.digitoCorporativo = jObj.getString("digitoCorporativo");
//            usuario.codUNBComercial = jObj.getString("codUNBComercial");
//            usuario.dataMinimaEntrega = jObj.getString("dataMinimaEntrega");
//            usuario.unb = jObj.getString("unb");
//            usuario.emailCobranca = jObj.getString("emailCobranca");
//            usuario.emailContato = jObj.getString("emailContato");
//            usuario.emailSite = jObj.getString("emailSite");
//
//            JSONObject jEnd = jObj.getJSONObject("endereco");
//            EnderecoVO endereco = new EnderecoVO();
//            endereco.bairro = jEnd.getString("bairro");
//            endereco.complemento = jEnd.getString("complemento");
//            endereco.endereco = jEnd.getString("endereco");
//            endereco.estado = jEnd.getString("estado");
//            endereco.latitude = jEnd.getString("latitude");
//            endereco.longitude = jEnd.getString("longitude");
//            endereco.numero = jEnd.getInt("numero");
//            endereco.cidade = jEnd.getString("cidade");
//            usuario.enderecoVO = endereco;
//
//            usuario.telefones = new ArrayList<>();
//            JSONArray jTelefones = jObj.getJSONArray("telefones");
//            for(int i = 0; i < jTelefones.length(); i++) {
//                JSONObject tel = jTelefones.getJSONObject(i);
//                TelefoneVO telefoneVO = new TelefoneVO();
//                telefoneVO.numero = tel.getString("numero");
//                usuario.telefones.add(telefoneVO);
//            }
//
//            usuario.diasVisita = jObj.getString("diasVisita");
//            usuario.nomeContato = jObj.getString("nomeContato");
//
//            JSONObject jPedido = jObj.getJSONObject("pedido");
//            PedidoVO pedidoVO = new PedidoVO();
//            pedidoVO.setIdPedido(jPedido.getInt("id"));
//            pedidoVO.setDataEntrega(jPedido.getString("dataEntrega"));
//            pedidoVO.setDataPedido(jPedido.getString("dataPedido"));
//            pedidoVO.setStatusPedido(jPedido.getString("statusPedido"));
//            usuario.pedido = pedidoVO;
//        }
//
//        PedidoFacilApplication.getInstance().setUsuario(usuario);
//
//        return usuario;
//    }
//
//    public static Request downloadRequest(Activity context, String idPedido) throws IOException, JSONException, DomainException {
//
//
//        try {
//            String json = "";
//            HttpHelper http = getHttpHelper();
//            Map<String, String> params = getHttpParams(context);
//            try {
//                params.put("pedido_id", idPedido);
//                http.doPost(URL_SERVER + "/ws/getPosicaoCaminhaoByPedido.htm", params);
//                json = http.getString();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            Request r = new Request();
//
//            JSONObject jObj = new JSONObject(json);
//
//            r.parse(jObj);
//
//            return r;
//        } catch (JSONException e) {
//            LivroAndroid.logError(e.getMessage(), e);
//            throw new DomainException(context.getString(R.string.nao_foi_possivel_obter_os_pontos_do_mapa));
//        }
//
//    }
//
//    private static String fixGarbage(String json) {
//        json = json.replace("\\\"", "\"");
//        if (json.startsWith("\"")) {
//            json = json.substring(1, json.length() - 1);//remove first and last character '"'
//        }
//        return json;
//    }
//
//    private static HttpHelper getHttpHelper() {
//        return getHttpHelper(15000);
//    }
//
//    private static HttpHelper getHttpHelper(int timeout) {
//        HttpHelper http = new HttpHelper();
//        http.setConnectTimeout(timeout);
//        http.setReadTimeout(timeout);
//        return http;
//    }
//
//    private static Map<String, String> getHttpParams(Context context) {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("form_name", "form");
//        params.put("mode", "json");
//        return params;
//    }
//
//
//    public static boolean recuperarSenha(Context context, String login) throws Exception {
//        String json;
//
//        if (FAKE_ON) {
//            json = FileUtils.getFileAssetsString(context, "login.json", "UTF-8");
//        } else {
//            HttpHelper http = getHttpHelper();
//            Map<String, String> params = getHttpParams(context);
//            params.put("cnpj", login);
//            http.doPost(URL_SERVER + "/ws/recuperarSenha.htm", params);
//            json = http.getString();
//        }
//
//        return true;
//    }
//
//    public static List<NavDrawerMenuItem> getList() {
//        List<NavDrawerMenuItem> list = new ArrayList<NavDrawerMenuItem>();
//        list.add(new NavDrawerMenuItem(R.string.inicio));
//        list.add(new NavDrawerMenuItem(R.string.fazer_pedido));
//        list.add(new NavDrawerMenuItem(R.string.meus_pedidos));
//        list.add(new NavDrawerMenuItem(R.string.meus_dados));
//        list.add(new NavDrawerMenuItem(R.string.emporio_cerveja));
//        list.add(new NavDrawerMenuItem(R.string.parceiro_ambev));
//        list.add(new NavDrawerMenuItem(R.string.nosso_bar));
//        list.add(new NavDrawerMenuItem(R.string.maquina_gelo));
//        list.add(new NavDrawerMenuItem(R.string.catalogo_ambev));
//        list.add(new NavDrawerMenuItem(R.string.tutorial));
//        list.add(new NavDrawerMenuItem(R.string.sair));
//        return list;
//    }
//
//    public static String enviarPedido(PedidoVO p, boolean editar, Context context) {
//        String json = null;
//
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        String produtosEnviar = "";
//        String combosEnviar = "";
//        if(p.getProdutos().size() > 0 && p.getProdutos() != null) {
//            for (ProdutoVO produto : p.getProdutos()) {
//                if(produto.getQuantidade() > 0) {
//                    produtosEnviar += produto.getId() + ":" + produto.getQuantidade() + ";";
//                }
//            }
//        }
//
//        for(ComboVO combo : p.getListCombos()) {
//            if(combo.getQuantidade() > 0) {
//                if (combo.isCombo()) {
//                    combosEnviar += combo.getId() + ":" + combo.getQuantidade() + ";";
//                } else {
//                    produtosEnviar += combo.getId() + ":" + combo.getQuantidade() + ";";
//                }
//            }
//        }
//        try {
//            params.put("usuario", p.getUsuario().getIdUsuário());
//            params.put("dataEntrega", p.getDataEntrega());
//            params.put("formaPagamento", String.valueOf(p.getForma().getId()));
//            params.put("produtos", produtosEnviar);
//            params.put("combos", combosEnviar);
//            if(editar) {
//                params.put("pedido_id", String.valueOf(p.getIdPedido()));
//            }
//            http.doPost(URL_SERVER + "/ws/salvarPedido.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (json != null) {
//            return json;
//        }
//
//        return null;
//    }
//
//    public static List<ProdutoVO> getProdutosCliente(Context context) {
//        String json = null;
//        UsuarioVO usuario = PedidoFacilApplication.getInstance().getUsuario();
//        List<ProdutoVO> list = null;
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        try {
//            params.put("id", usuario.getIdUsuário());
//            http.doPost(URL_SERVER + "/ws/getSugestaoByUser.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (json != null) {
//            try {
//                JSONObject jObj = new JSONObject(json);
//                PedidoFacilApplication.getInstance().getUsuario().dataSugestao = jObj.getString("dataSugestao");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            list = parseJSONProdutos(json);
//        }
//        return list;
//    }
//
//    public static List<ComboVO> getCombosCliente(Context context) {
//        String json = null;
//        UsuarioVO usuario = PedidoFacilApplication.getInstance().getUsuario();
//        List<ComboVO> list = null;
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        try {
//            params.put("id", usuario.getIdUsuário());
//            http.doPost(URL_SERVER + "/ws/getSugestaoByUser.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (json != null) {
//            try {
//                JSONObject jObj = new JSONObject(json);
//                PedidoFacilApplication.getInstance().getUsuario().dataSugestao = jObj.getString("dataSugestao");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            list = parseJSONCombos(json);
//        }
//        return list;
//    }
//
//    public static PedidoVO getPedido(Context context, String idPedido) {
//        String json = null;
//        PedidoVO pedido = new PedidoVO();
//        List<ProdutoVO> list;
//        List<ComboVO> listCombos;
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        try {
//            params.put("id", idPedido);
//            http.doPost(URL_SERVER + "/ws/getProdutosByPedido.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (json != null) {
//            list = parseJSONProdutos(json);
//            listCombos = parseJSONCombosPedido(json);
//            pedido.setList(list);
//            pedido.setListCombos(listCombos);
//            try {
//                JSONObject j = new JSONObject(json);
//                int id = j.getInt("id");
//                String numeroNota = j.getString("numeroNota");
//                String serieNot = j.getString("serieNota");
//                String origem = j.getString("origem");
//                String operacao = j.getString("operacao");
//                JSONObject jForma = j.getJSONObject("formaPagamento");
//                String descForma = jForma.getString("descCondicao");
//                JSONObject boleto = j.getJSONObject("boleto");
//                if(boleto != null && !boleto.toString().equals("{}")) {
//                    String numero = boleto.getString("numero");
//                    String dataVencimento = boleto.getString("dataVencimento");
//                    String cedente = boleto.getString("cedente");
//                    String sacaco = boleto.getString("sacaco");
//                    double valor = boleto.getDouble("valor");
//                    String url = boleto.getString("url");
//                    BoletoVO boletoVO = new BoletoVO(numero, dataVencimento, cedente, sacaco, valor, url);
//                    pedido.setBoleto(boletoVO);
//                }
//
//                pedido.setNumeroNota(numeroNota);
//                pedido.setSerieNota(serieNot);
//                pedido.setOrigem(origem);
//                pedido.setOperacao(operacao);
//                pedido.setIdPedido(id);
//                FormaPagamento formaPagamento = new FormaPagamento(descForma, 0, null, 0, 0);
//                pedido.setForma(formaPagamento);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return pedido;
//    }
//
//    private static List<ComboVO> parseJSONCombosPedido(String json) {
//        List<ComboVO> listCombos = new ArrayList<ComboVO>();
//        try {
//            JSONObject jObj = new JSONObject(json);
//            JSONArray combosArray = jObj.getJSONArray("combos");
//            for (int i = 0; i < combosArray.length(); i++) {
//                JSONObject j = combosArray.getJSONObject(i);
//                int idCombo = j.getInt("id");
//                String codigoCombo = j.getString("codigo");
//                String nomeCombo = j.getString("nome");
//                double precoUniCombo = j.getDouble("valorPreco");
//                int quantidadePedidoCombo = j.getInt("qtdePedido");
//
//                JSONArray produtosCombo = j.getJSONArray("produtos");
//                List<ProdutoVO> produtosComboList = new ArrayList<ProdutoVO>();
//                for (int i2 = 0; i2 < produtosCombo.length(); i2++) {
//                    JSONObject j2 = produtosCombo.getJSONObject(i2);
//                    int id = j2.getInt("id");
//                    String codigo = j2.getString("codigo");
//                    String nome = j2.getString("nome");
//                    String marca = j2.getString("marca");
//                    String embalagem = j2.getString("embalagem");
//                    double precoUni = j2.getDouble("valorPreco");
//                    int quantidadePedido = j2.getInt("qtdePedido");
//                    String volume = j2.getString("volume");
//                    int volumeLiquido = j2.getInt("volumeLiquido");
//                    String vasilhame = j2.getString("vasilhame");
//                    int capacidadeEmbalagem = j2.getInt("capacidadeEmbalagem");
//                    String urlProduto = j2.getString("thumbProdutoMobile");
//                    String urlMarca = j2.getString("thumbMarcaMobile");
//                    String tipo = j2.getString("tipo");
//
//                    ProdutoVO produto = new ProdutoVO();
//                    if (j2.getString("bonificacao").equals("S")) {
//                        produto.setBonificacao(true);
//                    } else {
//                        produto.setBonificacao(false);
//                    }
//                    produto.setId(id);
//                    produto.setCodigo(codigo);
//                    produto.setNome(nome);
//                    produto.setMarca(marca);
//                    produto.setEmbalagem(embalagem);
//                    produto.setPrecoUni(precoUni);
//                    produto.setQuantidade(quantidadePedido);
//                    produto.setPrecoTotalPedido(precoUni * quantidadePedido);
//                    produto.setVolume(volume);
//                    produto.setVolumeLiquido(volumeLiquido);
//                    produto.setVasilhame(vasilhame);
//                    produto.setCapacidadeEmbalagem(capacidadeEmbalagem);
//                    produto.setUrlMarca(urlMarca);
//                    produto.setUrlProduto(urlProduto);
//                    produto.setTipo(tipo);
//                    produto.setCombo(false);
//                    produto.setProdutoCombo(true);
//                    if(i2 == produtosCombo.length() - 1) {
//                        produto.setLastProdutoCombo(true);
//                    }
//                    produtosComboList.add(produto);
//                }
//
//                ComboVO combo = new ComboVO();
//                combo.setCombo(true);
//                combo.setId(idCombo);
//                combo.setNome(nomeCombo);
//                combo.setCodigo(codigoCombo);
//                combo.setPrecoUni(precoUniCombo);
//                combo.setListProdutos(produtosComboList);
//                combo.setQuantidade(quantidadePedidoCombo);
//                listCombos.add(combo);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return listCombos;
//        }
//        return listCombos;
//    }
//
//
//    public static List<FormaPagamento> getFormasPagamento(Context context) {
//        String json = null;
//        UsuarioVO usuario = PedidoFacilApplication.getInstance().getUsuario();
//        List<FormaPagamento> list = null;
//        if(usuario == null) {
//            return null;
//        }
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        try {
//            params.put("id", usuario.getIdUsuário());
//            http.doPost(URL_SERVER + "/ws/getFormasPagamentoByUser.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (json != null) {
//            list = parseJSONFormasPagamento(json);
//        }
//        return list;
//    }
//
//    private static List<FormaPagamento> parseJSONFormasPagamento(String json) {
//        List<FormaPagamento> list = new ArrayList<FormaPagamento>();
//        try {
//            JSONArray jsonArray = new JSONArray(json);
//            for(int i = 0; i < jsonArray.length(); i++) {
//                JSONObject j = jsonArray.getJSONObject(i);
//                int id = j.getInt("id");
//                String nome = j.getString("descCondicao");
//                double taxaMensal = j.getDouble("taxaMensalAdf");
//                int diasVencimento = j.getInt("diasVencimento");
//
//                JSONArray faixas = j.getJSONArray("faixas");
//                List<FaixasPagamento> listFaixas = new ArrayList<FaixasPagamento>();
//                for(int i2 = 0; i2 < faixas.length(); i2++) {
//                    JSONObject jFaixas = faixas.getJSONObject(i2);
//                    int idFaixa = jFaixas.getInt("id");
//                    double fator = jFaixas.getDouble("fator");
//                    double valor = jFaixas.getDouble("valor");
//                    FaixasPagamento faixaObj = new FaixasPagamento(idFaixa, fator, valor);
//                    listFaixas.add(faixaObj);
//                }
//
//                FormaPagamento formaPagamento = new FormaPagamento(nome, id, listFaixas, taxaMensal, diasVencimento);
//                list.add(formaPagamento);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    private static List<ProdutoVO> parseJSONProdutos(String json) {
//        List<ProdutoVO> listProdutos = new ArrayList<ProdutoVO>();
//        ProdutoVO produto;
//        try {
//            JSONObject jObj = new JSONObject(json);
//            JSONArray produtosArray = jObj.getJSONArray("produtos");
//            for(int i = 0; i < produtosArray.length(); i++) {
//                JSONObject j = produtosArray.getJSONObject(i);
//                int id = j.getInt("id");
//                String codigo = j.getString("codigo");
//                String nome = j.getString("nome");
//                String marca = j.getString("marca");
//                String embalagem = j.getString("embalagem");
//                String descricaoUnidadeVenda = j.getString("descricaoUnidadeVenda");
//                double precoUni = j.getDouble("valorPreco");
//                int quantidadePedido = j.getInt("qtdePedido");
//                String volume = j.getString("volume");
//                int volumeLiquido = j.getInt("volumeLiquido");
//                String vasilhame = j.getString("vasilhame");
//                int capacidadeEmbalagem = j.getInt("capacidadeEmbalagem");
//                String urlProduto = j.getString("thumbProdutoMobile");
//                String urlMarca = j.getString("thumbMarcaMobile");
//                String tipo = j.getString("tipo");
//
//                produto = new ProdutoVO();
//                produto.setId(id);
//                produto.setCodigo(codigo);
//                produto.setNome(nome);
//                produto.setMarca(marca);
//                produto.setEmbalagem(embalagem);
//                produto.setDescricaoUnidadeVenda(descricaoUnidadeVenda);
//                produto.setPrecoUni(precoUni);
//                produto.setQuantidade(quantidadePedido);
//                produto.setPrecoTotalPedido(precoUni * quantidadePedido);
//                produto.setVolume(volume);
//                produto.setVolumeLiquido(volumeLiquido);
//                produto.setVasilhame(vasilhame);
//                produto.setCapacidadeEmbalagem(capacidadeEmbalagem);
//                produto.setUrlMarca(urlMarca);
//                produto.setUrlProduto(urlProduto);
//                produto.setTipo(tipo);
//                listProdutos.add(produto);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return listProdutos;
//    }
//
//    private static List<ProdutoVO> parseJSONProdutosBusca(String json) {
//        List<ProdutoVO> listProdutos = new ArrayList<ProdutoVO>();
//        try {
//            JSONArray jObj = new JSONArray(json);
//            for(int i = 0; i < jObj.length(); i++) {
//                JSONObject j = jObj.getJSONObject(i);
//                ProdutoVO produto = getProdutoVO(j);
//                produto.setDataVigencia(j.getString("dtVigencia"));
//                produto.setProdutoCombo(false);
//                listProdutos.add(produto);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return listProdutos;
//    }
//
//    private static List<ComboVO> parseJSONCombos(String json) {
//        List<ComboVO> listCombos = new ArrayList<ComboVO>();
//        try {
//            JSONObject jObj = new JSONObject(json);
//            JSONObject jPromocao = jObj.getJSONObject("promocao");
//
//            JSONArray produtosPromocao = jPromocao.getJSONArray("produtos");
//            for (int i = 0; i < produtosPromocao.length(); i++) {
//                JSONObject j2 = produtosPromocao.getJSONObject(i);
//                ProdutoVO produto = getProdutoVO(j2);
//                produto.setDataVigencia(j2.getString("dtVigencia"));
//                produto.setProdutoCombo(false);
//                listCombos.add(new ComboVO(produto));
//            }
//
//            JSONArray combosArray = jPromocao.getJSONArray("combos");
//            for (int i = 0; i < combosArray.length(); i++) {
//                JSONObject j = combosArray.getJSONObject(i);
//                int idCombo = j.getInt("id");
//                String codigoCombo = j.getString("codigo");
//                String nomeCombo = j.getString("nome");
//                int qtdeMaxVenda = j.getInt("qtdeMaxVenda");
////                int qtdePedido = j.getInt("qtdePedido");
//                double precoUniCombo = j.getDouble("valorPreco");
//                String dataVigencia = j.getString("dataVigencia");
//                boolean restricao = j.getBoolean("restricao");
//
//                JSONArray produtosCombo = j.getJSONArray("produtos");
//                List<ProdutoVO> produtosComboList = new ArrayList<ProdutoVO>();
//                for (int i2 = 0; i2 < produtosCombo.length(); i2++) {
//                    JSONObject j2 = produtosCombo.getJSONObject(i2);
//                    ProdutoVO produto = getProdutoVO(j2);
//                    if(i2 == produtosCombo.length() - 1) {
//                        produto.setLastProdutoCombo(true);
//                    }
//                    produto.setProdutoCombo(true);
//                    produtosComboList.add(produto);
//                }
//
//                ComboVO combo = new ComboVO();
//                combo.setCombo(true);
//                combo.setId(idCombo);
//                combo.setNome(nomeCombo);
//                combo.setCodigo(codigoCombo);
////                combo.setQuantidade(qtdePedido);
//                combo.setQtdeMaxVenda(qtdeMaxVenda);
//                combo.setRestricao(restricao);
//                combo.setPrecoUni(precoUniCombo);
//                combo.setDataVigencia(dataVigencia);
//                combo.setListProdutos(produtosComboList);
//                listCombos.add(combo);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return listCombos;
//    }
//
//    private static ProdutoVO getProdutoVO(JSONObject j2) throws JSONException {
//        int id = j2.getInt("id");
//        String codigo = j2.getString("codigo");
//        String nome = j2.getString("nome");
//        String marca = j2.getString("marca");
//        String embalagem = j2.getString("embalagem");
//        double precoUni = j2.getDouble("valorPreco");
//        int quantidadePedido = j2.getInt("qtdePedido");
//        String volume = j2.getString("volume");
//        int volumeLiquido = j2.getInt("volumeLiquido");
//        String vasilhame = j2.getString("vasilhame");
//        int capacidadeEmbalagem = j2.getInt("capacidadeEmbalagem");
//        String urlProduto = j2.getString("thumbProdutoMobile");
//        String urlMarca = j2.getString("thumbMarcaMobile");
//        String tipo = j2.getString("tipo");
//        boolean promocao = j2.getBoolean("promocao");
//
//        ProdutoVO produto = new ProdutoVO();
//        if (j2.getString("bonificacao").equals("S")) {
//            produto.setBonificacao(true);
//        } else {
//            produto.setBonificacao(false);
//        }
//        produto.setId(id);
//        produto.setCodigo(codigo);
//        produto.setNome(nome);
//        produto.setMarca(marca);
//        produto.setEmbalagem(embalagem);
//        produto.setPrecoUni(precoUni);
//        produto.setQuantidade(quantidadePedido);
//        produto.setVolume(volume);
//        produto.setVolumeLiquido(volumeLiquido);
//        produto.setVasilhame(vasilhame);
//        produto.setUrlMarca(urlMarca);
//        produto.setUrlProduto(urlProduto);
//        produto.setCapacidadeEmbalagem(capacidadeEmbalagem);
//        produto.setTipo(tipo);
//        produto.setCombo(false);
//        produto.setPromocao(promocao);
//        return produto;
//    }
//
//    public static String formatValor(double total) {
//        DecimalFormat dec = new DecimalFormat("###,###.###");
//        dec.setMinimumFractionDigits(2);
//        return dec.format(total);
//    }
//
//    public static boolean isDevMode() {
//        return true;
//    }
//
//    public static String isDataValida(Context context, String data)  {
//
//        String json = null;
//        UsuarioVO usuario = PedidoFacilApplication.getInstance().getUsuario();
//        if(usuario != null) {
//            HttpHelper http = getHttpHelper();
//            Map<String, String> params = getHttpParams(context);
//            try {
//                params.put("usuario", usuario.getIdUsuário());
//                params.put("data", data);
//                http.doPost(URL_SERVER + "/ws/validateDataEntregaByUser.htm", params);
//                json = http.getString();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            if (json != null) {
//                JSONObject j;
//                try {
//                    j = new JSONObject(json);
//                    String status = j.getString("status");
//                    String mensagem = j.getString("mensagem");
//                    if(status.equals("OK")) {
//                        return status;
//                    } else if(status.equals("FORAENTREGA")) {
//                        return status +";"+mensagem;
//                    }
//                    return mensagem;
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//        }
//        return null;
//    }
//
//    public static List<ProdutoVO> getProdutosByNome(Context context, String textToSearch) {
//        String json = null;
//        UsuarioVO usuario = PedidoFacilApplication.getInstance().getUsuario();
//        List<ProdutoVO> list = null;
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        try {
//            params.put("user_id", usuario.getIdUsuário());
//            params.put("text", textToSearch);
//            http.doPost(URL_SERVER + "/ws/getProdutos.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (json != null) {
//            list = parseJSONProdutosBusca(json);
//        }
//        return list;
//    }
//
//    public static List<NotaVO> getPedidosCliente(Context context, UsuarioVO usuario) {
//        String json = null;
//        List<NotaVO> list = null;
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        try {
//            params.put("usuario", usuario.getIdUsuário());
//            http.doPost(URL_SERVER + "/ws/getPedidosByUser.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (json != null) {
//            list = parseJSONPedidos(json);
//        }
//        return list;
//    }
//
//    private static List<NotaVO> parseJSONPedidos(String json) {
//        List<NotaVO> list = new ArrayList<NotaVO>();
//        try {
//            JSONObject jObj = new JSONObject(json);
//            JSONArray npagos = jObj.getJSONArray("npago");
//            if(npagos != null && npagos.length() > 0) {
//                for(int i = 0; i < npagos.length(); i++) {
//                    JSONObject j = npagos.getJSONObject(i);
//                    int idPedido = j.getInt("id");
//                    JSONObject jForma = j.getJSONObject("formaPagamento");
//                    String formaPagamento = jForma.getString("descCondicao");
//                    String dataPedido = j.getString("dataPedido");
//                    String dataEntrega = j.getString("dataEntrega");
//                    String status = j.getString("statusPedido");
//                    double valor = j.getDouble("valorTotal");
//                    NotaVO nota = new NotaVO(idPedido, dataPedido, dataEntrega, valor, status, false);
//                    list.add(nota);
//                }
//            }
//            JSONArray pagos = jObj.getJSONArray("pago");
//            if(pagos != null && pagos.length() > 0) {
//                for(int i = 0; i < pagos.length(); i++) {
//                    JSONObject j = pagos.getJSONObject(i);
//                    int idPedido = j.getInt("id");
//                    JSONObject jForma = j.getJSONObject("formaPagamento");
//                    String formaPagamento = jForma.getString("descCondicao");
//                    String dataPedido = j.getString("dataPedido");
//                    String dataEntrega = j.getString("dataEntrega");
//                    String status = j.getString("statusPedido");
//                    double valor = j.getDouble("valorTotal");
//                    NotaVO nota = new NotaVO(idPedido, dataPedido, dataEntrega, valor, status, true);
//                    list.add(nota);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public static void loadImage(Context context, ProdutoVO p, final ImageView imgProduto, final ProgressBar progress) {
//        if(StringUtils.notEquals(p.getUrlMarca(), "") && p.getUrlMarca() != null) {
//            Picasso.with(context).load(p.getUrlMarca()).fit().into(imgProduto, new Callback() {
//                @Override
//                public void onSuccess() {
//                    progress.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onError() {
//                    imgProduto.setImageResource(R.drawable.placeholder_marca);
//                    progress.setVisibility(View.GONE);
//                }
//            });
//        } else {
//            progress.setVisibility(View.GONE);
//            imgProduto.setImageResource(R.drawable.placeholder_marca);
//        }
//    }
//
//    public static String informarPedidoNaoEntregue(Context context, String idPedido, String s) {
//        String json = null;
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        try {
//            params.put("pedido_id", idPedido);
//            params.put("observacao", s);
//            http.doPost(URL_SERVER + "/ws/informarPedidoNaoEntregue.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String resultado = "";
//        if (json != null) {
//            try {
//                JSONObject jsonObject = new JSONObject(json);
//                String status =  jsonObject.getString("status");
//                String mensagem = jsonObject.getString("mensagem");
//                resultado = status + ";" + mensagem;
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return resultado;
//    }
//
//    public static String solicitarDanfe(Context context, String idPedido) {
//        String json = null;
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        try {
//            params.put("pedido_id", idPedido);
//            http.doPost(URL_SERVER + "/ws/solicitarDanfe.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String resultado = "";
//        if (json != null) {
//            try {
//                JSONObject jsonObject = new JSONObject(json);
//                String status =  jsonObject.getString("status");
//                String mensagem = jsonObject.getString("mensagem");
//                resultado = status + ";" + mensagem;
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return resultado;
//    }
//
//    public static String cancelarPedido(Context context, String idPedido) {
//        String json = null;
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        try {
//            params.put("pedido_id", idPedido);
//            http.doPost(URL_SERVER + "/ws/cancelarPedido.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String resultado = "";
//        if (json != null) {
//            try {
//                JSONObject jsonObject = new JSONObject(json);
//                String status =  jsonObject.getString("status");
//                String mensagem = jsonObject.getString("mensagem");
//                resultado = status + ";" + mensagem;
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return resultado;
//    }
//
//    public static List<CatalogoFiltro> getFiltros(Context context, int tipo) {
//        String json = null;
//        List<CatalogoFiltro> listCateg = new ArrayList<>();
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        String busca;
//        switch (tipo) {
//            case 1:
//                busca = "tipoproduto";
//                break;
//            case 2:
//                busca = "marca";
//                break;
//            case 3:
//                busca = "embalagem";
//                break;
//            default:
//                busca = "";
//                break;
//        }
//        try {
//            params.put("busca", busca);
//            http.doPost(URL_SERVER + "/ws/getTipoMarcaEmbalagem.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        listCateg = parseJSONCateg(json, tipo);
//
//        return listCateg;
//    }
//
//    private static List<CatalogoFiltro> parseJSONCateg(String json, int tipo) {
//
//        List<CatalogoFiltro> listRetorno = new ArrayList<>();
//
//        String desc = "";
//        int id = 0;
//
//        try {
//            JSONArray jArray = new JSONArray(json);
//            for (int i = 0; i < jArray.length(); i++) {
//                JSONObject jObj = jArray.getJSONObject(i);
//                id = jObj.getInt("id");
//                if(id == 7) {
//                    continue;
//                }
//                if (tipo == 1) {
//                    desc = jObj.getString("tipoProduto");
//                } else if (tipo == 2) {
//                    desc = jObj.getString("marca");
//                } else if (tipo == 3) {
//                    desc = jObj.getString("nome");
//                }
//                CatalogoFiltro categ = new CatalogoFiltro(desc);
//                categ.setId(id);
//                categ.setTipo(tipo);
//                listRetorno.add(categ);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return listRetorno;
//    }
//
//    public static List<ProdutoVO> getProdutosCatalogo(Context context, CatalogoFiltro filtroTipoProduto, CatalogoFiltro filtroMarca, CatalogoFiltro filtroEmbalagem) {
//        String json = null;
//        UsuarioVO usuario = PedidoFacilApplication.getInstance().getUsuario();
//        List<ProdutoVO> list = null;
//        HttpHelper http = getHttpHelper();
//        Map<String, String> params = getHttpParams(context);
//        String idTipoProduto = filtroTipoProduto.getId() != 0 ? String.valueOf(filtroTipoProduto.getId()) : "";
//        String idMarca = filtroMarca.getId() != 0 ? String.valueOf(filtroMarca.getId()) : "";
//        String idEmbalagem = filtroEmbalagem.getId() != 0 ? String.valueOf(filtroEmbalagem.getId()) : "";
//        try {
//            params.put("user_id", usuario.getIdUsuário());
//            params.put("tipo_produto_id", idTipoProduto);
//            params.put("marca_id", idMarca);
//            params.put("embalagem_id", idEmbalagem);
//            http.doPost(URL_SERVER + "/ws/getProdutos.htm", params);
//            json = http.getString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (json != null && !StringUtils.contains("NOK", json)) {
//            list = parseJSONProdutosBusca(json);
//        } else if (StringUtils.contains("NOK", json)) {
//            return null;
//        }
//        return list;
//    }
//}
