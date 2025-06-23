
package com.example.quizapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizapp.ViewModel.QuizViewModel
import com.example.quizapp.presentation.quiz.QuizScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: QuizViewModel = viewModel()
            QuizScreen(viewModel)
        }
    }
}