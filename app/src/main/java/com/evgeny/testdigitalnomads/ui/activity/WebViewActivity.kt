package com.evgeny.testdigitalnomads.ui.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.util.POST_URL
import com.evgeny.testdigitalnomads.util.visible
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    //==============================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url =intent.getStringExtra(POST_URL)
        url?.let {
            initWebView(url)
        }
    }

    //==============================================================================================

    private fun initWebView(url: String) {
        web_view.webViewClient = WebViewClient()
        web_view.settings.javaScriptEnabled = true
        web_view.loadUrl(url)

        web_view.webChromeClient = object : WebChromeClient() {
            override fun getDefaultVideoPoster(): Bitmap? {
                // это фикс. На некоторых версиях ВебВью не может прогрузить какие-то ресурсы и крашится

                return if (super.getDefaultVideoPoster() == null) {
                    BitmapFactory.decodeResource(this@WebViewActivity.resources,
                        R.drawable.cmn_placeholder_transparent
                    )
                } else {
                    super.getDefaultVideoPoster()
                }
            }

            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress < 100 && web_pb.visibility == ProgressBar.GONE) {
                    web_pb.visibility = ProgressBar.VISIBLE
                }

                web_pb.progress = progress
                if (progress == 100) {
                    web_pb.visibility = ProgressBar.GONE
                }
            }
        }
    }

}