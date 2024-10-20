package com.example.tp2_mobileprogramming

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DestinationDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_detail)

        val destination = intent.getParcelableExtra<Destination>("DESTINATION")

        destination?.let {
            findViewById<ImageView>(R.id.detailImageView).setImageResource(it.imageResId)
            findViewById<TextView>(R.id.detailNameTextView).text = it.name
            findViewById<TextView>(R.id.detailDescriptionTextView).text = it.longDescription

            findViewById<Button>(R.id.shareButton).setOnClickListener {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Download Visit Bali app to explore and get more information about ${destination.name}")
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        }
    }
}
