package android.muzerk20.developerhub.Activities;

import android.content.Intent;
import android.muzerk20.developerhub.Adapters.ViewPagerAdapter;
import android.muzerk20.developerhub.SlidingTab.SlidingTabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.muzerk20.developerhub.R;

public class VideosAndCommentsActivity extends ActionBarActivity {

    Toolbar toolbar;

    // Variables for the SlidingTab logic
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Videos","Comentarios"};
    int Numboftabs =2;

    // The next 2 variables will hold selected course info
    String courseId;
    String courseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_and_comments);

        // Retrieve selected course info from the intent
        Intent intent = getIntent();
        String courseId = intent.getExtras().getString("course_id");
        String courseTitle = intent.getExtras().getString("course_title");

        // Set the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(courseTitle);
        toolbar.setLogo(R.mipmap.ic_launcher_screen);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs, courseId);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

}
