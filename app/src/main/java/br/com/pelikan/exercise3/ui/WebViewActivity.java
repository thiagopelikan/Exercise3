package br.com.pelikan.exercise3.ui;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import br.com.pelikan.exercise3.R;
import br.com.pelikan.exercise3.model.FavoriteModel;

public class WebViewActivity extends AppCompatActivity {
    public static final String TARGET_FAVORITE = "target_favorite";

    private FavoriteModel favorite;
    protected WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        injectExtras();

        if(favorite == null){
            finish();
        }

        webView = (WebView)findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewController());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(favorite.getUrl());
    }

    private void injectExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(TARGET_FAVORITE)) {
                this.favorite = extras.getParcelable(TARGET_FAVORITE);
            }
        }
    }

    public class WebViewController extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
