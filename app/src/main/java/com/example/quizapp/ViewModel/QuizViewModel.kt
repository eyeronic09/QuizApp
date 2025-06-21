package com.example.quizapp.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.quizapp.data.Question


class QuizViewModel : ViewModel() {

    private val _questions = listOf(
        Question(1, "What is the capital of France?", listOf("Paris", "London", "Berlin", "Madrid"), 0),
        Question(2, "What is the capital of Germany?", listOf("Berlin", "Munich", "Frankfurt", "Hamburg"), 0)
    )
    private var currentIndex = 0

     var selectedIndex = mutableStateOf(-1)
        private set


    var score = 0
        private set

    /**
     * this comment is about currentQuestion variable
     * Holds the current question as a mutable state.
     * The value is updated as the user progresses through the quiz.
     * Initialized with the first question or null if the list is empty.
     * When its value changes, the UI automatically updates (recomposes).
     *
     */
    var currentQuestion = mutableStateOf<Question?>(_questions.getOrNull(0))
        private set

    /**
     * Handles the user's answer selection.
     * Checks if the selected answer is correct for the current question.
     * If correct, increments the score and advances to the next question.
     * Updates the current question state and logs a debug message.
     *
     * @param selected The index of the selected answer.
     */
    fun answerSelect(selected: Int){
        val question = currentQuestion.value
        if(question != null && selected == question.correctAnswerIndex){
            score++
            currentIndex++
            currentQuestion.value = _questions.getOrNull(currentIndex)
        }else if(question != null && selected != question.correctAnswerIndex){
            currentIndex++
            currentQuestion.value = _questions.getOrNull(currentIndex)
        }

    }


}
