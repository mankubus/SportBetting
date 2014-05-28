package com.archystudio.sport;

import android.app.Activity;
import android.os.Bundle;

public class ExtActivity extends Activity{
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
	
		if (MainActivity.cur_task != 0) outState.putInt("CURTASK", MainActivity.cur_task);
		if (MainActivity._gameID != 0) outState.putInt("GAMEID", MainActivity._gameID);
		if (MainActivity._jsonG != null) outState.putString("JSONG", MainActivity._jsonG);
		if (MainActivity._jsonO != null) outState.putString("JSONO", MainActivity._jsonO);
		if (MainActivity._Game != null) outState.putParcelable("JGAME", MainActivity._Game);
		if (MainActivity._Odds != null) outState.putParcelableArrayList("JODDS", MainActivity._Odds);
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
        	if (savedInstanceState.containsKey("CURTASK")) MainActivity.cur_task = savedInstanceState.getInt("CURTASK");
        	if (savedInstanceState.containsKey("GAMEID")) MainActivity._gameID = savedInstanceState.getInt("GAMEID");
        	if (savedInstanceState.containsKey("JSONG")) MainActivity._jsonG = savedInstanceState.getString("JSONG");
        	if (savedInstanceState.containsKey("JSONO")) MainActivity._jsonO = savedInstanceState.getString("JSONO");
        	if (savedInstanceState.containsKey("JGAME")) MainActivity._Game = savedInstanceState.getParcelable("JGAME");
        	if (savedInstanceState.containsKey("JODDS")) MainActivity._Odds = savedInstanceState.getParcelableArrayList("JODDS");
        }
	}
}