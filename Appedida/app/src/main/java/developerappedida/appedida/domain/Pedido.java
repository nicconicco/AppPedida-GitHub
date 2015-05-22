package developerappedida.appedida.domain;

import android.content.ContentValues;

import br.livroandroid.db.Entity;

/**
 * Created by mestre on 22/05/2015.
 */
public class Pedido extends Entity {
    public String getIdsProdutos() {
        return idsProdutos;
    }

    public void setIdsProdutos(String idsProdutos) {
        this.idsProdutos = idsProdutos;
    }

    public String getValor_Pedido() {
        return valor_Pedido;
    }

    public void setValor_Pedido(String valor_Pedido) {
        this.valor_Pedido = valor_Pedido;
    }

    private String idsProdutos;
    private String valor_Pedido;

    public Pedido(String listaConcatenada, String precoTotal) {
        this.idsProdutos = listaConcatenada;
        this.valor_Pedido = precoTotal;
    }

    public Pedido() {

    }


    @Override
    public ContentValues serialize() {
        ContentValues c = new ContentValues();

        c.put("_id", id);
        c.put("idsProdutos", idsProdutos);
        c.put("valor_Pedido", valor_Pedido);

        return c;
    }

    @Override
    public void deserialize(ContentValues c) {

        id = c.getAsLong("_id");
        idsProdutos = c.getAsString("idsProdutos");
        valor_Pedido = c.getAsString("valor_Pedido");

    }

    @Override
    public String getTable() {
        return "pedido";
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idsProdutos='" + idsProdutos + '\'' +
                "valor_Pedido='" + valor_Pedido + '\'' +
                '}';
    }
}
