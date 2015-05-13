package developerappedida.appedida.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import developerappedida.appedida.AppedidaAplication;
import developerappedida.appedida.R;
import developerappedida.appedida.domain.AppedidaService;
import developerappedida.appedida.domain.Usuario;

public class MenuAppedida extends BaseActivity {

    private LinearLayout tFazerPedido;
    private Usuario user;
    private TextView tUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuappedida);

        user = AppedidaService.getUser(getContext());

        if(user!=null){
            tUsuario = (TextView) findViewById(R.id.tUsuario);
            tUsuario.setText(user.getLogin());
        }

        tFazerPedido = (LinearLayout) findViewById(R.id.tFazerPedido);
        tFazerPedido.findViewById(R.id.tFazerPedido).setOnClickListener(fazerPedido());
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
