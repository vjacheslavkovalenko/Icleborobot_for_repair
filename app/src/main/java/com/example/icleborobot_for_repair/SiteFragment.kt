package com.example.icleborobot_for_repair

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.icleborobot_for_repair.databinding.FragmentSiteBinding

class SiteFragment : Fragment() {

//    private lateinit var binding_site: FragmentSiteBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_site, container, false)
    }



    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        //lateinit var swipeRefreshLayout: SwipeRefreshLayout
        //var swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipe_fragment_site)
        val siteWebView: WebView = view.findViewById(R.id.site_web_view)
        siteWebView.settings.javaScriptEnabled = true
        siteWebView.webViewClient = object : WebViewClient(){
            val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
            override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    view.loadUrl(url)
                    return true
                    }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?){
                progressBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }

//            override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//                // Check whether the key event is the Back button and if there's history.
//                if (keyCode == KeyEvent.KEYCODE_BACK && siteWebView.canGoBack()) {
//                    siteWebView.goBack()
//                    return true
//                }
//                // If it isn't the Back button or there isn't web page history, bubble up to
//                // the default system behavior. Probably exit the activity.
//                return super.onKeyDown(keyCode, event)
//            }

        }
        siteWebView.loadUrl("https://icleborobot.by/product-category/aksessuary/")

        swipeRefreshLayout = view.findViewById(R.id.swipe_fragment_site)
        swipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(requireActivity(), "Обновлено", Toast.LENGTH_LONG).show()
//                Toast.makeText(this, "Обновлено", Toast.LENGTH_LONG).show()
                swipeRefreshLayout.isRefreshing = false
            }, 300)

        }



    }


//        override fun onViewCreated(view: View, savedInstanceState: Bundle?){
//            val myWebView: WebView = view.findViewById(R.id.WebView1)
//            myWebView.webViewClient = object : WebViewClient() {
//                override fun shouldOverrideUrlLoading(
//                    view: WebView,
//                    url: String
//                ): Boolean {
//                    view.loadUrl(url)
//                    return true
//                }
//            }
//
//            myWebView.loadUrl("https://google.com")
//            myWebView.settings.javaScriptEnabled = true
//            myWebView.settings.allowContentAccess = true
//            myWebView.settings.domStorageEnabled = true
//            myWebView.settings.useWideViewPort = true


//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        // Check whether the key event is the Back button and if there's history.
//        if (keyCode == KeyEvent.KEYCODE_BACK && binding_site.xmlWebview.canGoBack()) {
//            binding_content.xmlWebview.goBack()
//            return true
//        }
//        // If it isn't the Back button or there isn't web page history, bubble up to
//        // the default system behavior. Probably exit the activity.
//        return super.onKeyDown(keyCode, event)




//    override fun onRefresh() {
//        Handler(Looper.getMainLooper()).postDelayed({
//            Toast.makeText(this, "Обновлено", Toast.LENGTH_LONG).show()
//            swipeRefreshLayout.isRefreshing = false
//        }, 300)
//    }

}