package developerappedida.appedida.domain;

/**
 * Created by mestre on 01/05/15.
 */
public class Produto {

    private String id_Produto;
    private String descricao;
    private String valor;
    private String data_Cadastro;
    private String nome;
    private String id_foto;
    private boolean isSelected;

    public Produto(String id_Produto, String descricao, String valor, String data_Cadastro, String nome, String id_foto) {
        this.id_Produto = id_Produto;
        this.descricao = descricao;
        this.valor = valor;
        this.data_Cadastro = data_Cadastro;
        this.nome = nome;
        this.id_foto = id_foto;
        this.isSelected = false;
    }

    public Produto(){

    }

    public String getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(String id_Produto) {
        this.id_Produto = id_Produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData_Cadastro() {
        return data_Cadastro;
    }

    public void setData_Cadastro(String data_Cadastro) {
        this.data_Cadastro = data_Cadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId_foto() {
        return id_foto;
    }

    public void setId_foto(String id_foto) {
        this.id_foto = id_foto;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
