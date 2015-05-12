package developerappedida.appedida.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import developerappedida.appedida.R;
import developerappedida.appedida.domain.User;
import developerappedida.appedida.domain.AppedidaService;


public class MainActivity extends BaseActivity {

    private LinearLayout tEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainappedida);

        tEntrar = (LinearLayout) findViewById(R.id.tEntrar);

        tEntrar.findViewById(R.id.tEntrar).setOnClickListener(entrarAppedida());

    }

    private View.OnClickListener registrarAppedida() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }

    private View.OnClickListener entrarAppedida() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = AppedidaService.getUser(getContext());

//                if(user != null) {
                    show(MenuAppedida.class, null);
//                }else{
//                    show(RegistrarAppedida.class, null);
//                }
            }
        };
    }
}
