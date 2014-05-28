package com.archystudio.sport;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ExtActivity implements OnClickListener{
	public static int _gameID = 0;
	public static JGames _Game = null;
	public static ArrayList<JOdds> _Odds = null;
	
	public static String _jsonG = null;
	public static String _jsonO = null;
	
	public static int cur_task = 1;
	
	private Button btn_sportnfl;
	private Button btn_sportnba;
	private Button btn_sportmlb;
	
	private Button btn_sportnhl;
	private Button btn_sportcfl;
	private Button btn_sportncaaf;
	
	private Button btn_sportncaab;
	private Button btn_sportsoccer;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Sport buttons
        this.btn_sportnfl = (Button) findViewById(R.id.btn_sportnfl);
        this.btn_sportnba = (Button) findViewById(R.id.btn_sportnba);
        this.btn_sportmlb = (Button) findViewById(R.id.btn_sportmlb);
        this.btn_sportnhl = (Button) findViewById(R.id.btn_sportnhl);
        this.btn_sportcfl = (Button) findViewById(R.id.btn_sportcfl);
        this.btn_sportncaaf = (Button) findViewById(R.id.btn_sportncaaf);
        this.btn_sportncaab = (Button) findViewById(R.id.btn_sportncaab);
        this.btn_sportsoccer = (Button) findViewById(R.id.btn_sportsoccer);
        
        //click listeners
        this.btn_sportnfl.setOnClickListener(this);
        this.btn_sportnba.setOnClickListener(this);
        this.btn_sportmlb.setOnClickListener(this);
        this.btn_sportnhl.setOnClickListener(this);
        this.btn_sportcfl.setOnClickListener(this);
        this.btn_sportncaaf.setOnClickListener(this);
        this.btn_sportncaab.setOnClickListener(this);
        this.btn_sportsoccer.setOnClickListener(this);
        
    }
	
	public void onClick(View v)
	{
		int i = v.getId();
		int r = 0;
		
		switch (i)
		{
			case R.id.btn_sportnfl: r = 1; break; //NFL=1
			case R.id.btn_sportnba: r = 3; break; //NBA=3
			case R.id.btn_sportmlb: r = 5; break; //MLB=5
			case R.id.btn_sportnhl: r = 6; break; //NHL=6
			case R.id.btn_sportcfl: r = 7; break; //CFL=7
			case R.id.btn_sportncaaf: r = 2; break; //NCAAF = 2
			case R.id.btn_sportncaab: r = 4; break; //NCAAB=4
			case R.id.btn_sportsoccer: r = 10; break; //Soccer=10
			default : r = 0;
		}
		
		if (r != 0)
		{
			_gameID = r;
			_jsonG = null;
			Intent games_intent = new Intent("com.archystudio.sport.GamesActivityAct");
			startActivity(games_intent);
		}
	}
}