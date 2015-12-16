package android.muzerk20.developerhub.Adapters;

/**
 * Created by HP on 02/12/2015.
 */
import android.muzerk20.developerhub.Models.Comment;
import android.muzerk20.developerhub.R;
import android.muzerk20.developerhub.SlidingTab.Comments;
import android.muzerk20.developerhub.SlidingTab.Videos;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ListView;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    String courseId; // Store the id oh the course selected


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, String courseId) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.courseId = courseId;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        // to pass arguments(course id) to the fragment
        Bundle args = new Bundle();
        args.putString("course_id", courseId);

        if(position == 0) // if the position is 0 we are returning the First tab
        {

            Videos v = new Videos();
            v.setArguments(args);

            return v;
        }
        else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            Comments c = new Comments();
            c.setArguments(args);

            return c;
        }


    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
