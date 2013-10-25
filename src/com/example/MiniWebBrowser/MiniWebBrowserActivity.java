package com.example.MiniWebBrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: dimkas71
 * Date: 10/25/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class MiniWebBrowserActivity extends Activity {

    private WebView webView;

    private EditText link;

    private TextView statusBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        webView = (WebView) findViewById(R.id.webView);
        link = (EditText) findViewById(R.id.link);

        statusBar = (TextView) findViewById(R.id.txt_status_bar);

        webView.getSettings().setBuiltInZoomControls(true);

        //getWindow().requestFeature(Window.FEATURE_PROGRESS);



    }

    public void loadPage(View v) {


        // Let's display the progress in the activity title bar, like the
        // browser app does.
       // getWindow().requestFeature(Window.FEATURE_PROGRESS);

        webView.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                activity.setProgress(progress * 1000);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });


        String urlString = link.getText().toString();
        webView.loadUrl(urlString.toString());


        statusBar.setText(urlString);


    }

    public void onSaveBookmarkClick(View v) {
       /* Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getText().toString()));
        startActivity(intent);*/

        if ( webView.canGoBack() ) {
            webView.goBack();
        }

    }

}