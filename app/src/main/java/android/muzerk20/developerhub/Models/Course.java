package android.muzerk20.developerhub.Models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by HP on 11/11/2015.
 */
@ParseClassName("Course")
public class Course extends ParseObject{

    private String id;
    private String title;
    private Category category;
    private User owner;
    private ParseFile photo;
    private ParseFile icon;
    private boolean isValid;

    public String getId() {
        return getObjectId();
    }

    public String getTitle() {
        return getString("title");
    }

    public Category getCategory() {
        return (Category) getParseObject("category");
    }

    public User getOwner() {
        return (User) getParseObject("owner");
    }

    public ParseFile getPhoto() {
        return getParseFile("photo");
    }

    public boolean isValid() {
        return getBoolean("isValid");
    }

    public static ParseQuery<Course> getQuery(){
        return ParseQuery.getQuery(Course.class);
    }
}
