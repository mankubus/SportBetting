package com.archystudio.sport;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class JBooksAdapter extends BaseAdapter {
	 
    ArrayList<JBooks> data = new ArrayList<JBooks>();
    Context context;
    DrawableBackgroundDownloader drm;
    
    public JBooksAdapter(Context context, ArrayList<JBooks> arr) {
        if (arr != null) {
            data = arr;
        }
        this.context = context;
        
        drm = new DrawableBackgroundDownloader(context);
    }
 
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }
 
    public Object getItem(int num) {
        // TODO Auto-generated method stub
        return data.get(num);
    }
 
    public long getItemId(int arg0) {
        return arg0;
    }
    
    public void savedata(ArrayList<JBooks> arr)
    {   
    	if (arr != null) {
    		data = arr;
    	}
    }
 
    public View getView(int i, View someView, ViewGroup arg2) {
		// Get inflater from CTX
		LayoutInflater inflater = LayoutInflater.from(context);
		if (someView == null) {
			someView = inflater.inflate(R.layout.books_add, arg2, false);
		}

		TextView tvHome = (TextView) someView.findViewById(R.id.tvHomeMoney1);
		TextView tvAway = (TextView) someView.findViewById(R.id.tvAwayMoney1);
		ImageView imgBook = (ImageView) someView.findViewById(R.id.imgBook1);
		

		tvHome.setText(data.get(i).HomeMoney + " $");
		tvAway.setText(data.get(i).AwayMoney + " $");
		
		drm.loadDrawable(data.get(i).Logo, imgBook, null);

        return someView;
    }
 
}