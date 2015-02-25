package developerappedida.appedida.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import developerappedida.appedida.R;

public class RegistrarAppedida extends BaseActivity {

    private LinearLayout tRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarappedida);

        tRegistrar = (LinearLayout) findViewById(R.id.tRegistrar);

        tRegistrar.setOnClickListener(registrarUsuario());
    }

    private View.OnClickListener registrarUsuario() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), getString(R.string.usuario_cadastrado), Toast.LENGTH_LONG).show();
                show(MenuAppedida.class);
            }
        };
    }
}
