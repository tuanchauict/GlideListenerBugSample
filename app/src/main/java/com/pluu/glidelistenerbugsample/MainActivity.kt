package com.pluu.glidelistenerbugsample

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.pluu.glidelistenerbugsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val image1 =
        "https://plus.unsplash.com/premium_photo-1669885054268-cbd716cb8b52?q=80&w=2713&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    private val image2 =
        "https://images.unsplash.com/photo-1547721064-da6cfb341d50?q=80&w=2574&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    private val image3 =
        "https://images.unsplash.com/photo-1485470733090-0aae1788d5af?q=80&w=2717&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            loadImage(image1)
        }
        binding.btn2.setOnClickListener {
            loadImage(image2)
        }
        binding.btn3.setOnClickListener {
            loadImage(image3)
        }
    }

    private fun loadImage(url: String) {
        val listener = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable?>,
                isFirstResource: Boolean
            ): Boolean {
                Log.d("TAG", "[onLoadFailed] = ${hashCode()}")
                return false
            }

            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: Target<Drawable?>?,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                Log.d("TAG", "[onResourceReady] = ${hashCode()}")
                return false
            }
        }

        Log.d("TAG", "[Listener] = ${listener.hashCode()} ==> $url")

        Glide.with(binding.imgView)
            .load(url)
            .centerCrop()
            .addListener(listener)
            .into(binding.imgView)
    }
}