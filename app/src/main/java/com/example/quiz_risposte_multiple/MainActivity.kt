package com.example.quiz_risposte_multiple

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

//Hello GIT
//1
//2

//This class implements View.OnClickListener just to override its clicking method
class MainActivity : AppCompatActivity(), View.OnClickListener {
    var question_index : Int = 0
    var toggle_correct: Boolean = false
    var score: Int = 0

    lateinit var all_textBoxes: List<TextView>
    lateinit var all_buttons: List<TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //List of all textboxes
        all_textBoxes = listOf(
            findViewById(R.id.question_number),
            findViewById(R.id.question_id))

        //List of all five possible buttons
        all_buttons = listOf(
            findViewById(R.id.answer1),
            findViewById(R.id.answer2),
            findViewById(R.id.answer3),
            findViewById(R.id.answer4),
            findViewById(R.id.submit_id),
            findViewById(R.id.back_button))

        for(a in all_buttons) {
            a.setOnClickListener(this)
        }
        whiten_buttons()
        setup_text(question_index)
    }

    override fun onClick(view: View){
        when(view.id){
            R.id.answer1 -> {
                treat_clicked_button(0)
            }
            R.id.answer2 -> {
                treat_clicked_button(1)
            }
            R.id.answer3 -> {
                treat_clicked_button(2)
            }
            R.id.answer4 -> {
                treat_clicked_button(3)
            }
            R.id.submit_id -> {
                if(toggle_correct){
                    score++
                }
                if(question_index < questionAnswer.question.size-1){
                    questionAnswer.answered[question_index]=true
                    question_index++
                    whiten_buttons()
                    setup_text(question_index)
                }
                else{
                    val intent = Intent(this, LastActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.back_button->{
                if(question_index>0){
                    questionAnswer.answered[question_index] = false
                    /* TODO: clear score by creating a stack of last selected buttons
                    *   so I'm able to reconstructo history of moves and clear accordingly */

                    question_index--
                    whiten_buttons()
                    setup_text(question_index)
                }
            }
        }
    }

    fun treat_clicked_button(idx: Int){
        highlight_button(idx)
        toggle_correct = false
        if(check_correctness(idx) and !(questionAnswer.answered[idx])){
            toggle_correct = true
        }
    }

    fun setup_text(idx: Int){
        val n_q = idx + 1
        all_textBoxes[0].text = "Question $n_q score $score"
        all_textBoxes[1].text = questionAnswer.question[idx]
        for(i: Int in 0..3){
            all_buttons[i].text = questionAnswer.answers[idx][i]
        }
    }

    fun get_buttons_list(): List<Button>{
        //This list does not contain Back button, only 4 answer buttons
        val my_list: List<Button> = listOf(
            all_buttons[0] as Button,
            all_buttons[1] as Button,
            all_buttons[2] as Button,
            all_buttons[3] as Button)
        return my_list
    }

    fun whiten_buttons(){
        //Whitens only 4 answer buttons
        val btn_list: List<Button> = get_buttons_list()
        for(a in btn_list){
            a.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.white))
        }
    }

    fun highlight_button(idx: Int){
        val btn_list: List<Button> = get_buttons_list()
        whiten_buttons()
        btn_list[idx].setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.purple_200))
    }

    fun check_correctness(idx: Int): Boolean{
        return questionAnswer.answers[question_index][idx].equals(questionAnswer.correct[question_index])
    }
}

