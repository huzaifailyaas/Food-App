package com.example.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.first.Info.info
import com.example.first.chatadapter.IntroSlideAdapter
import com.example.first.modelclass.IntroSlide
import android.widget.ImageView as ImageView1

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var buttonnext:Button
    private lateinit var textSkipIntro:TextView

    private val introSlideAdapter=IntroSlideAdapter(
        listOf(
            IntroSlide(
                "Order for Food",
                    "Lorem ipsum dolor sit amet, consectetur\n adipiscing elit, sed do eiusmod tempor\n incididunt ut labore et\n dolore magna aliqua.",
                        R.drawable.lunch
            ),
                    IntroSlide(
                    "Easy Payment",
                        "Lorem ipsum dolor sit amet, consectetur\n adipiscing elit, sed do eiusmod tempor\n incididunt ut labore et dolore magna aliqua.",
                              R.drawable.payment,
        ),
            IntroSlide(
                "Fast Delivery",
                "Lorem ipsum dolor sit amet, consectetur\n adipiscing elit, sed do eiusmod tempor\n incididunt ut labore et dolore magna aliqua.",
                R.drawable.delievery,
            ),

        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2=findViewById(R.id.introSliderviewPager)
        indicatorContainer=findViewById(R.id.indicatorContainer)
        buttonnext=findViewById(R.id.buttonNext)
        textSkipIntro=findViewById(R.id.textSkipIntro)

        viewPager2.adapter = introSlideAdapter
        setupIndicators()
        setCurrentindicator(0)
        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentindicator(position)
            }
        })
        buttonnext.setOnClickListener{
            if(viewPager2.currentItem + 1 < introSlideAdapter.itemCount){
                viewPager2.currentItem+=1
            }else{
                    Intent(applicationContext,info::class.java).also {
                        startActivity(it)
                    }
            }
            textSkipIntro.setOnClickListener{
                Intent(applicationContext,info::class.java).also {
                    startActivity(it)
                }
            }
        }

    }
    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView1>(introSlideAdapter.itemCount)
        val layoutParams:LinearLayout.LayoutParams=
            LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView1(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams= layoutParams
            }
            indicatorContainer.addView(indicators[i])
        }
    }
    private fun setCurrentindicator(index: Int){
        val childCount = indicatorContainer.childCount
        for(i in 0 until childCount){
            val imageView= indicatorContainer[i] as ImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}
