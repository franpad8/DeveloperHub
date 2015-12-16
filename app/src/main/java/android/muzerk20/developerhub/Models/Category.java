package android.muzerk20.developerhub.Models;

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by HP on 11/11/2015.
 */
@ParseClassName("Category")
public class Category extends ParseObject {

    private String id;
    private String name;
    private ParseFile icon;
    private String color;

    public String getId() {
        return getObjectId();
    }

    public String getName() {
        return getString("name");
    }

    public ParseFile getIcon() {
        return getParseFile("icon");
    }

    public String getColor() {
        return getString("color");
    }

    public static ParseQuery<Category> getQuery() { return ParseQuery.getQuery(Category.class); }
}
