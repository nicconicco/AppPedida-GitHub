package developerappedida.appedida.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import developerappedida.appedida.AppedidaAplication;
import developerappedida.appedida.R;
import developerappedida.appedida.activity.SelecionarUnidadesActivity;
import developerappedida.appedida.domain.Produto;

/**
 * Created by Carlos Nicolau Galves on 18/05/2015.
 */
public class AdapterAppedidaSelecionados extends BaseAdapter {

    private final LayoutInflater inflater;
    private Activity context;
    private List<Produto> list;
    private List<Produto> listProduto = new ArrayList<Produto>();

    public AdapterAppedidaSelecionados(Activity context, ArrayList<Produto> listProduto) {
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
        View row = inflater.inflate(R.layout.adapter_row_lista_produtos_selecionados, null);

        TextView tProduto = (TextView) row.findViewById(R.id.tProduto);
        TextView tPreco = (TextView) row.findViewById(R.id.tPreco);
        TextView tMais = (TextView) row.findViewById(R.id.tMais);
        TextView tMenos = (TextView) row.findViewById(R.id.tMenos);
        TextView tContador = (TextView) row.findViewById(R.id.tContador);

        tProduto.setText(item.getNome().toString());
        tPreco.setText(item.getValor().toString().replace(".",",")+"0");

        tMais.setOnClickListener(aumentaValor(tContador, item));
        tMenos.setOnClickListener(diminuiValor(tContador, item));

        return row;
    }

    private View.OnClickListener diminuiValor(final TextView tContador,final Produto item) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(tContador.getText().toString());
                if (i != 1)
                    i--;
                tContador.setText(i + "");
                item.setQuantidade(i);
                setQuantidadeNaLista(item);

            }
        };
    }

    private void setQuantidadeNaLista(Produto item) {
        for(Produto d: list){
            if(d == item){
                d.setQuantidade(item.getQuantidade());
            }
        }

        AppedidaAplication.getInstance().setListProduto(list);
    }

    private View.OnClickListener aumentaValor(final TextView tContador,final Produto item) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(tContador.getText().toString());
                i++;
                tContador.setText(i + "");
                item.setQuantidade(i);
                setQuantidadeNaLista(item);

            }
        };
    }

}
