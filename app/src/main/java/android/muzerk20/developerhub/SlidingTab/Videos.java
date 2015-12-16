package android.muzerk20.developerhub.SlidingTab;

import android.muzerk20.developerhub.Adapters.CustomCourseAdapter;
import android.muzerk20.developerhub.Adapters.CustomVideoAdapter;
import android.muzerk20.developerhub.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class Videos extends Fragment{


    private ListView listView;
    private CustomVideoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.videos,container,false);
        ListView listView = (ListView) v.findViewById(R.id.listViewVideos);
        CustomVideoAdapter adapter = new CustomVideoAdapter(v.getContext(), getArguments().getString("course_id"));
        listView.setAdapter(adapter);
        return v;
    }

}
