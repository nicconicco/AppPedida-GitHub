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
import developerappedida.appedida.adapter.AdapterAppedida;
import developerappedida.appedida.domain.AppedidaService;
import developerappedida.appedida.domain.Produto;

public class FazerPedidoActivity extends BaseActivity {

    private static final String TAG = FazerPedidoActivity.class.getSimpleName();
    private ListView lAppedida;
    private List<Produto> listaProduto = new ArrayList<Produto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazerpedido);

        lAppedida = (ListView) findViewById(R.id.lAppedida);
        startTask(taskGetAllProdutos(), R.id.progress);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private Task taskGetAllProdutos() {
        return new BaseTask() {
            @Override
            public void execute() throws Exception {
                try {
                    listaProduto = AppedidaService.getAllProdutos();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }

            @Override
            public void updateView() {

                if (listaProduto != null) {
                    AdapterAppedida adapter = new AdapterAppedida(getActivity(), (ArrayList<Produto>) listaProduto);
                    lAppedida.setAdapter(adapter);
                }
            }
        };
    }

}
