package com.edlogiq.neurongym.neurongym;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.edlogiq.neurongym.R;
import com.gc.materialdesign.views.Slider;

/**
 * Created by DIPTI DUBEY on 1/20/2015.
 */
public class OverViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_over_view, container, false);
        //For setting action bar title in center
        //ActionBar actionBar = getActivity().getSupportActionBar();
        // getActivity().getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        // getActivity().getActionBar().setCustomView(R.layout.actionbar_title);
        ((Slider)rootView.findViewById(R.id.overall)).setValue(300);
//        ((Slider)rootView.findViewById(R.id.overall)).s;
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }
}