package com.juddsolutions.stalker;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class TwitterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twitter);
		
        String twitterUser = getIntent().getExtras().getString("twitterUser");
		
		WebView webView = (WebView)findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://mobile.twitter.com/" + twitterUser);
		setTitle(twitterUser + " tweets");

	}

	
}
