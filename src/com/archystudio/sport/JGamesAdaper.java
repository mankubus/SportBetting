package com.archystudio.sport;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class JGamesAdaper extends BaseAdapter {
	 
    ArrayList<Object> data = new ArrayList<Object>();
    Context context;
 
    public JGamesAdaper(Context context, ArrayList<Object> arr) {
        if (arr != null) {
            data = arr;
        }
        this.context = context;
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
 
    public View getView(int i, View someView, ViewGroup arg2) {
    	//Get inflater from CTX   	
    	LayoutInflater inflater = LayoutInflater.from(context);

    	//0 - String, 1 - Array
    	int flag = 1;
    	
    	if (data.get(i).getClass().getSimpleName().compareTo("String") == 0) flag = 0;
    	
    	int f2 = 0;
    	if (someView != null){
    		if (someView.findViewById(R.id.tvGCapt) == null) f2=1;
    		if (someView.findViewById(R.id.tvGTime) == null) f2=2;
    	}
    	
    	if (flag == 1)
    	{
    		if ((someView == null)||(f2 == 2)) {
    			someView = inflater.inflate(R.layout.games_capt, arg2, false);
            }
    		
    		TextView gTime = (TextView) someView.findViewById(R.id.tvGTime);
    		TextView gCom1 = (TextView) someView.findViewById(R.id.tvGCom1);
    		TextView gCom2 = (TextView) someView.findViewById(R.id.tvGCom2);
    		
    		gTime.setText(DateUtil.getStrTime(((JGames)data.get(i)).StartDate));
    		gCom1.setText(((JGames)data.get(i)).HomeTeamLast);
    		gCom2.setText(((JGames)data.get(i)).AwayTeamLast);
    	}
    	else
    	{
    		if ((someView == null)||(f2==1)) {
                someView = inflater.inflate(R.layout.games_add, arg2, false);
            }
    		
    		TextView gCaptC = (TextView) someView.findViewById(R.id.tvGCapt);
    		
    		gCaptC.setText((String)data.get(i));
    	}
 
        return someView;
    }
 
}