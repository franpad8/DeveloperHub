package android.muzerk20.developerhub.Activities;

import android.app.Activity;
import android.content.Intent;
import android.muzerk20.developerhub.API.Api;
import android.muzerk20.developerhub.Adapters.CustomCategoryAdapter;
import android.muzerk20.developerhub.Adapters.CustomCourseAdapter;
import android.muzerk20.developerhub.Models.Course;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.muzerk20.developerhub.R;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.LinkedList;
import java.util.List;

public class CoursesActivity extends ActionBarActivity implements ListView.OnItemClickListener {

    private Toolbar toolbar;
    private ListView listView;
    private CustomCourseAdapter adapter;
    private String categoryId;
    private String categoryTitle;
    private List<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        // Getting the name of the category where this course belongs from the intent
        Intent intent = getIntent();
        categoryId = intent.getExtras().getString("category_id");
        categoryTitle = intent.getExtras().getString("category_title");

        // Setting the tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(categoryTitle);
        toolbar.setLogo(R.mipmap.ic_launcher_screen);

        // Display current user's username
        TextView textViewUserName = (TextView) toolbar.findViewById(R.id.toolbar_textview_username);
        textViewUserName.setText(ParseUser.getCurrentUser().getUsername());


        listView = (ListView) findViewById(R.id.listViewCourses);
        adapter = new CustomCourseAdapter(this, categoryId);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


        // Obtain all the courses of the category selected
        courses = new LinkedList<>();
        courses = Api.getCourses(categoryId);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        Intent intent = new Intent(CoursesActivity.this, VideosAndCommentsActivity.class);
        intent.putExtra("course_title", courses.get(pos).getTitle());
        intent.putExtra("course_id", courses.get(pos).getId());
        startActivity(intent);
    }
}
