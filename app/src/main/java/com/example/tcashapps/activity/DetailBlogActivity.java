package com.example.tcashapps.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.tcashapps.R;

public class DetailBlogActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_blog);

        progressDialog = new ProgressDialog(DetailBlogActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url = getIntent().getStringExtra("url");
        Log.d("TEST", "onCreate: "+url);

//        String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"320\" height=\"315\" src=\"https://www.youtube.com/embed/47yJ2XCRLZs\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        String frameVideo = "<html><body>Youtube video .. <br> <iframe width='320' height='315' src='https://www.youtube.com/embed/lY2H2ZP56K4?autoplay=1' frameborder='0' allowfullscreen></iframe></body></html>";

        String html = "<iframe class=\"youtube-player\" style=\"border: 0; width: 100%; height: 95%; padding:0px; margin:0px\" id=\"ytplayer\" type=\"text/html\" src=\"http://www.youtube.com/embed/"
                + "lY2H2ZP56K4?autoplay=1"
                + "&fs=0\" frameborder=\"0\">\n"
                + "</iframe>\n";

        String url1 = "<html><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"><meta name=\"HandheldFriendly\" content=\"true\"><body><iframe width='320' height='315 src=\\\"http://45.32.105.117:9876/files/5bdc85b4509acc71ee5f11ec\\\" frameborder=\\\"1\\\" allow=\\\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\\\" allowfullscreen></iframe></body></html>";

        WebView webView = new WebView(this);
        setContentView(webView);
//        webView.loadUrl("https://stackoverflow.com/");
//        webView.loadDataWithBaseURL("", url, "text/html", "UTF-8", "");
        webView.loadData(url, "text/html; charset=utf-8", "UTF-8");
//        webView.loadData(""+Html.fromHtml(url), "text/html", "UTF-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(DetailBlogActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
