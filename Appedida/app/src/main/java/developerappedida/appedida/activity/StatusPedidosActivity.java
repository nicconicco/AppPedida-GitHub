package developerappedida.appedida.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.livroandroid.task.BaseTask;
import br.livroandroid.task.Task;
import developerappedida.appedida.R;
import developerappedida.appedida.adapter.AdapterAppedidaPedidos;
import developerappedida.appedida.adapter.AdapterPedidosOnline;
import developerappedida.appedida.domain.AppedidaService;
import developerappedida.appedida.domain.Pedido;
import developerappedida.appedida.domain.PedidoOnline;

/**
 * Created by nicolaugalves on 6/22/15.
 */
public class StatusPedidosActivity extends BaseActivity {


    private static final String TAG = StatusPedidosActivity.class.getSimpleName();
    private ListView lAppedida;
    private List<PedidoOnline> pedidosOnline = new ArrayList<PedidoOnline>();
    private LinearLayout btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statuspedido);


        btnVoltar = (LinearLayout) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(voltarParaMenu());

        lAppedida = (ListView) findViewById(R.id.lAppedida);
        startTaskParallel(GetAllPedidoPorStatus(), R.id.progress);

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

    private Task GetAllPedidoPorStatus() {
        return new BaseTask() {
            @Override
            public void execute() throws Exception {
                pedidosOnline = AppedidaService.GetAllPedidoPorStatus(getContext());
            }

            @Override
            public void updateView() {
                if(pedidosOnline != null) {
                    AdapterPedidosOnline adapter = new AdapterPedidosOnline(getActivity(), (ArrayList<PedidoOnline>) pedidosOnline);
                    lAppedida.setAdapter(adapter);
                }
            }
        };
    }
}
