package developerappedida.appedida;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.livroandroid.LivroAndroidApplication;
import br.livroandroid.db.Session;
import br.livroandroid.db.SessionFactory;
import developerappedida.appedida.domain.Produto;
import developerappedida.appedida.domain.Usuario;
import developerappedida.appedida.db.AppedidaSQLiteOpenHelper;

public class AppedidaAplication extends LivroAndroidApplication {


    private List<Produto> listProduto = new ArrayList<Produto>();

    public List<Produto> getListProdutoSelecionados() {
        return listProdutoSelecionados;
    }

    public void setListProdutoSelecionados(List<Produto> listProdutoSelecionados) {
        this.listProdutoSelecionados = listProdutoSelecionados;
    }

    private List<Produto> listProdutoSelecionados = new ArrayList<Produto>();


    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getBaseContext();
        sf = new SessionFactory(context, new AppedidaSQLiteOpenHelper(context));
        sf.configure();
    }

    private SessionFactory sf;

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    private Usuario user;

    public static AppedidaAplication getInstance() {
        return (AppedidaAplication) LivroAndroidApplication.getInstance();
    }

    public Session getSession(Context context) {
        return sf.getSession(context, "_id");
    }

    @Override
    public String getLogCatTag() {
        return "Appedida";
    }

    @Override
    public String getPrefsTag() {
        return "Appedida";
    }

    @Override
    public int getLogoAlert() {
        return 0;
    }

    @Override
    public int getProgressId() {
        return 0;
    }

    public List<Produto> getListProduto() {
        return listProduto;
    }

    public void setListProduto(List<Produto> listProduto) {
        this.listProduto = listProduto;
    }
}
