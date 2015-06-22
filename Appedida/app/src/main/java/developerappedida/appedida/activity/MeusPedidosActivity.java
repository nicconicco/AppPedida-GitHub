package developerappedida.appedida.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import developerappedida.appedida.AppedidaAplication;
import developerappedida.appedida.R;
import developerappedida.appedida.adapter.AdapterAppedidaPedidos;
import developerappedida.appedida.adapter.AdapterAppedidaSelecionados;
import developerappedida.appedida.domain.AppedidaService;
import developerappedida.appedida.domain.Pedido;
import developerappedida.appedida.domain.Produto;

public class MeusPedidosActivity extends BaseActivity {

    private ListView lAppedida;
    private LinearLayout btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meuspedidos);

        lAppedida = (ListView) findViewById(R.id.lAppedida);

        btnVoltar = (LinearLayout) findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(voltarParaMenu());

    }

    private View.OnClickListener voltarParaMenu() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(MenuAppedida.class);
                finish();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Pedido> listPedido = AppedidaService.getPedidosJaRealizados(getContext());
        if(listPedido != null) {
            AdapterAppedidaPedidos adapter = new AdapterAppedidaPedidos(getActivity(), (ArrayList<Pedido>) listPedido);
            lAppedida.setAdapter(adapter);
        }

    }
}
