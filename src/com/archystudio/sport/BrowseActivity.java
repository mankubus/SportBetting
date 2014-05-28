package com.archystudio.sport;

import android.os.Bundle;
import android.webkit.WebView;

public class BrowseActivity extends ExtActivity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        
        Bundle extras = getIntent().getExtras();
        String link = extras.getString("cur_odd");
        
        WebView wb = (WebView) findViewById(R.id.webview);
        wb.loadUrl(link);
        finish();
	}
}
