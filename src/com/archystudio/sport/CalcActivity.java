package com.archystudio.sport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CalcActivity extends ExtActivity{
	private TextView tvHomeCommand;
	private TextView tvAwayCommand;
	private TextView tvTimeDate;

	private EditText edML;
	private EditText edSL;
	private EditText edTL;
	
	private Button btnCalc;
	private ImageView btnCalculate;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        
        this.edML = (EditText) findViewById(R.id.etML);
        this.edSL = (EditText) findViewById(R.id.etSL);
        this.edTL = (EditText) findViewById(R.id.etTL);
        this.btnCalculate = (ImageView) findViewById(R.id.imgCalculate);
        
        
        this.tvHomeCommand = (TextView) findViewById(R.id.tvHomeCommand);
        this.tvAwayCommand = (TextView) findViewById(R.id.tvAwayCommand);
        this.tvTimeDate = (TextView) findViewById(R.id.tvDateTime);
        this.btnCalc = (Button) findViewById(R.id.btnCalc);
        
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
		
		this.btnCalculate.setOnClickListener(new OnClickListener() {
	         public void onClick(View v) 
	 	         {
	        	 	String ML = edML.getText().toString();
	        	 	String SL = edSL.getText().toString();
	        	 	String TL = edTL.getText().toString();
	        	 	
	        	 	int iML; 
	        	 	try {iML = Integer.parseInt(ML);} catch (Exception e) { iML = 0;};
	        	 	int iSL; 
	        	 	try {iSL = Integer.parseInt(SL);} catch (Exception e) { iSL = 0;};
	        	 	int iTL; 
	        	 	try {iTL = Integer.parseInt(TL);} catch (Exception e) { iTL = 0;};
	        	 			
	        	 	
	        	 	Intent calc_intent = null;		
	 	         	calc_intent = new Intent("com.archystudio.sport.LinesActivityAct");
	 	         	calc_intent.putExtra("iML", iML);
	 	         	calc_intent.putExtra("iSL", iSL);
	 	         	calc_intent.putExtra("iTL", iTL);
	 	         	startActivity(calc_intent);
	 	         }
	         });
	}

}
