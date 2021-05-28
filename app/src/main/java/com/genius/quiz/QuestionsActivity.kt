package com.genius.quiz

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.questions.*
import java.io.InputStream

class QuestionsActivity : AppCompatActivity() {

    var number = 0

    var num_correct = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.questions)

        //load data from Json in assets folder
        var questions = Gson().fromJson(loadJson(this), QuestionList::class.java)


        //put data into UI
        fillTextViews(questions, number)

        val texts = ArrayList<TextView>()
        texts.add(optionsOne)
        texts.add(optionsTwo)

        texts.add(optionsThree)
        texts.add(optionsFourth)

        //set on click  methods,  process the "selection" of "answers"
        for (i in 0..3) {
            val idString = "TextView$i"
            val buttonID = resources.getIdentifier(idString, "id", packageName)

            texts[i].setOnClickListener() {
                val correct = questions.questionList[number].correctAnswerIndex

                if (i.equals(correct)) {

                    texts[i].setTextColor(Color.parseColor("#00ff00"))

                    num_correct++


                } else {
                    texts[i].setTextColor(Color.parseColor("#ff0000"))


                }


                for (i in 0..3) {
                    texts[i].setClickable(false);


                }

                number++

            }
        }

        //updated questions
        submit.setOnClickListener {

            if (number < questions.questionList.size) {


                for (i in 0..3)

                    texts[i].setClickable(true);

                for (j in 0..3)

                    texts[j].setTextColor(Color.parseColor("#000000"))


                fillTextViews(questions, number)

            } else {


                val intent = Intent(this, ResultActivity::class.java)

                intent.putExtra("correct", num_correct)

                startActivity(intent)

                finish()


            }

        }

    }

    //fill Text Views
    private fun fillTextViews(questions: QuestionList, number: Int) {

        if (questions != null) {

            question.text = ""

            question.text =
                    questions.questionList.get(number).questionText

            optionsOne.text = ""

            optionsOne.text = questions.questionList.get(number).answersList.get(0).answerText

            optionsTwo.text = ""

            optionsTwo.text = questions.questionList.get(number).answersList.get(1).answerText

            optionsThree.text = ""

            optionsThree.text = questions.questionList.get(number).answersList.get(2).answerText

            optionsFourth.text = ""

            optionsFourth.text = questions.questionList.get(number).answersList.get(3).answerText

        }
    }

    private fun loadJson(context: Context): String? {
        var input: InputStream? = null
        var jsonString: String

        try {
            // Create InputStream
            input = context.assets.open("Questions.json")

            val size = input.available()

            // Create a buffer with the size
            val buffer = ByteArray(size)

            // Read data from InputStream into the Buffer
            input.read(buffer)

            // Create a json String
            jsonString = String(buffer)

            return jsonString;

        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            // Must close the stream
            input?.close()
        }

        return null
    }

}