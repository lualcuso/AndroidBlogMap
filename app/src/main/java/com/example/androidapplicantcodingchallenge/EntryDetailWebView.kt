package com.example.androidapplicantcodingchallenge

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.androidapplicantcodingchallenge.models.BlogEntry

class EntryDetailWebView : AppCompatActivity() {

    private lateinit var webView: WebView

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_URL = "url"

        fun newIntent(context: Context, blogEntry: BlogEntry): Intent {
            val detailIntent = Intent(context, EntryDetailWebView::class.java)

            detailIntent.putExtra(EXTRA_TITLE, blogEntry.title)
            detailIntent.putExtra(EXTRA_URL, blogEntry.link)

            return detailIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_detail_web_view)

        val title = intent.extras?.getString(EXTRA_TITLE)
        var url = intent.extras?.getString(EXTRA_URL)

        url = url?.replace("android.html", "")?.replace("http", "https")

        setTitle(title)

        webView = findViewById(R.id.entry_detail_web_view)

        webView.loadUrl(url)
    }
}
