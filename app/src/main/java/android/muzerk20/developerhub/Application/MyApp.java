package android.muzerk20.developerhub.Application;

import android.app.Application;
import android.muzerk20.developerhub.Models.Category;
import android.muzerk20.developerhub.Models.Comment;
import android.muzerk20.developerhub.Models.Course;
import android.muzerk20.developerhub.Models.User;
import android.muzerk20.developerhub.Models.Video;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.PushService;

/**
 * Created by HP on 10/11/2015.
 */
public class MyApp extends Application {

    private final String APPID = "nNessDCszCeACjd9CsYxtgzWQqvIiQ60iHPkcggX";
    private final String CLIENTKEY = "q9NYzxSfa2KT0Tlhn7PxQBEIrARuTcCXBLaP7cFF";

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Category.class);
        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Course.class);
        ParseObject.registerSubclass(Video.class);
        ParseObject.registerSubclass(Comment.class);



        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, APPID, CLIENTKEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
    }
}
