package developerappedida.appedida.domain;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import developerappedida.appedida.R;
import developerappedida.appedida.activity.BaseActivity;
import developerappedida.appedida.classes.Pedido;

public class AppedidaService  extends BaseActivity{

    private static List<Pedido> listaPedidos;

    public static void getListaDeProdutos(List<Pedido> listaPedidos) {
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };



//        listaPedidos.add();

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
    }

    public static List<Pedido> getListaDeProdutos(Context context) {

        Pedido pedido_um = new Pedido(context.getString(R.string.batata_com_presunto), context.getString(R.string.reais)+" "+context.getString(R.string.dez_reais));
        Pedido pedido_dois = new Pedido(context.getString(R.string.batata_com_queijo), context.getString(R.string.reais)+" "+context.getString(R.string.dez_reais));
        Pedido pedido_tres = new Pedido(context.getString(R.string.batata_com_oregano), context.getString(R.string.reais)+" "+context.getString(R.string.dez_reais));
        Pedido pedido_quatro = new Pedido(context.getString(R.string.batata_com_batata_palha), context.getString(R.string.reais)+" "+context.getString(R.string.dez_reais));
        Pedido pedido_cinco = new Pedido(context.getString(R.string.batata_com_strogonoff), context.getString(R.string.reais)+" "+context.getString(R.string.dez_reais));
        Pedido pedido_seis = new Pedido(context.getString(R.string.batata_com_rucula), context.getString(R.string.reais)+" "+context.getString(R.string.dez_reais));


        listaPedidos.add(pedido_um);
        listaPedidos.add(pedido_dois);
        listaPedidos.add(pedido_tres);
        listaPedidos.add(pedido_quatro);
        listaPedidos.add(pedido_cinco);
        listaPedidos.add(pedido_seis);

        return listaPedidos;
    }
}
