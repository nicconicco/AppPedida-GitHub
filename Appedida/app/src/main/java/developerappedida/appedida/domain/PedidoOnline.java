package developerappedida.appedida.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicolaugalves on 6/23/15.
 */
public class PedidoOnline {

    private String idPedido;
    private String valorPedido;
    private String idStatusPedido;
    private List<Produto> listProdutos = new ArrayList<Produto>();


    public PedidoOnline() {

    }

    public PedidoOnline(String idPedido, String valorPedido, String idStatusPedido, List<Produto> listProdutos) {

        this.idPedido = idPedido;
        this.valorPedido = valorPedido;
        this.idStatusPedido = idStatusPedido;
        this.listProdutos = listProdutos;
    }


    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(String valorPedido) {
        this.valorPedido = valorPedido;
    }

    public String getIdStatusPedido() {
        return idStatusPedido;
    }

    public void setIdStatusPedido(String idStatusPedido) {
        this.idStatusPedido = idStatusPedido;
    }

    public List<Produto> getListProdutos() {
        return listProdutos;
    }

    public void setListProdutos(List<Produto> listProdutos) {
        this.listProdutos = listProdutos;
    }
}
