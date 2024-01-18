package com.example.icleborobot_for_repair

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.icleborobot_for_repair.databinding.FragmentSiteBinding

class SiteFragment : Fragment() {

//    private lateinit var binding_site: FragmentSiteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_site, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        val siteWebView: WebView = view.findViewById(R.id.site_web_view)
        siteWebView.settings.javaScriptEnabled = true
        siteWebView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    view.loadUrl(url)
                    return true
                    }
        }
        siteWebView.loadUrl("https://icleborobot.by/product-category/aksessuary/")
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


}