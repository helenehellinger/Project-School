package com.h.projectschool;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class InterDatabase {

    static String TAG = "InterDatabase";

    public static String getData(Activity activity, String name) {
        TinyDB tinydb = new TinyDB(activity);
        String list = tinydb.getString(name);
        Log.d(TAG, "getData " + list);
        return list;
    }

    public static ArrayList<String> getDataArray(Activity activity, String name) {
        TinyDB tinydb = new TinyDB(activity);
        ArrayList<String> list = tinydb.getListString(name);
        //Collections.reverse(list);
        Log.d(TAG, list.toString());
        return list;
    }

    public static void saveDataString(Activity activity, String name, String data) {
        TinyDB tinydb = new TinyDB(activity);
        Log.d(TAG, "saveDataString " + data);
        tinydb.putString(name, data);
    }

    public static void saveData(Activity activity, String name, String data) {
        TinyDB tinydb = new TinyDB(activity);
        ArrayList<String> arrayList = tinydb.getListString(name);
        Log.d(TAG, arrayList.toString());
        Log.d(TAG, "saveData " + data);
        if (!arrayList.contains(data)) {
            arrayList.add(0, data);
            tinydb.putListString(name, arrayList);
        } else {
            arrayList.remove(data);
            arrayList.add(0, data);
            tinydb.putListString(name, arrayList);
        }
    }

}