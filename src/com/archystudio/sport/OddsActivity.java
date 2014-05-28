package com.archystudio.sport;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OddsActivity extends ExtActivity{	
	private ProgressTask backTask = null; 
	private ProgressDialog dialog;
	
	private ListView lvOdds;
	
	private TextView tvHomeCommand;
	private TextView tvAwayCommand;
	private TextView tvTimeDate;
	
	private Button btnCalc;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.odds);    
        
        this.lvOdds = (ListView) findViewById(R.id.lvOdds);
        
        this.tvHomeCommand = (TextView) findViewById(R.id.tvHomeCommand);
        this.tvAwayCommand = (TextView) findViewById(R.id.tvAwayCommand);
        this.tvTimeDate = (TextView) findViewById(R.id.tvDateTime);
        this.btnCalc = (Button) findViewById(R.id.btnCalc);
        
        this.tvHomeCommand.setText(MainActivity._Game.HomeTeamLast);
        this.tvAwayCommand.setText(MainActivity._Game.AwayTeamLast);
        this.tvTimeDate.setText(DateUtil.getShortDate(MainActivity._Game.StartDate));
        
        
        if (savedInstanceState != null)
        {
        	if (MainActivity._jsonO != null)
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
		
        backTask = new ProgressTask(OddsActivity.this);
		backTask.execute();
	}
	
	//Get old ProgressBar
	 public Object onRetainNonConfigurationInstance() {
		    if (backTask != null && backTask.getStatus() != Status.FINISHED)
		      return backTask;
		    return null;
	 }
	 
	 private void showModalDialog(final JOdds item)
	 {
 	  	  Context mContext = OddsActivity.this;
 	  	  final Dialog dialog = new Dialog(mContext);
          
 	  	  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
 	  	  dialog.setContentView(R.layout.odds_data);
 	  	  dialog.setCancelable(true);
 	  	  
 	  	  ImageView imgBookEx = (ImageView) dialog.findViewById(R.id.imgBookEx);
 	  	  ImageView imgVisit = (ImageView) dialog.findViewById(R.id.ivVisit);
 	  	  Button btnClose = (Button) dialog.findViewById(R.id.btnClose);
 	  	  
 	  	  TextView tvPlayers = (TextView) dialog.findViewById(R.id.tv_PL);
 	  	  TextView tvBonuses = (TextView) dialog.findViewById(R.id.tv_BN);
 	  	  
 	  	  tvBonuses.setText("Bonuses: " + item.Bonuses);
 	  	  tvPlayers.setText("Players: " + item.PlayersAccepted);
 	  	  
 	  	  DrawableBackgroundDownloader drm = new DrawableBackgroundDownloader(OddsActivity.this);
 	  	  drm.loadDrawable(item.SportBookLogo, imgBookEx, null);
 	  	  
 	  	  btnClose.setOnClickListener(new OnClickListener() {
 	         public void onClick(View v) 
	 	         {
	 	         	dialog.dismiss();
	 	         }
 	         });
 	  	  
 	  	  imgVisit.setOnClickListener(new OnClickListener() {
  	         public void onClick(View v) 
 	 	         {
 	 	         	dialog.dismiss();
 	 				
 	 	         	Intent brw_intent = new Intent("com.archystudio.sport.BrowseActivityAct");
 	 	         	brw_intent.putExtra("cur_odd", item.SportBookURL);
 	 	         	startActivity(brw_intent);
 	 	         }
  	         });
 	  	  
 	  	  dialog.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    finish();
                }});
 	  	  
		  dialog.show();
		 
	 }
	 
	// processing JSON
	private void onReceive(int flag) throws JSONException {
		MainActivity._Odds = new ArrayList<JOdds>();

		if (flag == 1) {
			
			// json Object
			JSONArray jsonRoot = new JSONArray(MainActivity._jsonO);
			for (int i = 0; i < jsonRoot.length(); i++) {

				JSONObject jsonObject = jsonRoot.getJSONObject(i);
				JOdds _jo = new JOdds();

				_jo.Bonuses = jsonObject.getString("Bonuses");
				_jo.PlayersAccepted = jsonObject.getString("PlayersAccepted");
				_jo.SportBookID = jsonObject.getInt("SportBookID");
				_jo.SportBookURL = jsonObject.getString("SportBookURL");
				_jo.SportBookLogo = jsonObject.getString("SportbookLogo");

				JSONObject jsonML = jsonObject.getJSONObject("ML");
				_jo.ML_Away = jsonML.getInt("Away");
				_jo.ML_Home = jsonML.getInt("Home");

				JSONObject jsonSL = jsonObject.getJSONObject("SL");
				_jo.SL_AwayLine = jsonSL.getDouble("AwayLine");
				_jo.SL_AwayMoney = jsonSL.getInt("AwayMoney");
				_jo.SL_HomeLine = jsonSL.getDouble("HomeLine");
				_jo.SL_HomeMoney = jsonSL.getInt("HomeMoney");

				JSONObject jsonTL = jsonObject.getJSONObject("Total");
				_jo.Total_AwayMoney = jsonTL.getInt("AwayMoney");
				_jo.Total_HomeMoney = jsonTL.getInt("HomeMoney");
				_jo.Total_TotalLine = jsonTL.getDouble("TotalLine");
				MainActivity._Odds.add(_jo);
			}
			
			final JOddsAdapter _jadapter = new JOddsAdapter(this, MainActivity._Odds);
			this.lvOdds.setAdapter(_jadapter);
			
			//On item in List click
			this.lvOdds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		  	      public void onItemClick(AdapterView<?> av, View view, int index, long arg3) {
		  	    	  	  JOdds a = (JOdds) av.getItemAtPosition(index);	  	    		  

		  	    	  	  showModalDialog(a);
		  	      }
		    	});
			
			//goto Calculator
			this.btnCalc.setOnClickListener(new OnClickListener() {
 	         public void onClick(View v) 
	 	         {
	 	         	Intent calc_intent = new Intent("com.archystudio.sport.CalcActivityAct");
	 	         	startActivity(calc_intent);
	 	         }
 	         });
		}
	}
	
	//Progress bar and server answ.
	private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private OddsActivity activity;
        
        public ProgressTask(OddsActivity activity) {
            super();
        	this.activity = activity;
        }
        
        public void attach(OddsActivity a) {
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
				if (MainActivity._jsonO != "")
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
            	String json1 = HTTPReader.readJSON("http://sportsbettingspot.com/WebServiceGate.aspx?RequestType=gamebets&GameID=" + MainActivity._Game.GameID);
                
            	if (json1 != "ERROR")
                {
                	MainActivity._jsonO = json1;
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
