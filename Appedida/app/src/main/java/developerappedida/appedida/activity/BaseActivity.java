package developerappedida.appedida.activity;

import android.os.Bundle;
import android.view.Window;

import br.livroandroid.actionbar.compat.utils.LivroAndroidActionBarActivity;

public class BaseActivity extends LivroAndroidActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

    }
}
