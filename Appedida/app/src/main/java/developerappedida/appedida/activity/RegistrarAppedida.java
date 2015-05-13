package developerappedida.appedida.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import br.livroandroid.task.BaseTask;
import br.livroandroid.task.Task;
import br.livroandroid.utils.StringUtils;
import developerappedida.appedida.AppedidaAplication;
import developerappedida.appedida.R;
import developerappedida.appedida.domain.Usuario;
import developerappedida.appedida.domain.AppedidaService;
import developerappedida.appedida.util.MascaraCPF;


public class RegistrarAppedida extends BaseActivity {

    private LinearLayout tRegistrar;

    private EditText tLogin;
    private EditText tSenha;
    private EditText tEmail;
    private EditText tCpf;

    private TextWatcher cpfMask;
    private String email;
    private String login;
    private String senha;
    private String cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarappedida);

        tRegistrar = (LinearLayout) findViewById(R.id.tRegistrar);

        tRegistrar.setEnabled(false);

        tLogin = (EditText) findViewById(R.id.tLogin);
        tSenha = (EditText) findViewById(R.id.tSenha);
        tEmail = (EditText) findViewById(R.id.tEmail);

        tCpf = (EditText) findViewById(R.id.tCpf);
        cpfMask = MascaraCPF.insert("###.###.###-##", tCpf);
        tCpf.addTextChangedListener(cpfMask);

        TextWatcher watch = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int a, int b, int c) {
                registrarButtonState();
            }
        };

        tSenha.addTextChangedListener(watch);
        tCpf.addTextChangedListener(watch);

        tRegistrar.setOnClickListener(registrarUsuario());
    }

    private void registrarButtonState() {

        login = tLogin.getText().toString();
        email = tEmail.getText().toString();
        senha = tSenha.getText().toString();
        cpf = tCpf.getText().toString();

        if (StringUtils.isNotEmpty(login) && StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(cpf) && StringUtils.isNotEmpty(senha)){
            tRegistrar.setEnabled(true);
        } else {
            tRegistrar.setEnabled(false);
        }
    }

    private View.OnClickListener registrarUsuario() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login = tLogin.getText().toString();
                email = tEmail.getText().toString();
                senha = tSenha.getText().toString();
                cpf = tCpf.getText().toString();

                if(login.length()>4) {
                    if(senha.length()>5) {
                        if(email.contains("@") && email.contains(".com")) {
                            if(cpf.length()>10) {
                                if (setNewUser()) {
                                    startTask(taskCreateUsuario(), R.id.progress);
                                }
                            }else{
                                toast(R.string.erro_cpf);
                            }
                        }else{
                            toast(R.string.erro_email);
                        }
                    }else{
                        toast(R.string.erro_senha);
                    }
                }else{
                    toast(R.string.erro_login);
                }
            }
        };
    }

    private Task taskCreateUsuario() {

        return new BaseTask() {

            boolean criouUsuario = false;
            @Override
            public void execute() throws Exception {
                criouUsuario = AppedidaService.CreateUsuario();
            }

            @Override
            public void updateView() {
                if(criouUsuario){
                    toast(getString(R.string.usuario_cadastrado));
                    show(MenuAppedida.class);
                }else{
                    toast(R.string.erro_criar_usuario);
                }
            }
        };
    }

    private boolean setNewUser() {
        Usuario usuario = new Usuario();
        usuario.setLogin(tLogin.getText().toString());
        usuario.setSenha(tSenha.getText().toString());
        usuario.setEmail(tEmail.getText().toString());
        usuario.setCpf(tCpf.getText().toString());
        usuario.setIsAdmin("false");

        if (AppedidaService.saveUser(getContext(), usuario)) {
            AppedidaAplication.getInstance().setUser(usuario);
            return true;
        }
        return false;
    }


}
