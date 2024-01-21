package com.example.icleborobot_for_repair

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentManager


class MainFragment : Fragment() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: MainFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        val buttonToSite: Button = view.findViewById(R.id.button_to_site)
        val buttonToRepair: Button = view.findViewById(R.id.button_to_repair)

        buttonToSite.setOnClickListener{
            goToFragment(SiteFragment())
        }

        buttonToRepair.setOnClickListener{
            Toast.makeText(requireActivity(), "Раздел в разработке", Toast.LENGTH_SHORT).show()
        }

    }

    private fun goToFragment(fragment: SiteFragment){
        fragmentManager = childFragmentManager
        fragmentManager.beginTransaction().replace(R.id.constraintlayout_fragment_main, fragment).commit()
    }

}