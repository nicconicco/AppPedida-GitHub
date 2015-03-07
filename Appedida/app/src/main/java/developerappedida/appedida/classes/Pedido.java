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


    public Pedido(String nome, String preco) {
        this.nome = nome;
        this.preco = preco;
    }
}
