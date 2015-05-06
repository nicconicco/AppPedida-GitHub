package developerappedida.appedida.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import developerappedida.appedida.R;

public class MenuAppedida extends BaseActivity {

    private LinearLayout tFazerPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuappedida);

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
