package com.edlogiq.neurongym.neurongym;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidplot.series.XYSeries;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;
import com.edlogiq.neurongym.R;

/**
 * Created by Incarnation on 1/29/2015.
 */
public class SpeedHistory extends Fragment {



    LinearLayout chartContainer;
    Context mContext;
    //private GraphicalView mChart;
    private XYPlot xyPlot;

    final String[] mMonths = new String[] {
            "5th Jan","10th Jan", "15th Jan","20th Jan", "25th Jan","30th Jan"
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
           /*mContext=(Context) getActivity();
           openChart();*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_speed_history, container, false);



        //For setting action bar title in center
        //ActionBar actionBar = getActivity().getSupportActionBar();
        // getActivity().getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        // getActivity().getActionBar().setCustomView(R.layout.actionbar_title);

        // initialize our XYPlot reference:
        //chartContainer =(LinearLayout)rootView.findViewById(R.id.chart_container);

        xyPlot = (XYPlot) rootView.findViewById(R.id.xyplot);

        return rootView;
    }



    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        mContext=(Context) getActivity();





        Number[] income =  {0, 200, 400, 600, 800, 1000 };
        //Number[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };

        // Converting the above income array into XYSeries
        XYSeries incomeSeries = new SimpleXYSeries(
                Arrays.asList(income),          		 // array => list
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY , // Y_VALS_ONLY means use the element index as the x value
                "Income");                             	 // Title of this series

      /*  // Converting the above expense array into XYSeries
        XYSeries expenseSeries = new SimpleXYSeries(
                Arrays.asList(expense), 				// array => list
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                "Expense");								// Title of this series
*/
        // Create a formatter to format Line and Point of income series
        LineAndPointFormatter incomeFormat = new LineAndPointFormatter(
                Color.rgb(63, 81, 181),                   // line color(CYAN)
                Color.rgb(63, 81, 181),               // point color
                null );                					// fill color (none)


        /*// Create a formatter to format Line and Point of expense series
        LineAndPointFormatter expenseFormat = new LineAndPointFormatter(
                Color.rgb(255, 0, 0),                   // line color
                Color.rgb(200, 200, 200),               // point color
                null);					                // fill color (none)

*/

        // add expense series to the xyplot:
        // xyPlot.addSeries(expenseSeries,expenseFormat);

        // add income series to the xyplot:
        xyPlot.addSeries(incomeSeries, incomeFormat);


        // Formatting the Domain Values ( X-Axis )
        xyPlot.setDomainValueFormat(new Format() {

            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                return new StringBuffer( mMonths[ ( (Number)obj).intValue() ]  );
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });

        xyPlot.setDomainLabel("");
        xyPlot.setRangeLabel("Average Neuron Index(ANI)");

        // Increment X-Axis by 1 value
        xyPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);

        xyPlot.getGraphWidget().setRangeLabelWidth(50);

        // Reduce the number of range labels
        xyPlot.setTicksPerRangeLabel(2);

        // Reduce the number of domain labels
        xyPlot.setTicksPerDomainLabel(2);

        // Remove all the developer guides from the chart
        xyPlot.disableAllMarkup();
    }


      /*  //Calling New AddGrouopFragment on Button click to Add New Group
        edit_group.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Activity activity = getActivity();
                Fragment mFragment1 = new EditGroupFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, mFragment1)
                        .addToBackStack("")
                        .commit();


                // System.out.println("good");

            }
        });*/


}


