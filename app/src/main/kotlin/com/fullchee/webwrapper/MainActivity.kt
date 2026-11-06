package com.fullchee.webwrapper

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.WindowInsets
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.Toast
import android.window.OnBackInvokedDispatcher

class MainActivity : Activity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webView = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            // Google refuses sign-in from user agents marked as an embedded WebView ("; wv").
            settings.userAgentString = settings.userAgentString.replace("; wv", "")
            webViewClient = AllowlistClient()
        }
        CookieManager.getInstance().apply {
            setAcceptCookie(true)
            setAcceptThirdPartyCookies(webView, true)
        }
        // WebView ignores its own padding, so a plain container keeps it clear of the system bars.
        setContentView(FrameLayout(this).apply {
            addView(webView)
            // Target SDK 35+ draws edge to edge.
            setOnApplyWindowInsetsListener { view, insets ->
                val bars = insets.getInsets(WindowInsets.Type.systemBars())
                view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
                insets
            }
        })
        webView.loadUrl(HOME_URL)
        onBackInvokedDispatcher.registerOnBackInvokedCallback(
            OnBackInvokedDispatcher.PRIORITY_DEFAULT,
        ) { if (webView.canGoBack()) webView.goBack() else finish() }
    }

    override fun onPause() {
        super.onPause()
        CookieManager.getInstance().flush()
    }

    private inner class AllowlistClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            val url = request.url.toString()
            if (isAllowed(url)) return false
            Toast.makeText(this@MainActivity, "Blocked: ${request.url.host ?: url}", Toast.LENGTH_SHORT).show()
            return true
        }
    }
}
