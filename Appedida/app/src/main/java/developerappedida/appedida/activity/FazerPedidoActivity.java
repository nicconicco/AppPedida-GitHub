package developerappedida.appedida.activity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import developerappedida.appedida.R;
import developerappedida.appedida.adapter.AdapterAppedida;
import developerappedida.appedida.classes.Pedido;
import developerappedida.appedida.domain.AppedidaService;

public class FazerPedidoActivity extends BaseActivity {

    private ListView lAppedida;
    private List<Pedido> listaPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazerpedido);

        lAppedida = (ListView) findViewById(R.id.lAppedida);
        listaPedidos = AppedidaService.getListaDeProdutos(getContext());

        AdapterAppedida adapter = new AdapterAppedida(getActivity(), (ArrayList<Pedido>) listaPedidos);
        lAppedida.setAdapter(adapter);

    }

}
