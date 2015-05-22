package com.edlogiq.neurongym.neurongym;

/**
 * Created by infoincarnation on 2/17/2015.
 */


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.edlogiq.neurongym.R;


public class CustomAdapter extends BaseAdapter {
    Context context;
    List<RowItem> rowItems;

    public CustomAdapter(Context context, List<RowItem> items) {
        this.context = context;
        this.rowItems = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.game_menu_view, null);
            holder = new ViewHolder();
            holder.txtSub = (TextView) convertView.findViewById(R.id.textView2);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.textView1);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
            holder.imageView3 = (ImageView) convertView.findViewById(R.id.imageView3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RowItem rowItem = (RowItem) getItem(position);

        holder.txtSub.setText(rowItem.getDesc());
        holder.txtTitle.setText(rowItem.getTitle());
        holder.imageView.setImageResource(rowItem.getImageId());
        holder.imageView2.setImageResource(rowItem.getsubImage());

        return convertView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        ImageView imageView2;
        ImageView imageView3;
        TextView txtTitle;
        TextView txtSub;
    }
}