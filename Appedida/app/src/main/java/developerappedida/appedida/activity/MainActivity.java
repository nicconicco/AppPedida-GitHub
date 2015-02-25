package developerappedida.appedida.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import developerappedida.appedida.R;


public class MainActivity extends BaseActivity {

    private LinearLayout tEntrar;
    private LinearLayout tRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainappedida);

        tEntrar = (LinearLayout) findViewById(R.id.tEntrar);
        tRegistrar = (LinearLayout) findViewById(R.id.tRegistrar);

        tEntrar.findViewById(R.id.tEntrar).setOnClickListener(entrarAppedida());
        tRegistrar.findViewById(R.id.tRegistrar).setOnClickListener(registrarAppedida());

    }

    private View.OnClickListener registrarAppedida() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(RegistrarAppedida.class, null);
            }
        };
    }

    private View.OnClickListener entrarAppedida() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(MenuAppedida.class, null);
            }
        };
    }
}
