package developerappedida.appedida.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import developerappedida.appedida.AppedidaAplication;
import developerappedida.appedida.R;
import developerappedida.appedida.domain.User;
import developerappedida.appedida.domain.AppedidaService;
import developerappedida.appedida.util.EmailValidator;


public class RegistrarAppedida extends BaseActivity {

    private LinearLayout tRegistrar;

    private EditText tLogin;
    private EditText tSenha;
    private EditText tEmail;
    private EditText tCpf;
    private EditText tCelular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarappedida);

        tRegistrar = (LinearLayout) findViewById(R.id.tRegistrar);

        tLogin = (EditText) findViewById(R.id.tLogin);
        tSenha = (EditText) findViewById(R.id.tSenha);
        tEmail = (EditText) findViewById(R.id.tEmail);
        tCpf = (EditText) findViewById(R.id.tCpf);
        tCelular = (EditText) findViewById(R.id.tCelular);

        tRegistrar.setOnClickListener(registrarUsuario());
    }

    private View.OnClickListener registrarUsuario() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validaDados()) {
                    toast(getString(R.string.usuario_cadastrado));
                    if(setNewUser()) {
                        show(MenuAppedida.class);
                    }
                }else{
                    toast(getString(R.string.faltou_algum_dado));
                }
            }
        };
    }

    private boolean setNewUser() {
        User usuario = new User();
        usuario.setLogin(tLogin.getText().toString());
        usuario.setSenha(tSenha.getText().toString());
        usuario.setEmail(tEmail.getText().toString());
        usuario.setCpf(tCpf.getText().toString());
        usuario.setCelular(tCelular.getText().toString());

        if(AppedidaService.saveUser(getContext(), usuario)) {
            AppedidaAplication.getInstance().setUser(usuario);
            return true;
        }
        return false;
    }

    private boolean validaDados() {

        EmailValidator validarEmail = new EmailValidator();

        boolean validaEmail = validarEmail.validate(tSenha.getText().toString());

        if(!validaEmail){
            toast(R.string.email_invalido);
            return false;
        }

        boolean contemDigitosCelular = tCelular.getText().toString().contains("41") && tCelular!= null && (tCelular.getTextSize()>9);

        if(!contemDigitosCelular){
            toast(R.string.msg_celular_erro);
            return false;
        }

        boolean contemCpf = tCpf.getTextSize() > 10;

        if(!contemCpf){
            toast(R.string.cpf_invalido);
            return false;
        }

        if(tLogin != null && tSenha != null && validaEmail && contemCpf && contemDigitosCelular){
            return true;
        }
        return false;
    }
}
