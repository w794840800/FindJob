import android.app.Application;
import android.content.Context;

/**
 * Created by wanglei on 18-3-24.
 */

public class BaseApplication extends Application {

   public static Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();
     context = this;
    }
    public static Context getBaseApplicationContext(){

        return context;

    }
}
