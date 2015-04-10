package developerappedida.appedida.classes;

import developerappedida.appedida.activity.BaseActivity;

public class Pedido extends BaseActivity {


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    private String nome;
    private String preco;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    public Pedido(String nome, String preco, boolean checked) {
        this.nome = nome;
        this.preco = preco;
        this.checked = checked;
    }
}
