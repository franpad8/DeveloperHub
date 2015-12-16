package android.muzerk20.developerhub.API;

import android.muzerk20.developerhub.Models.Category;
import android.muzerk20.developerhub.Models.Course;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HP on 02/12/2015.
 *
 * Class where all the querys requests to Parse database are done
 */

public class Api {

    // Returns all the Category objects found in the database in a LinkedList
    public static List<Category> getAllCategories(){
        final List<Category> list = new LinkedList<Category>();

        ParseQuery<Category> query = Category.getQuery();
        // query all the categories
        query.findInBackground(new FindCallback<Category>() {
            @Override
            public void done(List<Category> objects, ParseException e) {
                // If there are not any errors
                if(e == null) {
                    // copy the received list
                    for (Category cat : objects)
                        list.add(cat);
                }
            }
        });
        // returns all categories
        return list;
    }

    /*
        Returns all the courses that belongs to a certain category
        @param categoryId: string with the id of the category
      */
    public static List<Course> getCourses(String categoryId){
        final List<Course> courses = new LinkedList<>();
        ParseQuery<Course> query = Course.getQuery();
        final ParseObject obj = ParseObject.createWithoutData("Category", categoryId);
        query.whereEqualTo("category", obj);
        query.findInBackground(new FindCallback<Course>() {
            @Override
            public void done(List<Course> objects, ParseException e) {
                if (e != null) {
                    Log.e("Error: CourseActivity", e.getMessage());
                    return;
                } else {
                    for(Course c: objects)
                        courses.add(c);

                }
            }
        });

        return courses;
    }
}
