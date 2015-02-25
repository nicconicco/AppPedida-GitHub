package developerappedida.appedida.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import developerappedida.appedida.R;

public class MenuAppedida extends BaseActivity {

    private LinearLayout tFazerPedido;
    private LinearLayout tMeusPedidos;
    private LinearLayout tEditarPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuappedida);

        tFazerPedido = (LinearLayout) findViewById(R.id.tFazerPedido);
        tMeusPedidos = (LinearLayout) findViewById(R.id.tMeusPedidos);
        tEditarPerfil = (LinearLayout) findViewById(R.id.tEditar);

        tFazerPedido.findViewById(R.id.tFazerPedido).setOnClickListener(fazerPedido());
        tMeusPedidos.findViewById(R.id.tMeusPedidos).setOnClickListener(meusPedidos());
        tEditarPerfil.findViewById(R.id.tEditar).setOnClickListener(editarPerfil());
    }

    private View.OnClickListener editarPerfil() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(EditarPerfilActivity.class);
            }
        };
    }

    private View.OnClickListener meusPedidos() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(MeusPedidosActivity.class);
            }
        };
    }

    private View.OnClickListener fazerPedido() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(FazerPedidoActivity.class);
            }
        };
    }
}
