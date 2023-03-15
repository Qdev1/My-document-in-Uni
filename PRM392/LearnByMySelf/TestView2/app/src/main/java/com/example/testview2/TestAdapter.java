package com.example.testview2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TestAdapter extends BaseAdapter {

    private Activity activity;
    private String [] items;
    public TestAdapter (Activity activity, String [] items){
        this.activity = activity;
        this.items=items;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.items_name,null);

        TextView tvName = (TextView) view.findViewById(R.id.textview_name);
        tvName.setText(items[i]);
        //  return null;
        return view;
    }
}
