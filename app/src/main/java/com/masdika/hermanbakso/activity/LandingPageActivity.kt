package com.masdika.hermanbakso.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.masdika.hermanbakso.R
import com.masdika.hermanbakso.adapter.ImageSliderAdapter
import com.masdika.hermanbakso.databinding.ActivityLandingPageBinding
import com.masdika.hermanbakso.model.ImageData

class LandingPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingPageBinding

    //    IMAGE SLIDER VARIABEL
    private lateinit var adapter: ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Change status bar color into white
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        // Running Image Slider
        runSlider()

        // btnStart Listener
        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun runSlider() {
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            var index = 0
            override fun run() {
                if (index == list.size)
                    index = 0
                Log.e("Runnable.", "$index")
                binding.viewPager.currentItem = index
                index++
                handler.postDelayed(this, 3000)
            }
        }

        //        ADDING DATA IMAGE SLIDER
        list.add(
            ImageData(R.drawable.slider_1)
        )
        list.add(
            ImageData(R.drawable.slider_2)
        )
        list.add(
            ImageData(R.drawable.slider_3)
        )
        list.add(
            ImageData(R.drawable.slider_4)
        )

        adapter = ImageSliderAdapter(list)
        binding.viewPager.adapter = this.adapter

        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })
    }

    private fun selectedDot(position: Int) {
        for (i in 0 until list.size) {
            if (i == position) {
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.primary1))
            } else {
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.gray))
            }
        }
    }

    //    DOT INDIKATOR IMAGE SLIDER
    private fun setIndicator() {
        for (i in 0 until list.size) {
            dots.add(TextView(this))
            dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            dots[i].textSize = 14f
            binding.dotsIndicator.addView(dots[i])
        }
    }
//    DOT INDIKATOR IMAGE SLIDER

    override fun onStart() {
        super.onStart()
        handler.post(runnable)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
}
