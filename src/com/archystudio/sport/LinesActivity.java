package com.archystudio.sport;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class LinesActivity extends ExtActivity{
	
	private TextView tvHomeCommand;
	private TextView tvAwayCommand;
	private TextView tvTimeDate;
	
	private TextView tvHome;
	private TextView tvAway;
	
	private Button btnCalc;
	private TextView tvBet;
	
	private ListView lvBooks;
	
	private int iML = 0;
	private int iSL = 0;
	private int iTL = 0;
	
	private SegmentedControlButton scbML;
	private SegmentedControlButton scbSL;
	private SegmentedControlButton scbTL;
	
	private JBooksAdapter _jadapter;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lines);
        
        Bundle extras = getIntent().getExtras();
        this.iML = extras.getInt("iML");
        this.iSL = extras.getInt("iSL");
        this.iTL = extras.getInt("iTL");
        
        this.lvBooks = (ListView) findViewById(R.id.lvBooks);
        this.tvHome = (TextView) findViewById(R.id.tvHomeC);
        this.tvAway = (TextView) findViewById(R.id.tvAwayC);
        
        scbML = (SegmentedControlButton) findViewById(R.id.option1);
        scbSL = (SegmentedControlButton) findViewById(R.id.option2);
        scbTL = (SegmentedControlButton) findViewById(R.id.option3);
        
        this.tvHomeCommand = (TextView) findViewById(R.id.tvHomeCommand);
        this.tvAwayCommand = (TextView) findViewById(R.id.tvAwayCommand);
        this.tvTimeDate = (TextView) findViewById(R.id.tvDateTime);
        this.btnCalc = (Button) findViewById(R.id.btnCalc);
        this.tvBet = (TextView) findViewById(R.id.tvBet);
        
        this.tvHome.setText(MainActivity._Game.HomeTeamLast);
        this.tvAway.setText(MainActivity._Game.AwayTeamLast);
        this.tvHomeCommand.setText(MainActivity._Game.HomeTeamLast);
        this.tvAwayCommand.setText(MainActivity._Game.AwayTeamLast);
        this.tvTimeDate.setText(DateUtil.getShortDate(MainActivity._Game.StartDate));
        
		this.btnCalc.setOnClickListener(new OnClickListener() {
	         public void onClick(View v) 
	 	         {
	        	 	Intent calc_intent = null;		
	 	         	calc_intent = new Intent("com.archystudio.sport.OddsActivityAct");
	 	         	calc_intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	 	         	startActivity(calc_intent);
	 	         	finish();
	 	         }
	         });
		
		scbML.setOnClickListener(new OnClickListener() {
	         public void onClick(View v)
	         {
	        	 MainActivity.cur_task = 1;
	        	 showAdd(1);
	         }
		});
		
		scbSL.setOnClickListener(new OnClickListener() {
	         public void onClick(View v)
	         {
	        	 MainActivity.cur_task = 2;
	        	 showAdd(2);
	         }
		});
		
		scbTL.setOnClickListener(new OnClickListener() {
	         public void onClick(View v)
	         {
	        	 MainActivity.cur_task = 3;
	        	 showAdd(3);
	         }
		});
		
		_jadapter = new JBooksAdapter(this, null);
		
		//Previous state
		showAdd(MainActivity.cur_task);
		
	}
	
	private int calcSum(int param, int bet)
	{
		double res = 0;
		
		if (param < 0)
		{
			res = (bet*100)/Math.abs(param);
		}
		
		if (param > 0) 
		{
			res = (bet * Math.abs(param)) / 100;
		}
		
		BigDecimal bigDecimal = new BigDecimal(res);
		bigDecimal = bigDecimal.setScale(BigDecimal.ROUND_HALF_UP);
		
		return bigDecimal.intValue();
	}
	
	private void showAdd(int id)
	{
		int ubet = 100;
		String line = "ERROR";
		switch (id)
		{
			case 1: ubet = iML; line = "Moneyline"; break; 
			case 2: ubet = iSL; line = "Spreadline"; break; 
			case 3: ubet = iTL; line = "Total"; break; 
		}
		
		tvBet.setText("Your " + line + " bet: " + ubet + "$");
		
		ArrayList<JBooks> arr = new ArrayList<JBooks>();
		
		for(int i = 0; i < MainActivity._Odds.size(); i++)
		{
			int p_home  = 0;
			int p_away  = 0;
			int post = 0;
			
			switch (id)
			{
				case 1: 
					p_home = MainActivity._Odds.get(i).ML_Home;
					p_away = MainActivity._Odds.get(i).ML_Away;
					post = iML;
					break;

				case 2: 
					p_home = MainActivity._Odds.get(i).SL_HomeMoney;
					p_away = MainActivity._Odds.get(i).SL_AwayMoney;
					post = iSL;
					break;
				
				case 3: 
					p_home = MainActivity._Odds.get(i).Total_HomeMoney;
					p_away = MainActivity._Odds.get(i).Total_AwayMoney;
					post = iTL;
					break;
			}
			
			JBooks jb = new JBooks();
			jb.Logo = MainActivity._Odds.get(i).SportBookLogo;
			jb.AwayMoney = calcSum(p_away, post);
			jb.HomeMoney = calcSum(p_home, post);
			
			arr.add(jb);
			
		}
		
		_jadapter.savedata(arr);
		this.lvBooks.setAdapter(_jadapter);
		_jadapter.notifyDataSetChanged();
			
	}

}
