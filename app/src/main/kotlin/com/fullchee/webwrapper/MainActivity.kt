package com.fullchee.webwrapper

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowInsets
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import android.window.OnBackInvokedDispatcher

class MainActivity : Activity() {
    private lateinit var home: LinearLayout
    private lateinit var browser: LinearLayout
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        home = buildHome()
        browser = buildBrowser()
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(home, LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))
            addView(browser, LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))
            // Target SDK 35+ draws edge to edge, so pad content clear of the system bars.
            setOnApplyWindowInsetsListener { view, insets ->
                val bars = insets.getInsets(WindowInsets.Type.systemBars())
                view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
                insets
            }
        }
        setContentView(root)
        showHome()
        onBackInvokedDispatcher.registerOnBackInvokedCallback(
            OnBackInvokedDispatcher.PRIORITY_DEFAULT,
        ) { goBack() }
    }

    override fun onPause() {
        super.onPause()
        CookieManager.getInstance().flush()
    }

    private fun buildHome() = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(48, 48, 48, 48)
        SITES.forEach { site ->
            addView(
                Button(context).apply {
                    text = site.name
                    setOnClickListener { open(site.url) }
                },
                LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT),
            )
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun buildBrowser(): LinearLayout {
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
        val toolbar = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            addView(Button(context).apply {
                text = "← Back"
                setOnClickListener { goBack() }
            })
            addView(Button(context).apply {
                text = "Home"
                setOnClickListener { showHome() }
            })
        }
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(toolbar, LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
            addView(webView, LinearLayout.LayoutParams(MATCH_PARENT, 0, 1f))
        }
    }

    private fun open(url: String) {
        webView.loadUrl(url)
        home.visibility = View.GONE
        browser.visibility = View.VISIBLE
    }

    private fun showHome() {
        webView.loadUrl("about:blank")
        webView.clearHistory()
        browser.visibility = View.GONE
        home.visibility = View.VISIBLE
    }

    private fun goBack() {
        when {
            home.visibility == View.VISIBLE -> finish()
            webView.canGoBack() -> webView.goBack()
            else -> showHome()
        }
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
