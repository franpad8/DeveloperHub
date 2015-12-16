package android.muzerk20.developerhub.Adapters;


import android.content.Context;
import android.content.Intent;
import android.muzerk20.developerhub.Activities.CoursesActivity;
import android.muzerk20.developerhub.Models.Category;
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
 * Created by HP on 11/11/2015.
 */
public class CustomCategoryAdapter extends ParseQueryAdapter<Category> {


    public CustomCategoryAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Category>(){


            @Override
            public ParseQuery<Category> create() {
                ParseQuery<Category> query = ParseQuery.getQuery(Category.class);
                return query;
            }
        });
    }

    @Override
    public View getItemView(Category category, View v, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(v==null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_category, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = (TextView) v.findViewById(R.id.textViewCategoryTitle);
            viewHolder.imageView = (ParseImageView) v.findViewById(R.id.imageViewCategoryPhoto);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        // Set the title of the category
        viewHolder.textViewTitle.setText(category.getName());
        // Get the category picture
        ParseFile file = category.getIcon();

        // we use picasso to squeeze it(the image) to the imageView
        if (file != null) {
            Picasso.with(getContext()).load(file.getUrl()).into(viewHolder.imageView);
        }

        return v;
    }

    public class ViewHolder {
        TextView textViewTitle;
        ParseImageView imageView;
    }


}
