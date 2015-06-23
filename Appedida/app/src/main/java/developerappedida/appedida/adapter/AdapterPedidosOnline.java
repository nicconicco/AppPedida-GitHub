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

        TextView tIdDoPedido = (TextView) row.findViewById(R.id.tIdDoPedido);
        TextView tValorDoPedido = (TextView) row.findViewById(R.id.tValorDoPedido);
        TextView tStatusDoPedido = (TextView) row.findViewById(R.id.tStatusDoPedido);

        tIdDoPedido.setText(item.getIdPedido());
        tValorDoPedido.setText(item.getValorPedido().replace(".",",")+"0");

        if(item.getIdStatusPedido().toString().equals("1")) {
            tStatusDoPedido.setText("Aprovado");
            tStatusDoPedido.setTextColor(context.getResources().getColor(R.color.verde));
        }else if (item.getIdStatusPedido().toString().equals("2")) {
            tStatusDoPedido.setText("Pendente");
            tStatusDoPedido.setTextColor(context.getResources().getColor(R.color.amarelo));
        }else if (item.getIdStatusPedido().toString().equals("3")) {
            tStatusDoPedido.setText("Não Aprovado");
            tStatusDoPedido.setTextColor(context.getResources().getColor(R.color.vermelho));
        }else if (item.getIdStatusPedido().toString().equals("4")) {
            tStatusDoPedido.setText("Cancelado");
            tStatusDoPedido.setTextColor(context.getResources().getColor(R.color.vermelho));
        }else {
            tStatusDoPedido.setText(item.getIdStatusPedido());
        }

        return row;
    }
}
