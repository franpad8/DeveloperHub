package android.muzerk20.developerhub.Adapters;

import android.content.Context;
import android.muzerk20.developerhub.Models.Course;
import android.muzerk20.developerhub.Models.Video;
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
 * Created by HP on 03/12/2015.
 */
public class CustomVideoAdapter extends  ParseQueryAdapter<Video> {

    public CustomVideoAdapter(Context context, final String courseId) {
        super(context, new ParseQueryAdapter.QueryFactory<Video>(){
            @Override
            public ParseQuery<Video> create(){
                ParseQuery<Video> query = Video.getQuery();
                ParseObject obj = ParseObject.createWithoutData("Course", courseId);
                query.whereEqualTo("course", obj);
                return query;

            }
        });
    }

    @Override
    public View getItemView(Video video, View v, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(v==null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_video, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = (TextView) v.findViewById(R.id.textViewTitle);
            viewHolder.textViewDuration = (TextView) v.findViewById(R.id.textViewDuration);
            viewHolder.imageView = (ParseImageView) v.findViewById(R.id.imageViewPhoto);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        // set the text views with the video info
        viewHolder.textViewTitle.setText(video.getTitle());
        viewHolder.textViewDuration.setText(Integer.toString(video.getDuration()));


        ParseFile file = video.getPhoto();

        if (file != null){
            Picasso.with(getContext()).load(file.getUrl()).into(viewHolder.imageView);
        }
        return v;
    }

    public class ViewHolder {
        TextView textViewTitle;
        TextView textViewDuration;
        ParseImageView imageView;
    }
}
