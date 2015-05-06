package developerappedida.appedida.activity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.livroandroid.task.BaseTask;
import br.livroandroid.task.Task;
import developerappedida.appedida.R;
import developerappedida.appedida.adapter.AdapterAppedida;
import developerappedida.appedida.domain.Pedido;
import developerappedida.appedida.domain.AppedidaService;
import developerappedida.appedida.domain.Produto;

public class FazerPedidoActivity extends BaseActivity {

    private ListView lAppedida;
    private List<Pedido> listaPedidos;
    private List<Produto> listaProduto = new ArrayList<Produto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazerpedido);

        lAppedida = (ListView) findViewById(R.id.lAppedida);
        listaPedidos = AppedidaService.getListaDeProdutos(getContext());



        AdapterAppedida adapter = new AdapterAppedida(getActivity(), (ArrayList<Pedido>) listaPedidos);

        lAppedida.setAdapter(adapter);

        startTask(taskGetAllProdutos(), R.id.progress);

    }

    private Task taskGetAllProdutos() {
        return new BaseTask() {
            @Override
            public void execute() throws Exception {
                listaProduto = AppedidaService.getAllProdutos();
            }

            @Override
            public void updateView() {

            }
        };
    }

}
