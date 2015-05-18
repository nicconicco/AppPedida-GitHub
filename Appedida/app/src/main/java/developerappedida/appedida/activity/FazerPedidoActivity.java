package developerappedida.appedida.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.livroandroid.task.BaseTask;
import br.livroandroid.task.Task;
import developerappedida.appedida.AppedidaAplication;
import developerappedida.appedida.R;
import developerappedida.appedida.adapter.AdapterAppedida;
import developerappedida.appedida.domain.AppedidaService;
import developerappedida.appedida.domain.Produto;

public class FazerPedidoActivity extends BaseActivity {

    private static final String TAG = FazerPedidoActivity.class.getSimpleName();
    private ListView lAppedida;
    private ListView lAppedidaSelecionados;
    private List<Produto> listaProduto = new ArrayList<Produto>();
    private List<Produto> listaProdutoSelecionados = new ArrayList<Produto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazerpedido);

        lAppedida = (ListView) findViewById(R.id.lAppedida);
        startTask(taskGetAllProdutos(), R.id.progress);

        findViewById(R.id.tContinuar).setOnClickListener(continuarCompra());

    }

    private View.OnClickListener continuarCompra() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmarItensSelecionados();
            }
        };
    }

    public void openConfirmarItensSelecionados() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(getString(R.string.appedida));
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view_confirmar, null);
        dialog.setView(v);
        lAppedidaSelecionados = (ListView) v.findViewById(R.id.lAppedidaSelecionados);
        listaProdutoSelecionados = AppedidaAplication.getInstance().getListProduto();
        if ( listaProdutoSelecionados != null && lAppedidaSelecionados != null) {
            AdapterAppedida adapter = new AdapterAppedida(getActivity(), (ArrayList<Produto>) listaProdutoSelecionados);
            lAppedidaSelecionados.setAdapter(adapter);
        }

        // Por padrao vem o Cancelar antes, mas no PDF ta o OK antes...
        dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
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
