package com.genius.quiz

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.result.*


class ResultActivity : AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.result)

        //set percentage and  number of correct answers
        var correct = intent.getIntExtra("correct", 0)

        var percent : Double = (correct*100)/3.00

        progress.text="Asked"  + " " + 3 + " " +  "questions." +" You answered correctly in  " + " " +  correct+ " " +"questions." + " " + "Percent of right answers" + " " +  percent + " " + "%."

    }}