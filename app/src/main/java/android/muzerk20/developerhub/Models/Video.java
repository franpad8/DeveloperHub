package android.muzerk20.developerhub.Models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by HP on 11/11/2015.
 */
@ParseClassName("Video")
public class Video extends ParseObject {

    private String id;
    private String title;
    private String code;
    private Course course;
    private ParseFile photo;
    private int lesson;
    private int duration;

    public String getId() {
        return getObjectId();
    }

    public String getTitle() {
        return getString("title");
    }

    public Course getCourse() {
        return (Course)getParseObject("course");
    }

    public String getCode() {
        return getString("code");
    }

    public ParseFile getPhoto() {
        return getParseFile("photo");
    }

    public int getLesson() {
        return (int)getNumber("lesson");
    }

    public int getDuration() {
        return (int)getNumber("duration");
    }

    public static ParseQuery<Video> getQuery() { return ParseQuery.getQuery(Video.class); }
}
