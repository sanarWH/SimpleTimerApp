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

    val OFFSET_KEY = "offset"
    val RUNNING_KEY = "running"
    val BASE_KEY = "base"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometr = findViewById<Chronometer>(R.id.chronometer)

        if (savedInstanceState != null) {
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if (running) {
                chronometr.base = savedInstanceState.getLong(BASE_KEY)
                chronometr.start()
            } else setBaseTime()
        }

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

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong(OFFSET_KEY, offset)
        savedInstanceState.putBoolean(RUNNING_KEY, running)
        savedInstanceState.putLong(BASE_KEY, chronometr.base)
        super.onSaveInstanceState(savedInstanceState)
    }

    private fun setBaseTime() {
        chronometr.base = SystemClock.elapsedRealtime() - offset
    }

    private fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - chronometr.base
    }
}