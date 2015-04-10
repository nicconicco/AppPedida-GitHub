package developerappedida.appedida;

import android.content.Context;

import br.livroandroid.LivroAndroidApplication;
import br.livroandroid.db.Session;
import br.livroandroid.db.SessionFactory;
import developerappedida.appedida.classes.User;
import developerappedida.appedida.db.AppedidaSQLiteOpenHelper;

public class AppedidaAplication extends LivroAndroidApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getBaseContext();
        sf = new SessionFactory(context, new AppedidaSQLiteOpenHelper(context));
        sf.configure();
    }

    private SessionFactory sf;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

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
}
