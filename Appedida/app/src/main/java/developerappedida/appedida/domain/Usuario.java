package developerappedida.appedida.domain;

import android.content.ContentValues;

import br.livroandroid.db.Entity;

/**
 * Created by mestre on 17/03/15.
 */
public class Usuario extends Entity {

    private String login;
    private String senha;
    private String email;
    private String cpf;
    private String IsAdmin;
    private int id_Usuario;

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

    @Override
    public ContentValues serialize() {
        ContentValues c = new ContentValues();

        c.put("_id", id);
        c.put("login", login);
        c.put("senha", senha);
        c.put("email", email);
        c.put("cpf", cpf);

        return c;
    }

    @Override
    public void deserialize(ContentValues c) {

        id = c.getAsLong("_id");
        login = c.getAsString("login");
        senha = c.getAsString("senha");
        email = c.getAsString("email");
        cpf = c.getAsString("cpf");

    }

    @Override
    public String getTable() {
        return "user";
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "login='" + login + '\'' +
                "login='" + senha + '\'' +
                "login='" + senha + '\'' +
                "login='" + email + '\'' +
                "senha='" + cpf + '\'' +
                '}';
    }

    public String isAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        IsAdmin = isAdmin;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }
}
