package com.example.simpletimerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var chronometr: Chronometer
    var running = false
    var offset: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometr = findViewById<Chronometer>(R.id.chronometer)

        val startButton = findViewById<Button>(R.id.btn_start)
        startButton.setOnClickListener {
            if (running == false){
                setBaseTime()
                chronometr.start()
                running = true
            }
        }

        val pauseButton = findViewById<Button>(R.id.btn_pause)
        pauseButton.setOnClickListener {
            if (running == true) {
                saveOffset()
                chronometr.stop()
                running = false
            }
        }

        val resetButton = findViewById<Button>(R.id.btn_reset)
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }

    private fun setBaseTime() {
        chronometr.base = SystemClock.elapsedRealtime() - offset
    }

    private fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - chronometr.base
    }
}