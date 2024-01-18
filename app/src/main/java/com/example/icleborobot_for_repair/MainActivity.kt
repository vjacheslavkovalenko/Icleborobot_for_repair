package com.example.icleborobot_for_repair

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.icleborobot_for_repair.databinding.ActivityMainBinding
import com.example.icleborobot_for_repair.databinding.FragmentSiteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var conf: AppBarConfiguration
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    private lateinit var binding_site: FragmentSiteBinding
    private lateinit var dialog : AlertDialog

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
//        Thread.sleep(2000)
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = MaterialAlertDialogBuilder(this , R.style.MaterialAlertDialog_Rounded)
            .setView(R.layout.internet_dialog)
            .setCancelable(false)
            .create()

        val networkManager = NetworkManager(this)
        networkManager.observe(this){

            if (!it){
                if (!dialog.isShowing)
                    dialog.show()
            }else{
                if (dialog.isShowing)
                    dialog.hide()
            }
        }


        setSupportActionBar(binding.contentNavToolbar.toolbar)
        navController = findNavController(R.id.fragmentContainerView)
        conf = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.menu_site,
                R.id.repair,
                R.id.contacts
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, conf)
        binding.navView.setupWithNavController(navController)
//        binding.xmlWebView.settings.javaScriptEnabled=true
//        binding.xmlWebView.webViewClient = WebViewClient()

//        binding_site = FragmentSiteBinding.inflate(layoutInflater)
//
//        binding_site.siteWebView.settings.javaScriptEnabled=true
//        binding_site.siteWebView.webViewClient = WebViewClient()

        binding.navView.setNavigationItemSelectedListener {
            binding.drawerLayout.closeDrawers()
            when(it.itemId){
                R.id.mainFragment->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MainFragment()).commit()
                    Toast.makeText(this@MainActivity, "Выбрано \"Главная\"", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_site->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SiteFragment()).commit()
                    Toast.makeText(this@MainActivity, "Выбран \"Сайт\"", Toast.LENGTH_SHORT).show()
                }

                R.id.repair -> {

                    Toast.makeText(this@MainActivity, "Выбрано \"Ремонт\"", Toast.LENGTH_SHORT).show()
                }

                R.id.contacts->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ContactsFragment()).commit()
                    Toast.makeText(this@MainActivity, "Third Item Clicked", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(conf) || super.onSupportNavigateUp()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check whether the key event is the Back button and if there's history.
        if (keyCode == KeyEvent.KEYCODE_BACK && binding_site.siteWebView.canGoBack()) {
            binding_site.siteWebView.goBack()
            return true
        }
        // If it isn't the Back button or there isn't web page history, bubble up to
        // the default system behavior. Probably exit the activity.
        return super.onKeyDown(keyCode, event)
    }


}