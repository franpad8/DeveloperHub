package android.muzerk20.developerhub.Models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseUser;

/**
 * Created by HP on 11/11/2015.
 */

@ParseClassName("_User")
public class User extends ParseUser {

    private String id;
    private String email;
    private String password;
    private String username;
    private boolean emailVerified;
    private ParseFile photo;

    public String getId() {
        return getObjectId();
    }

    @Override
    public void setEmail(String email) {
        put("email", email);
        put("username", email);
    }

    public void setEmailVerified(boolean emailVerified) {
        put("emailVerified", emailVerified);
    }

    public void setPhoto(ParseFile photo) {
        put("photo", photo);
    }

    @Override
    public void setUsername(String username) {
        put("name", username);
    }

    @Override
    public String getEmail() {
        return getString("email");
    }

    @Override
    public String getUsername() {
        return getString("name");
    }

    public boolean isEmailVerified() {
        return getBoolean("emailVerified");
    }

    public ParseFile getPhoto() {
        return getParseFile("photo");
    }
}
