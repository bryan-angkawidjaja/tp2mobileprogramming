package com.example.tp2_mobileprogramming

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DestinationListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var destinationAdapter: DestinationAdapter
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_list)

        dbHelper = DatabaseHelper(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val destinations = dbHelper.getAllDestinations()

        destinationAdapter = DestinationAdapter(destinations) { destination ->
            val intent = Intent(this, DestinationDetailActivity::class.java)
            intent.putExtra("DESTINATION", destination)
            startActivity(intent)
        }

        recyclerView.adapter = destinationAdapter
    }
}