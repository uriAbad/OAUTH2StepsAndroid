package uriabad.oauth2weblibrary;

import android.app.Application;
import android.content.Context;

/**
 * Created by Uri Abad on 08/09/16.
 * Seidor S.A.
 * oabad@seidor.es
 */
public class OAUTH2weblibrary extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        OAUTH2weblibrary.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return context;
    }
}
