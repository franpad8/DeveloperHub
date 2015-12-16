package android.muzerk20.developerhub.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by HP on 11/11/2015.
 */

@ParseClassName("Score")
public class Score extends ParseObject{

    private String id;
    private User owner;
    private Course course;
    private float score;

    public String getId() {
        return getObjectId();
    }


    public User getOwner() {
        return (User)getParseObject("owner");
    }

    public void setOwner(User owner) {
        put("owner", owner);
    }

    public float getScore() {
        return (float)getDouble("score");
    }

    public void setScore(float score) {
        put("score", score);
    }

    public Course getCourse() {
        return (Course)getParseObject("course");
    }

    public void setCourse(Course course) {
        put("course", course);
    }
}
