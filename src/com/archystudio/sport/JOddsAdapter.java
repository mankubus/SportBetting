package com.archystudio.sport;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class JOddsAdapter extends BaseAdapter {
	ArrayList<JOdds> data = new ArrayList<JOdds>();
    Context context;
    DrawableBackgroundDownloader drm;
 
    public JOddsAdapter(Context context, ArrayList<JOdds> arr) {
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
    
    private String IntToStrI(int i)
    {
    	if (i < 0) return ""+i;
    	if (i == 0) return "";
    	return "+"+i;
    	
    }
    
    private String IntToStrI(double i)
    {
    	if (i < 0) return ""+i;
    	if (i == 0) return "";
    	return "+"+i;
    	
    }
 
    
	public View getView(int i, View someView, ViewGroup arg2) {
		// Get inflater from CTX
		LayoutInflater inflater = LayoutInflater.from(context);

		if (someView == null) {
			someView = inflater.inflate(R.layout.odds_add, arg2, false);
		}

		TextView tv_ML1 = (TextView) someView.findViewById(R.id.tv_ML1);
		TextView tv_ML2 = (TextView) someView.findViewById(R.id.tv_ML2);

		TextView tv_TL1 = (TextView) someView.findViewById(R.id.tv_TL1);
		TextView tv_TL2 = (TextView) someView.findViewById(R.id.tv_TL2);
		TextView tv_TL3 = (TextView) someView.findViewById(R.id.tv_TL3);
		TextView tv_TL4 = (TextView) someView.findViewById(R.id.tv_TL4);

		TextView tv_Total1 = (TextView) someView.findViewById(R.id.tv_Total1);
		TextView tv_Total2 = (TextView) someView.findViewById(R.id.tv_Total2);
		TextView tv_Total3 = (TextView) someView.findViewById(R.id.tv_Total3);
		TextView tv_Total4 = (TextView) someView.findViewById(R.id.tv_Total4);

		tv_ML1.setText(IntToStrI(data.get(i).ML_Home));
		tv_ML2.setText(IntToStrI(data.get(i).ML_Away));

		tv_TL1.setText(IntToStrI(data.get(i).SL_HomeMoney));
		tv_TL2.setText(IntToStrI(data.get(i).SL_AwayMoney));
		tv_TL3.setText(IntToStrI(data.get(i).SL_HomeLine));
		tv_TL4.setText(IntToStrI(data.get(i).SL_AwayLine));

		tv_Total1.setText(IntToStrI(data.get(i).Total_HomeMoney));
		tv_Total2.setText(IntToStrI(data.get(i).Total_AwayMoney));
		tv_Total3.setText(IntToStrI(data.get(i).Total_TotalLine));
		tv_Total4.setText(IntToStrI(data.get(i).Total_TotalLine));

		ImageView imgBook = (ImageView) someView.findViewById(R.id.imgBook);

		drm.loadDrawable(data.get(i).SportBookLogo, imgBook, null);

		return someView;
    }
 
}