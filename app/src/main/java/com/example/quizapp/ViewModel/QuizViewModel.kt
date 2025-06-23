package com.example.quizapp.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.QuizApp
import com.example.quizapp.data.Question
import com.example.quizapp.resultScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class QuizViewModel : ViewModel() {

    private val _questions = listOf(
        Question(1, "What is the capital of France?", listOf("Paris", "London", "Berlin", "Madrid"), 0),
        Question(2, "What is the capital of Germany?", listOf("Berlin", "Munich", "Frankfurt", "Hamburg"), 0)
    )
    private var currentIndex = 0

    private val _timeLeft = mutableStateOf(10)
    val timeLeft: State<Int> = _timeLeft

    /**
         * Holds the mutable state indicating whether the quiz is ongoing.
         * Used to control the quiz flow and UI updates.
         */
        private var _isQuizGoingOn = mutableStateOf(true)


        /**
         * Exposes the quiz ongoing state as an immutable State to observers.
         * UI components should observe this property for changes.
         */
        val _isQuizGoingon: State<Boolean>  get() = _isQuizGoingOn


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
        }
        currentIndex++
        currentQuestion.value =  _questions.getOrNull(currentIndex)
        if (currentQuestion.value == null) {
            _isQuizGoingOn.value = false
        }

    }
    fun startTimer(){
        viewModelScope.launch {
            while (_timeLeft.value > 0){
                delay(1000L)
                _timeLeft.value -= 1
            }
            _isQuizGoingOn.value = false
        }
    }



}
