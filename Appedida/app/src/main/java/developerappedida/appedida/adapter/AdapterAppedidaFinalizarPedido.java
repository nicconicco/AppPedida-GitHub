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
import developerappedida.appedida.domain.Produto;

/**
 * Created by Carlos Nicolau Galves on 19/05/2015.
 */
public class AdapterAppedidaFinalizarPedido extends BaseAdapter {

    private final LayoutInflater inflater;
    private Activity context;
    private List<Produto> list;
    private List<Produto> listProduto = new ArrayList<Produto>();

    public AdapterAppedidaFinalizarPedido(Activity context, ArrayList<Produto> listProduto) {
        this.context = context;
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
        Produto item = (Produto) getItem(position);
        View row = inflater.inflate(R.layout.adapter_row_finalizar_pedido, null);

        TextView tProduto = (TextView) row.findViewById(R.id.tProduto);
        TextView tUnidades = (TextView) row.findViewById(R.id.tUnidades);

        tProduto.setText(item.getNome().toString());
        tUnidades.setText(item.getQuantidade()+"");


        return row;
    }
}
