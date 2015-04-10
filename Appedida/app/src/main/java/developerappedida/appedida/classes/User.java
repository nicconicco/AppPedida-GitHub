package developerappedida.appedida.classes;

import android.content.ContentValues;

import br.livroandroid.db.Entity;

/**
 * Created by mestre on 17/03/15.
 */
public class User extends Entity {


    public String login;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String senha;
    public String email;
    public String cpf;
    public String celular;



    @Override
    public ContentValues serialize() {
        ContentValues c = new ContentValues();

        c.put("_id", id);
        c.put("login", login);
        c.put("senha", senha);
        c.put("email", email);
        c.put("cpf", cpf);
        c.put("celular", celular);

        return c;
    }

    @Override
    public void deserialize(ContentValues c) {

        id = c.getAsLong("_id");
        login = c.getAsString("login");
        senha = c.getAsString("senha");
        email = c.getAsString("email");
        cpf = c.getAsString("cpf");
        celular = c.getAsString("celular");

    }

    @Override
    public String getTable() {
        return "user";
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                "login='" + senha + '\'' +
                "login='" + senha + '\'' +
                "login='" + email + '\'' +
                "senha='" + cpf + '\'' +
                "senha='" + celular + '\'' +
                '}';
    }

}
