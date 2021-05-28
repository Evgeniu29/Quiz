package com.genius.quiz


import com.google.gson.annotations.SerializedName

class QuestionList {

 @SerializedName("questions")
 var questionList: ArrayList<Question> = ArrayList()

}
