package android.muzerk20.developerhub.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by HP on 11/11/2015.
 */

@ParseClassName("Comment")
public class Comment extends ParseObject {

    private String content;
    private Course course;
    private ParseUser owner;

    public String getContent() {
        return getString("content");
    }

    public void setContent(String content) {
        put("content", content);
    }

    public Course getCourse() {
        return (Course) getParseObject("course");
    }

    public void setCourse(Course course) {
        put("course", course);
    }

    public ParseUser getOwner() {
        return (User)getParseObject("owner");
    }

    public void setOwner(ParseUser owner) {
        put("owner", owner);
    }

    public static ParseQuery<Comment> getQuery() { return ParseQuery.getQuery(Comment.class); }
}
