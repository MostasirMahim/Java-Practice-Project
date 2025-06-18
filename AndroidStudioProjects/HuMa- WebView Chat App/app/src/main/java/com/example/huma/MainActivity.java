package com.example.huma;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply window insets for edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the WebView
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebClient());
        webView.loadUrl("https://whatsup-mmco.onrender.com/");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setFocusableInTouchMode(true);
        webView.requestFocus();

        View mainLayout = findViewById(R.id.main);

        // Add OnGlobalLayoutListener to detect keyboard visibility
        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                mainLayout.getWindowVisibleDisplayFrame(rect);

                int screenHeight = mainLayout.getHeight();
                int keypadHeight = screenHeight - rect.bottom;

                // If keyboard is visible, scroll the WebView content up
                if (keypadHeight > 150) {
                    webView.scrollTo(0, keypadHeight);  // Adjust scroll position
                }
            }
        });

        // Handle back press using OnBackPressedCallback
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack(); // Navigate back in WebView history
                } else {
                    finish(); // Exit the app
                }
            }
        };

        // Attach the callback to the back press dispatcher
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    // Custom WebViewClient to handle specific behaviors
    private static class MyWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString()); // Load the URL within the WebView
            return true; // Indicate that the URL loading is handled
        }
    }
}
