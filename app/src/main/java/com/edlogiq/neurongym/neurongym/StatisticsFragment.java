package com.edlogiq.neurongym.neurongym;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.edlogiq.neurongym.R;


/**
 * Created by Rajiv Kumar on 1/20/2015.
 */
public class StatisticsFragment  extends Fragment implements View.OnClickListener {

    Button btn_attention, btn_memory,btn_flexibility,btn_problem, btn_speed, btn_visual,btn_overall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);

        btn_overall = (Button) rootView.findViewById(R.id.st_overall);
        btn_attention = (Button) rootView.findViewById(R.id.st_attention);
        btn_memory = (Button) rootView.findViewById(R.id.st_memory);
        btn_flexibility = (Button) rootView.findViewById(R.id.st_flexbility);
        btn_problem = (Button) rootView.findViewById(R.id.st_problem);
        btn_speed = (Button) rootView.findViewById(R.id.st_speed);
        btn_visual = (Button) rootView.findViewById(R.id.st_visual);

        btn_overall.setOnClickListener(this);
        btn_attention.setOnClickListener(this);
        btn_memory.setOnClickListener(this);
        btn_flexibility.setOnClickListener(this);
        btn_speed.setOnClickListener(this);
        btn_problem.setOnClickListener(this);
        btn_visual.setOnClickListener(this);

        btn_overall.setBackgroundResource(R.color.cyan);

//For setting action bar title in center
//        ActionBar actionBar = getActivity().getSupportActionBar();
//        getActivity().getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getActivity().getActionBar().setCustomView(R.layout.actionbar_title);
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
    if(v.getId()==R.id.st_overall){
        selector("overall");
    }else if(v.getId()==R.id.st_attention){
        selector("attention");
    }else if(v.getId()==R.id.st_memory){
        selector("memory");
    }else if(v.getId()==R.id.st_flexbility){
        selector("flexibility");
    }else if(v.getId()==R.id.st_problem){
        selector("problem");
    }else if(v.getId()==R.id.st_speed){
        selector("speed");
    }else if(v.getId()==R.id.st_visual){
        selector("visual");
    }
    }

    private void selector(String btn_select){
        if(btn_select.equals("attention")){
            btn_attention.setBackgroundResource(R.color.cyan);
        }else{
            btn_attention.setBackgroundResource(R.color.gray);
        }
        if(btn_select.equals("memory")){
            btn_memory.setBackgroundResource(R.color.cyan);
        }else{
            btn_memory.setBackgroundResource(R.color.gray);
        } if(btn_select.equals("flexibility")){
            btn_flexibility.setBackgroundResource(R.color.cyan);
        }else{
            btn_flexibility.setBackgroundResource(R.color.gray);
        } if(btn_select.equals("problem")){
            btn_problem.setBackgroundResource(R.color.cyan);
        }else{
            btn_problem.setBackgroundResource(R.color.gray);
        } if(btn_select.equals("speed")){
            btn_speed.setBackgroundResource(R.color.cyan);
        }else{
            btn_speed.setBackgroundResource(R.color.gray);
        }if(btn_select.equals("visual")){
            btn_visual.setBackgroundResource(R.color.cyan);
        }else{
            btn_visual.setBackgroundResource(R.color.gray);
        }if(btn_select.equals("overall")){
            btn_overall.setBackgroundResource(R.color.cyan);
        }else{
            btn_overall.setBackgroundResource(R.color.gray);
        }
    }


}


