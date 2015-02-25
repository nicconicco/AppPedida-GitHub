package developerappedida.appedida.activity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import developerappedida.appedida.R;
import developerappedida.appedida.adapter.AdapterAppedida;

public class FazerPedidoActivity extends BaseActivity {

    private ListView lAppedida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazerpedido);

        lAppedida = (ListView) findViewById(R.id.lAppedida);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        AdapterAppedida adapter = new AdapterAppedida(getActivity(), list);
        lAppedida.setAdapter(adapter);

    }

}
