package com.example.icleborobot_for_repair

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.icleborobot_for_repair.databinding.ActivityMainBinding
import com.example.icleborobot_for_repair.databinding.ContentSiteBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding_site: ContentSiteBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
//        Thread.sleep(2000)
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding_site = ContentSiteBinding.inflate(layoutInflater)

        binding.xmlWebView.settings.javaScriptEnabled=true
        binding.xmlWebView.webViewClient = WebViewClient()
        binding_site.siteWebView.settings.javaScriptEnabled=true
        binding_site.siteWebView.webViewClient = WebViewClient()

        binding.apply {
            toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                binding.drawerLayout.closeDrawers()
                when(it.itemId){
                    R.id.firstItem->{
                        Toast.makeText(this@MainActivity, "First Item Clicked", Toast.LENGTH_SHORT).show()
                    }
                    R.id.secondItem->{
                        Toast.makeText(this@MainActivity, "Second Item Clicked", Toast.LENGTH_SHORT).show()
                    }

                    R.id.icleborobot_aksessuary -> {
                        binding_site.siteWebView.loadUrl("https://icleborobot.by/product-category/aksessuary/")
                        setContentView(binding_site.root)
                        Toast.makeText(this@MainActivity, "Выбрано \"Запчасти\"", Toast.LENGTH_SHORT).show()
                        //return true
                    }

                    R.id.thirdItem->{
                        Toast.makeText(this@MainActivity, "Third Item Clicked", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }

}