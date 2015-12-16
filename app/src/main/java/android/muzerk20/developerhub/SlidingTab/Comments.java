package android.muzerk20.developerhub.SlidingTab;

/**
 * Created by HP on 02/12/2015.
 */

    import android.muzerk20.developerhub.Adapters.CustomCommentAdapter;
    import android.muzerk20.developerhub.R;
    import android.os.Bundle;
    import android.support.annotation.Nullable;
    import android.support.v4.app.Fragment;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ListView;

/**
     * Created by hp1 on 21-01-2015.
     */
    public class Comments extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.comments,container,false);
            // set the list view
            ListView listView = (ListView) v.findViewById(R.id.listViewComments);
            CustomCommentAdapter adapter = new CustomCommentAdapter(v.getContext(),
                    getArguments().getString("course_id"));
            listView.setAdapter(adapter);
            return v;
        }
    }

