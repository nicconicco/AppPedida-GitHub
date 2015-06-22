package developerappedida.appedida.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import developerappedida.appedida.R;
import developerappedida.appedida.domain.Pedido;
import developerappedida.appedida.domain.Produto;

/**
 * Created by mestre on 22/05/2015.
 */
public class AdapterAppedidaPedidos extends BaseAdapter {

    private final LayoutInflater inflater;
    private Activity context;
    private List<Pedido> list;
    private List<Pedido> listProduto = new ArrayList<Pedido>();

    public AdapterAppedidaPedidos(Activity activity, ArrayList<Pedido> listProduto) {
        this.context = activity;
        this.list = listProduto;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : null;
    }

    @Override
    public Object getItem(int position) {
        return list != null ? list.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pedido item = (Pedido) getItem(position);
        View row = inflater.inflate(R.layout.adapter_row_lista_pedidos, null);

        TextView tPedidos = (TextView) row.findViewById(R.id.tPedidos);
        TextView tPreco = (TextView) row.findViewById(R.id.tPreco);


        tPedidos.setText(item.getIdsProdutos());
        tPreco.setText(item.getValor_Pedido().replace(".",",")+"0");


        return row;
    }
}
