package com.example.tp2_mobileprogramming

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logoImageView: ImageView = findViewById(R.id.logoImageView)
        val continueButton: Button = findViewById(R.id.continueButton)

        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        logoImageView.startAnimation(animation)

        continueButton.setOnClickListener {
            startActivity(Intent(this, DestinationListActivity::class.java))
            finish()
        }
    }
}