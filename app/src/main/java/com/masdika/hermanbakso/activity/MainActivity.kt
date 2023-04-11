package com.masdika.hermanbakso.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.masdika.hermanbakso.R
import com.masdika.hermanbakso.databinding.ActivityMainBinding
import com.masdika.hermanbakso.fragment.CatatanFragment
import com.masdika.hermanbakso.fragment.MenuFragment
import com.masdika.hermanbakso.fragment.RiwayatFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {

    //    viewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisiasi Menu Fragment
        supportFragmentManager.beginTransaction().replace(R.id.frame_ly, MenuFragment()).commit()

        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                Log.d("Bottom_Bar", "Selected index: $newIndex, title: ${newTab.title}")
                when (newTab.id) {
                    // Get ID from nav_menu
                    R.id.tab_menu -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_ly, MenuFragment()).commit()
                    }

                    R.id.tab_catatan -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_ly, CatatanFragment()).commit()
                    }

                    R.id.tab_riwayat -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_ly, RiwayatFragment()).commit()
                    }

                }
            }
        })
    }
}