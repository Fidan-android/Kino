package com.example.kino.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kino.R;

import java.util.List;

public class TheatreAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private List<String[]> lines;

    public TheatreAdapter(Context context, List<String[]> strings) {
        ctx = context;
        lines = strings;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lines.size();
    }

    @Override
    public Object getItem(int position) {
        return lines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.theatre_cell, parent, false);
        }
        ;
        String[] p = (String[]) getItem(position);
        ((TextView) view.findViewById(R.id.tvText)).setText(p[0]);
        ((TextView) view.findViewById(R.id.tvText1)).setText(p[1]);

        return view;
    }

    ;

}
