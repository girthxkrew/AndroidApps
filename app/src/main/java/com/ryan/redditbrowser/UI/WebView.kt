package com.ryan.redditbrowser.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.ryan.redditbrowser.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebView : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
    }
}
