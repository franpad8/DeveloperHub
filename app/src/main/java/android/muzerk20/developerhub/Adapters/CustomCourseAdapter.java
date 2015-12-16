package android.muzerk20.developerhub.Adapters;

import android.content.Context;
import android.muzerk20.developerhub.Models.Course;
import android.muzerk20.developerhub.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.CountCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by HP on 17/11/2015.
 */
public class CustomCourseAdapter extends ParseQueryAdapter<Course> {

    public CustomCourseAdapter(Context context, final String categoryId) {
        super(context,  new ParseQueryAdapter.QueryFactory<Course>(){

            @Override
            public ParseQuery<Course> create() {
                ParseQuery<Course> query = ParseQuery.getQuery(Course.class);
                ParseObject obj = ParseObject.createWithoutData("Category", categoryId);
                query.whereEqualTo("category", obj);
                return query;
            }
        });
    }

    @Override
    public View getItemView(Course course, View v, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(v==null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_course, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = (TextView) v.findViewById(R.id.textViewCourseTitle);
            viewHolder.textViewInfo = (TextView) v.findViewById(R.id.textViewCourseDetails);
            viewHolder.imageView = (ParseImageView) v.findViewById(R.id.imageViewCoursePhoto);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }



        viewHolder.textViewTitle.setText(course.getTitle());

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Video");
        query.whereEqualTo("course", course);
        query.countInBackground(new CountCallback() {
            @Override
            public void done(int count, ParseException e) {
                if(e == null) {
                    viewHolder.textViewInfo.setText(count + " videos");
                } else {
                    viewHolder.textViewInfo.setText("¿? video");
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        ParseFile file = course.getPhoto();

        if (file != null){
            Picasso.with(getContext()).load(file.getUrl()).into(viewHolder.imageView);
        }
        return v;
    }

    public class ViewHolder {
        TextView textViewTitle;
        TextView textViewInfo;
        ParseImageView imageView;
    }
}
