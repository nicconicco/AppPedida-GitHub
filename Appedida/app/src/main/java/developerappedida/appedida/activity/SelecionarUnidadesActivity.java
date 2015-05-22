package developerappedida.appedida.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.livroandroid.task.BaseTask;
import br.livroandroid.task.Task;
import developerappedida.appedida.AppedidaAplication;
import developerappedida.appedida.R;
import developerappedida.appedida.adapter.AdapterAppedidaFinalizarPedido;
import developerappedida.appedida.adapter.AdapterAppedidaSelecionados;
import developerappedida.appedida.domain.AppedidaService;
import developerappedida.appedida.domain.Produto;

/**
 * Created by Carlos Nicolau Galves on 18/05/2015.
 */
public class SelecionarUnidadesActivity extends BaseActivity {

    private static final String TAG = SelecionarUnidadesActivity.class.getSimpleName();
    private List<Produto> listaProdutoSelecionados = new ArrayList<Produto>();
    private ListView lAppedida;
    private TextView tPrecoTotal;
    private ListView lAppedidaSelecionados;
    private boolean realizouPedido = false;
    private boolean realizouAutenticacao = false;
    private String precoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_unidades);

        lAppedida = (ListView) findViewById(R.id.lAppedida);

        findViewById(R.id.tFinalizarPedido).setOnClickListener(finalizarPedido());
    }

    private View.OnClickListener finalizarPedido() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFinalizarPedido();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdapterAppedidaSelecionados adapter = new AdapterAppedidaSelecionados(getActivity(), (ArrayList<Produto>) AppedidaAplication.getInstance().getListProduto());
        lAppedida.setAdapter(adapter);

    }

    public void openFinalizarPedido() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(getString(R.string.appedida));
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view_finalizar_pedido, null);
        dialog.setView(v);

        lAppedidaSelecionados = (ListView) v.findViewById(R.id.lAppedidaSelecionados);

        listaProdutoSelecionados = AppedidaAplication.getInstance().getListProduto();
        if (listaProdutoSelecionados != null && lAppedidaSelecionados != null) {
            AdapterAppedidaFinalizarPedido adapter = new AdapterAppedidaFinalizarPedido(getActivity(), (ArrayList<Produto>) listaProdutoSelecionados);
            lAppedidaSelecionados.setAdapter(adapter);

            float total = 0;
            for (Produto d : listaProdutoSelecionados) {

                if (d.getValor() != null && !d.getValor().contains("null")) {
                    float floatNumber = Float.parseFloat(d.getValor());
                    total = total + (d.getQuantidade() * floatNumber);
                }
            }

            precoTotal = Float.toString(total);
            precoTotal = precoTotal.replace(".", ",");
            tPrecoTotal = (TextView) v.findViewById(R.id.tPrecoTotal);
            tPrecoTotal.setText(precoTotal);
        }

        // Por padrao vem o Cancelar antes, mas no PDF ta o OK antes...
        dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startTaskParallel(tastkAutenticarUsuarioMobile(), R.id.progress);
            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    private Task tastkAutenticarUsuarioMobile() {
        return new BaseTask() {
            @Override
            public void execute() throws Exception {
                // carlosteste1234@gmail.com 123456 CarlosTeste
                realizouAutenticacao = AppedidaService.AutenticarUsuarioMobile(getContext());
            }

            @Override
            public void updateView() {
                if (realizouAutenticacao) {
                    toast("Usuario autenticado, seu pedido esta sendo realizado.");
                    startTaskParallel(taskCreatePedido(getContext(), precoTotal), R.id.progress);
                    finish();
                }
            }
        };
    }

    private Task taskCreatePedido(final Context context, final String precoTotal) {
        return new BaseTask() {
            @Override
            public void execute() throws Exception {
                try {
                    realizouPedido = AppedidaService.CreatePedido(context, listaProdutoSelecionados, precoTotal);
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }

            @Override
            public void updateView() {

                if(realizouPedido){
                    toast("Pedido realizado com sucesso!");
                        show(MeusPedidosActivity.class);
                }
            }
        };
    }
}
