package com.h.projectschool;

import android.widget.BaseAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private static final String TAG = "LastLocation";
    Activity activity;
    public ArrayList<String> data = new ArrayList<String>();
    private static LayoutInflater inflater = null;

    public ListViewAdapter(Activity activity) {
        // TODO Auto-generated constructor stub
        this.activity = activity;
        data = InterDatabase.getDataArray(activity, "documents");
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);

    }

    public void updateList(ArrayList<String> data) {
        this.data = data;
        Log.d(TAG, String.valueOf(this.data));
        notifyDataSetChanged();
    }

    @Override

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.document_row, parent, false);
        TextView last_location_name_textview = (TextView) vi.findViewById(R.id.document_row_item);
        last_location_name_textview.setText(data.get(position));
        Log.d(TAG, String.valueOf(this.data));
        return vi;

    }


}