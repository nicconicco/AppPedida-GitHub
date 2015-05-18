package developerappedida.appedida.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import developerappedida.appedida.AppedidaAplication;
import developerappedida.appedida.R;
import developerappedida.appedida.adapter.AdapterAppedida;
import developerappedida.appedida.adapter.AdapterAppedidaSelecionados;
import developerappedida.appedida.domain.Produto;

/**
 * Created by Carlos Nicolau Galves on 18/05/2015.
 */
public class SelecionarUnidadesActivity extends BaseActivity {

    private List<Produto> listaProdutoSelecionados = new ArrayList<Produto>();
    private ListView lAppedida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_unidades);


        lAppedida = (ListView) findViewById(R.id.lAppedida);

        AdapterAppedidaSelecionados adapter = new AdapterAppedidaSelecionados(getActivity(), (ArrayList<Produto>) AppedidaAplication.getInstance().getListProduto());
        lAppedida.setAdapter(adapter);

    }
}
