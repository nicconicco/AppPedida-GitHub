package developerappedida.appedida.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import developerappedida.appedida.R;
import developerappedida.appedida.classes.User;
import developerappedida.appedida.domain.AppedidaService;


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

                User user = AppedidaService.getUser(getContext());

                if(user != null) {
                    show(MenuAppedida.class, null);
                }else{
                    toast(getString(R.string.voce_precisa_estar));
                }
            }
        };
    }
}
