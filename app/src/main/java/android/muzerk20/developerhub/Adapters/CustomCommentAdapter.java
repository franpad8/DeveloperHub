package android.muzerk20.developerhub.Adapters;

import android.content.Context;
import android.muzerk20.developerhub.Models.Comment;
import android.muzerk20.developerhub.Models.Video;
import android.muzerk20.developerhub.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by HP on 03/12/2015.
 */
public class CustomCommentAdapter extends ParseQueryAdapter<Comment> {

    public CustomCommentAdapter(Context context, final String courseId) {
        super(context, new ParseQueryAdapter.QueryFactory<Comment>(){

            @Override
            public ParseQuery<Comment> create() {
                ParseQuery<Comment> query = Comment.getQuery();
                ParseObject obj = ParseObject.createWithoutData("Course", courseId);
                query.whereEqualTo("course", obj);
                return query;
            }
        });
    }

    @Override
    public View getItemView(Comment comment, View v, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(v==null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_comment, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewComment = (TextView) v.findViewById(R.id.textViewComment);
            viewHolder.textViewOwner = (TextView) v.findViewById(R.id.textViewOwner);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        // set the text views with the video info
        viewHolder.textViewComment.setText(comment.getContent());
        String username = "";
        try {
            username = comment.getOwner().fetchIfNeeded().getUsername();

        } catch (ParseException e) {
            Log.v("Error:fetch the user", e.toString());
            e.printStackTrace();
        }


        viewHolder.textViewOwner.setText(comment.getOwner().getUsername());

        return v;

    }

    public class ViewHolder {
        TextView textViewComment;
        TextView textViewOwner;
    }


}
