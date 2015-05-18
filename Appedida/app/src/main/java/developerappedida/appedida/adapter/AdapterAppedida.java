package developerappedida.appedida.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.livroandroid.utils.ColorUtils;
import developerappedida.appedida.R;
import developerappedida.appedida.domain.Produto;

public class AdapterAppedida  extends BaseAdapter {

    private final LayoutInflater inflater;
    private Activity context;
    private List<Produto> list;

    public AdapterAppedida(Activity context, ArrayList<Produto> list) {
        this.context = context;
        this.list = list;
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
        Produto item = (Produto) getItem(position);
        View row = inflater.inflate(R.layout.adapter_row_lista_produtos, null);

        TextView tProduto = (TextView) row.findViewById(R.id.tProduto);
        TextView tPreco = (TextView) row.findViewById(R.id.tPreco);
        TextView tDescricao = (TextView) row.findViewById(R.id.tDescricao);
        ImageView iProduto = (ImageView) row.findViewById(R.id.iProduto);
        LinearLayout lRowLista = (LinearLayout) row.findViewById(R.id.lRowLista);

        tProduto.setText(item.getNome().toString());
        tPreco.setText(item.getValor().toString());
        tDescricao.setText(item.getDescricao().toString());
//        iProduto.setImageResource(item.getId_foto());

        lRowLista.setOnClickListener(selecionaItem(item));

        if(item.isSelected()){
            lRowLista.setBackgroundColor(ColorUtils.getColor(context, R.color.branco));
            tProduto.setTextColor(ColorUtils.getColor(context, R.color.preto));
            tPreco.setTextColor(ColorUtils.getColor(context, R.color.preto));
            tDescricao.setTextColor(ColorUtils.getColor(context, R.color.preto));
        }else{
            lRowLista.setBackgroundColor(ColorUtils.getColor(context, R.color.azul_fundo_oscuro));
            tProduto.setTextColor(ColorUtils.getColor(context, R.color.branco));
            tPreco.setTextColor(ColorUtils.getColor(context, R.color.branco));
            tDescricao.setTextColor(ColorUtils.getColor(context, R.color.branco));
        }


        return row;
    }

    private View.OnClickListener selecionaItem(final Produto item) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isSelected() && item != null){
                    item.setIsSelected(false);
                    notifyDataSetChanged();
                }else{
                    item.setIsSelected(true);
                    notifyDataSetChanged();
                }
            }
        };
    }
}
