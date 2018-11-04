package com.example.tcashapps.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tcashapps.R;
import com.example.tcashapps.fragment.BlogFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailBlogActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    @BindView(R.id.mainWebView)
    WebView webView;
    @BindView(R.id.mainIdTitle)
    TextView mainIdTitle;
    @BindView(R.id.backdrop)
    ImageView backDrop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_blog);

        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(DetailBlogActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Bundle bundle = getIntent().getExtras();

        String url = bundle.getString(BlogFragment.URL);
        String title = bundle.getString(BlogFragment.TITLE);
        String cover = bundle.getString(BlogFragment.COVER);

        Picasso.with(this).load(cover).into(backDrop);

        mainIdTitle.setText(String.format("%s", title));

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });

        webView.setLongClickable(false);
        webView.setHapticFeedbackEnabled(false);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.getSettings().setUseWideViewPort(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadData(url, "text/html; charset=utf-8", "UTF-8");
//        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);

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
