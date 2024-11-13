package com.example.seekbar

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        val mySeekBar: SeekBar = findViewById(R.id.who_won_one)
        val myTextView: TextView = findViewById(R.id.seekbar_view)
        mySeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progess: Int, fromUser: Boolean) {
                myTextView.text = "Wartość suwaka: ${progess}"
            }

            override fun onStartTrackingTouch(seekbar: SeekBar?) {
                Log.i("log", "start")
            }

            override fun onStopTrackingTouch(seekbar: SeekBar?) {
                Log.i("log", "stop")
            }

        })

        val myListView: ListView = findViewById(R.id.adapter_nb1)
        val myKebs = listOf("średni kebs", "duży kebs", "gigant kebs", "kosmicznie duży kebab")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myKebs)
        myListView.adapter = adapter

        myListView.setOnItemClickListener { _, _, index, _ ->
            val clickedKebs = myKebs[index]
            Log.i("kebs", "to jest kliknięty $clickedKebs")
        }
    }
}