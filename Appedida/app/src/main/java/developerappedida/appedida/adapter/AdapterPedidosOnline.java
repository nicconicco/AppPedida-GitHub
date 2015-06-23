package developerappedida.appedida.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import developerappedida.appedida.R;
import developerappedida.appedida.domain.PedidoOnline;
import developerappedida.appedida.domain.Produto;

/**
 * Created by nicolaugalves on 6/23/15.
 */
public class AdapterPedidosOnline extends BaseAdapter {

    private List<PedidoOnline> list;
    private final LayoutInflater inflater;
    private Activity context;

    public AdapterPedidosOnline(Activity context, ArrayList<PedidoOnline> listPedidoOnline) {
        this.context = context;
        this.list = listPedidoOnline;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        PedidoOnline item = (PedidoOnline) getItem(position);
        View row = inflater.inflate(R.layout.adapter_row_lista_pedidos_online, null);

        return null;
    }
}
