package developerappedida.appedida.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import developerappedida.appedida.R;

public class AdapterAppedida  extends BaseAdapter {

    private final LayoutInflater inflater;
    private Activity context;
    private List<String> list;

    public AdapterAppedida(Activity context, ArrayList<String> list) {
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
        String item = (String) getItem(position);
        View row = inflater.inflate(R.layout.adapter_row_lista_produtos, null);
        TextView tMinuto = (TextView) row.findViewById(R.id.tProduto);
        tMinuto.setText(item);
        return row;
    }
}
