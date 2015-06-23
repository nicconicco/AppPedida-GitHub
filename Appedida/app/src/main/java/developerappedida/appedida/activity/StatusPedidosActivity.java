package developerappedida.appedida.activity;


import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.livroandroid.task.BaseTask;
import br.livroandroid.task.Task;
import developerappedida.appedida.R;
import developerappedida.appedida.domain.AppedidaService;
import developerappedida.appedida.domain.PedidoOnline;

/**
 * Created by nicolaugalves on 6/22/15.
 */
public class StatusPedidosActivity extends BaseActivity {


    private static final String TAG = StatusPedidosActivity.class.getSimpleName();
    private ListView lAppedida;
    private List<PedidoOnline> pedidosOnline = new ArrayList<PedidoOnline>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statuspedido);

        lAppedida = (ListView) findViewById(R.id.lAppedida);
        startTaskParallel(GetAllPedidoPorStatus(), R.id.progress);

    }

    private Task GetAllPedidoPorStatus() {
        return new BaseTask() {
            @Override
            public void execute() throws Exception {
                pedidosOnline = AppedidaService.GetAllPedidoPorStatus(getContext());
            }

            @Override
            public void updateView() {

            }
        };
    }
}
