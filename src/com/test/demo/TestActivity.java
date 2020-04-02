package com.test.demo;


import com.hikvision.netsdk.HCNetSDK;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class TestActivity extends Activity {
	
	private WebView webview;  	
	public final static int RESULT_CODE = 1;
	public String appkey = ""; // fill in with appkey
	public String areaId = ""; //fill in wtih areaId
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.test_layout); 
     
    webview = (WebView)findViewById(R.id.id_webview); 
    String HCurl = "https://openauth.ezvizlife.com/oauth/ddns/" + appkey + "?areaId=" + areaId;  
    webview.loadUrl(HCurl);
    webview.getSettings().setJavaScriptEnabled(true);
    webview.setWebViewClient(new WebViewClient(){
    		
    	@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url){
			view.loadUrl(url);			
			return true;
		}
    	  
    	public void onPageFinished(WebView view, String url){
    		
    		if(url != null && url.contains("success"))
	        {
    			Intent intent  = new Intent();
	            intent.putExtra("Info", url);
	            setResult(1, intent);
	            TestActivity.this.finish();         	
	        }
    		super.onPageFinished(view, url);   		
    	}        	
    });    
    }
}
