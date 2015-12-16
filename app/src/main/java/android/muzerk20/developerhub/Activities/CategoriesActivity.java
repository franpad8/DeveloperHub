package android.muzerk20.developerhub.Activities;

import android.content.Intent;
import android.muzerk20.developerhub.API.Api;
import android.muzerk20.developerhub.Adapters.CustomCategoryAdapter;
import android.muzerk20.developerhub.Models.Category;
import android.muzerk20.developerhub.Models.User;
import android.muzerk20.developerhub.R;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.LinkedList;
import java.util.List;


public class CategoriesActivity extends ActionBarActivity  implements ListView.OnItemClickListener{


    private Toolbar toolbar;
    private ListView listView;
    private CustomCategoryAdapter adapter;

    private List<Category> listCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher_screen);
        // Display current user's username
        TextView textViewUserName = (TextView) toolbar.findViewById(R.id.toolbar_textview_username);
        textViewUserName.setText(ParseUser.getCurrentUser().getUsername());

        listView = (ListView) findViewById(R.id.listViewCategory);
        adapter = new CustomCategoryAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        listCategories = new LinkedList<Category>();
        listCategories = Api.getAllCategories();



        /*ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories, menu);
        menu.findItem(R.id.action_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(CategoriesActivity.this,
                        R.string.toast_logout,
                        Toast.LENGTH_SHORT).show();
                // Logout current user
                User currentUser = (User) ParseUser.getCurrentUser();
                currentUser.logOut();
                Intent intent = new Intent(CategoriesActivity.this, DispatchActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(CategoriesActivity.this, CoursesActivity.class);
        intent.putExtra("category_id", listCategories.get(position).getId());
        intent.putExtra("category_title", listCategories.get(position).getName());

        startActivity(intent);

    }
}
