package com.h.projectschool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class ListViewAdapterImages extends BaseAdapter {

    private static final String TAG = "LastLocation";
    File[]  files;
    public ArrayList<File> data = new ArrayList<File>();
    private static LayoutInflater inflater = null;

    public ListViewAdapterImages(Activity activity, ArrayList<File> files) {
        // TODO Auto-generated constructor stub
        this.data = files;
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

    public void updateList(ArrayList<File> data) {
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
            vi = inflater.inflate(R.layout.image_row, parent, false);

        Bitmap bitmap = null;
        File f = data.get(position);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView imageView = (ImageView) vi.findViewById(R.id.imageView2);
        imageView.setImageBitmap(bitmap);
        Log.d(TAG, String.valueOf(this.data));
        return vi;

    }


}