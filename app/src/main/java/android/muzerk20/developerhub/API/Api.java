package android.muzerk20.developerhub.API;

import android.content.Context;
import android.muzerk20.developerhub.Models.Category;
import android.muzerk20.developerhub.Models.Course;
import android.muzerk20.developerhub.R;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.CountCallback;
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
        final List<Category> list = new LinkedList<>();

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

    /** Get the number of courses that belongs to a certain category
     *  and set it to a textView
     *  @param  context: context in which this method has been called
     *  @param  categoryId: string that contains the category's id
     *  @param  details: TextView where the number of courses will be displayed
     *
     */
    public static void showNumberOfCoursesByCategory(final Context context, final String categoryId,
                                                    final TextView details){
        ParseQuery<Course> query = Course.getQuery();
        final ParseObject cat = ParseObject.createWithoutData("Category", categoryId);
        query.whereEqualTo("category", cat);
        query.whereEqualTo("isValid", true);
        try {
            final int number = query.count();
            final String text = (number == 1) ? number + " curso" : number + " cursos";
            details.setText(text);
        } catch (ParseException e) {
            Toast.makeText(context, context.getString(R.string.error_restart), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * Returns all the courses that belongs to a certain category
     *   @param categoryId: string with the id of the category
     *
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
                } else {
                    for(Course c: objects)
                        courses.add(c);

                }
            }
        });

        return courses;
    }
}
