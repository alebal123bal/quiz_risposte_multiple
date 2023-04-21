package com.example.quiz_risposte_multiple;

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity;

class LastActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?){
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_last)

                val scoreTV = findViewById<TextView>(R.id.score_tv)

                scoreTV.text = getString(R.string.score, intent.getStringExtra("score"))

                //TODO Clear old activity
        }
}
