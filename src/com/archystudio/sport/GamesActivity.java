package com.archystudio.sport;

import java.util.ArrayList;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class GamesActivity extends ExtActivity{
	private TreeMap<String, ArrayList<JGames>> dict = null;
	private ArrayList<Object> items = null;

	private ListView lvGames;
	
	ProgressTask backTask = null; 
	private ProgressDialog dialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games);
        
        lvGames = (ListView) findViewById(R.id.lvGames);
        
        if (savedInstanceState != null)
        {
        	if (MainActivity._jsonG != null)
        	{
        		try {
					this.onReceive(1);
				} catch (JSONException e) {
					e.printStackTrace();
				}
        		return;
        	}
        }
        
        //for progress bar
        startBackgroundTask();
	}
	
	//Startting Progressbar
	private void startBackgroundTask() {
        if (getLastNonConfigurationInstance() != null) {
            backTask = (ProgressTask) getLastNonConfigurationInstance();
            backTask.attach(this);
        }
		
        backTask = new ProgressTask(GamesActivity.this);
		backTask.execute();
	}
	
	//Get old ProgressBar
	 public Object onRetainNonConfigurationInstance() {
		    if (backTask != null && backTask.getStatus() != Status.FINISHED)
		      return backTask;
		    return null;
	 }
	
	//processing JSON
	private void onReceive(int flag) throws JSONException
	{	
		items = new ArrayList<Object>();
		
		if (flag == 1)
		{
			//json Object
			JSONArray jsonRoot = new JSONArray(MainActivity._jsonG);
			
			//hashmap [date->array(class)]
			dict = new TreeMap<String, ArrayList<JGames>>();
			
			//array parsing
			for (int i = 0; i < jsonRoot.length(); i++) {
				JSONObject jsonObject = jsonRoot.getJSONObject(i);
				JGames _jg = new JGames(jsonObject.getString("StartDate"));
				
				_jg.AwayTeamFirst = jsonObject.getString("AwayTeamFirst");
				_jg.AwayTeamLast = jsonObject.getString("AwayTeamLast");
				_jg.GameID = jsonObject.getInt("GameID");
				_jg.HomeTeamFirst = jsonObject.getString("HomeTeamFirst");
				_jg.HomeTeamLast = jsonObject.getString("HomeTeamLast");
				
				String _shortDate = DateUtil.getStrDate(_jg.StartDate);
	
				if (dict.get(_shortDate) == null)
				//new array
				{
					dict.put(_shortDate, new ArrayList<JGames>());
				}
				
				//add class to array
				dict.get(_shortDate).add(_jg);
			}
			
			//here is visualizing
			for (TreeMap.Entry<String, ArrayList<JGames>> entry : dict.entrySet()) {
			    items.add(entry.getKey());
			    ArrayList<JGames> a = entry.getValue();
			    for(int i=0; i < a.size(); i++)
			    {
			    	items.add(a.get(i));
			    }
			}
		}
		else
		{
			items.add("NO GAMES");
		}
		
		//in @items@ - our games
		this.lvGames.setAdapter(new JGamesAdaper(this,items));
		
		//onClick
		this.lvGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
  	      public void onItemClick(AdapterView<?> av, View view, int index, long arg3) {
  	    	  if (av.getItemAtPosition(index).getClass().getSimpleName().compareTo("String") != 0)
  	    	  {
  	    		  JGames a = (JGames) av.getItemAtPosition(index);
  	    		  MainActivity._Game = a;
  	    		  
  	    	  	  Intent intent = new Intent( "com.archystudio.sport.OddsActivityAct");
  	    	  	  startActivity(intent);
  	    	  }
  	      }
    	});
		
	}
	
	//Progress bar and server answ.
	private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private GamesActivity activity;
        
        public ProgressTask(GamesActivity activity) {
            super();
        	this.activity = activity;
        }
        
        public void attach(GamesActivity a) {
            activity = a;
          }
        
        protected void onPreExecute() {
        	this.activity.dialog = new ProgressDialog(this.activity);
        	this.activity.dialog.setMessage("Wait...");
        	this.activity.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
        	super.onPostExecute(success);
	        if (dialog.isShowing()) 
	        {
	        	this.activity.dialog.dismiss();
	        }

			if (!success) {
				// Error
				Toast.makeText(this.activity, "Connection error",
						Toast.LENGTH_LONG).show();
			} else {
				// Normal answer
				int flg = 0;
				if (MainActivity._jsonG != "")
					flg = 1;

				try {
					this.activity.onReceive(flg);
				} catch (JSONException e) {
					Toast.makeText(this.activity, "Invalid request",
							Toast.LENGTH_LONG).show();
				}
			}
        }

        protected Boolean doInBackground(final String... args) {
            try{    
            	String json1 = HTTPReader.readJSON("http://sportsbettingspot.com/WebServiceGate.aspx?RequestType=gameslist&SportTypeID=" + MainActivity._gameID);
                
            	if (json1 != "ERROR")
                {
                	MainActivity._jsonG = json1;
                }
            	else
            		return false;
                
                return true;
             } 
            catch (Exception e)
             {
                return false;
             }
        }
    }

}
