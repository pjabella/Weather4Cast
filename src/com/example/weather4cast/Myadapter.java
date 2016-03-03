package com.example.weather4cast;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Myadapter extends BaseAdapter {

   LayoutInflater inflater;
   ArrayList<student> list;
   Context context;
	
	
	public Myadapter(ArrayList<student> list, Context context) {
	this.list = list;
	this.context = context;
	this.inflater=LayoutInflater.from(context);
}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		coun handler = null;
        if (arg1==null){
            arg1 = inflater.inflate(R.layout.castlayout,null);
            handler = new coun();
            handler.mv = (ImageView) arg1.findViewById(R.id.imageView1);
            handler.t1 = (TextView) arg1.findViewById(R.id.textView1);
            handler.t2 = (TextView) arg1.findViewById(R.id.textView2);
            handler.t3 = (TextView) arg1.findViewById(R.id.textView3);
            arg1.setTag(handler);
        }
            else handler = (coun) arg1.getTag();

        handler.mv.setImageResource(list.get(arg0).getImage());
        handler.t1.setText(list.get(arg0).getName());
        handler.t2.setText(list.get(arg0).getTemp());
        handler.t3.setText(list.get(arg0).getCoor());
        return arg1;
	}
	  static class coun{
	        ImageView mv;
	        TextView t1,t2,t3;


	    }
}
