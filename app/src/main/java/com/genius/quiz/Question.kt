package com.genius.quiz

import com.google.gson.annotations.SerializedName

class Question {
    @SerializedName("questionText")
    var questionText: String=""

    @SerializedName("correctAnswerIndex")
    var correctAnswerIndex: Int = 0

    @SerializedName("answers")
    var answersList: ArrayList<Answer> = ArrayList()

}