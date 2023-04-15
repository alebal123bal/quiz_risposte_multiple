package com.example.quiz_risposte_multiple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat


//This class implements View.OnClickListener just to override its clicking method
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var questionIndex : Int = 0
    private var toggleCorrect: Boolean = false
    private var score: Int = 0

    private var clickedStack = ArrayDeque<Int>(5)
    private var lastClickedAnswer: Int = 0
    private var enableSubmit: Boolean = false

    private lateinit var allTextboxes: List<TextView>
    private lateinit var allButtons: List<TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //List of all textboxes
        allTextboxes = listOf(
            findViewById(R.id.question_number),
            findViewById(R.id.question_id))

        //List of all five possible buttons
        allButtons = listOf(
            findViewById(R.id.answer1),
            findViewById(R.id.answer2),
            findViewById(R.id.answer3),
            findViewById(R.id.answer4),
            findViewById(R.id.submit_id),
            findViewById(R.id.back_button))

        for(a in allButtons) {
            a.setOnClickListener(this)
        }
        whitenButtons()
        setupText(questionIndex)
    }

    override fun onClick(view: View){
        when(view.id){
            R.id.answer1 -> {
                enableSubmit = true
                lastClickedAnswer = 0
                treatClickedButton(0)
            }
            R.id.answer2 -> {
                enableSubmit = true
                lastClickedAnswer = 1
                treatClickedButton(1)
            }
            R.id.answer3 -> {
                enableSubmit = true
                lastClickedAnswer = 2
                treatClickedButton(2)
            }
            R.id.answer4 -> {
                enableSubmit = true
                lastClickedAnswer = 3
                treatClickedButton(3)
            }
            R.id.submit_id -> {
                if(enableSubmit){
                    clickedStack.addLast(lastClickedAnswer)

                    if(toggleCorrect){
                        score++
                    }
                    if(questionIndex < questionAnswer.question.size-1){
                        questionIndex++
                        whitenButtons()
                        setupText(questionIndex)
                    }
                    else{
                        val intent = Intent(this, LastActivity::class.java)
                        startActivity(intent)
                    }
                    enableSubmit = false
                }
            }
            R.id.back_button->{
                if(questionIndex>0){
                    enableSubmit = false
                    questionIndex--
                    val popped = clickedStack.removeLast()
                    //If last time you hit correctly, and you wanna redo, I remove one point of score
                    //No decurting points if you did not answer correctly
                    //Una volta che ho clickato submit, mi sono salvato l'ultimo bottone valido
                    //che è stato selezionato tra i quattro disponibili
                    //Essi hanno un id crescente uno dopo l'altro
                    if(popped == questionAnswer.correct_idx[questionIndex]){
                        score-=1
                    }
                    whitenButtons()
                    highlightButton(popped, ContextCompat.getColor(applicationContext, R.color.gray))
                    setupText(questionIndex)
                }
            }
        }
    }

    private fun treatClickedButton(idx: Int){
        highlightButton(idx, ContextCompat.getColor(applicationContext, R.color.purple_200))
        toggleCorrect = false
        if(checkCorrectness(idx)){
            toggleCorrect = true
        }
    }

    private fun setupText(idx: Int){
        val nQ = idx + 1
        allTextboxes[0].text = "Question $nQ score $score"
        allTextboxes[1].text = questionAnswer.question[idx]
        for(i: Int in 0..3){
            allButtons[i].text = questionAnswer.answers[idx][i]
        }
    }

    private fun getButtonsList(): List<Button> {
        //This list does not contain Back button, only 4 answer buttons
        return listOf(
            allButtons[0] as Button,
            allButtons[1] as Button,
            allButtons[2] as Button,
            allButtons[3] as Button
        )
    }

    private fun whitenButtons(){
        //Whitens only 4 answer buttons
        //val btnList: List<Button> = getButtonsList()
        for(a in getButtonsList()){
            a.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.white))
        }
    }

    private fun highlightButton(idx: Int, colorId: Int){
        val btnList: List<Button> = getButtonsList()
        whitenButtons()
        btnList[idx].setBackgroundColor(colorId)
    }

    private fun checkCorrectness(idx: Int): Boolean{
        return questionAnswer.answers[questionIndex][idx].equals(questionAnswer.correct[questionIndex])
    }

    companion object {
        const val TAG: String = "MainActivity"
    }
}

