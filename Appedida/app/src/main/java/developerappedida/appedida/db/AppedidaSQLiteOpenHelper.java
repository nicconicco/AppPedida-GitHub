package developerappedida.appedida.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.livroandroid.db.BaseSQLiteOpenHelper;
import developerappedida.appedida.R;

/**
 * Created by mestre on 14/03/15.
 */

public class AppedidaSQLiteOpenHelper extends BaseSQLiteOpenHelper {

    private static final String TAG = AppedidaSQLiteOpenHelper.class.getSimpleName();
    private static final String TAG_APP = "Appedida";
    private Context context;

    public AppedidaSQLiteOpenHelper(Context context) {
        super(context, TAG_APP, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        executaScriptCriacao(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
    }

    private void executaScriptCriacao(SQLiteDatabase db) {
        Log.d(TAG, "Executando executaScriptCriacao do banco");
        execScript(db, R.raw.script_create, "UTF-8");

    }
}
