package com.example.sujanhasan.listviewdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    String[] period;
    ArrayList<String>cource;
    Context context;
    private LayoutInflater inflater;

    CustomAdapter(Context context,String[] period,ArrayList<String>cource){
        this.context=context;
        this.period=period;
        this.cource=cource;
    }

    @Override
    public int getCount() {
        return period.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.sample_view,parent,false);
        }
        TextView periodTextView=convertView.findViewById(R.id.periodTextViewId);
        TextView courceTextView=convertView.findViewById(R.id.courceTextViewId);

        periodTextView.setText(period[position]);
        courceTextView.setText(cource.get(position));

        return convertView;
    }
}
