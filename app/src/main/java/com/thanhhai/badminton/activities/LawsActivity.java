package com.thanhhai.badminton.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thanhhai.badminton.R;

public class LawsActivity extends AppCompatActivity {

    private WebView webView;
    private String mFileName;
    private String mTitleName;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laws);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        mTitleName = intent.getStringExtra("titleFile");
        //getSupportActionBar().setTitle(mTitleName);
        mFileName = intent.getStringExtra("nameFile");

        progressDialog = new ProgressDialog(this);
        webView = (WebView) findViewById(R.id.wv_law);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
        progressDialog.setMessage("Loading..Please wait.");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        webView.loadUrl("https://badminton-laws.firebaseapp.com/laws/law"+mFileName+".html");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
