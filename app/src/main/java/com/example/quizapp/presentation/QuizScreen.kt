package com.example.quizapp.presentation.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.ViewModel.QuizViewModel

@Composable
fun QuizScreen(viewModel: QuizViewModel) {
    val question = viewModel.currentQuestion.value
    val timer = viewModel.timeLeft.value
    val isQuizRunning = viewModel._isQuizGoingon.value

    LaunchedEffect(Unit) {
        viewModel.startTimer()
    }

    if (!isQuizRunning) {
        ResultScreen(viewModel)
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question?.question ?: "",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Time left: $timer",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        question?.options?.forEachIndexed { index, option ->
            Button(
                onClick = { viewModel.answerSelect(index) },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = option)
            }
        }
    }
}

@Composable
fun ResultScreen(viewModel: QuizViewModel) {
    val score = viewModel.score
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is your result", fontSize = 36.sp)
        Text("Your score: $score", fontSize = 28.sp)
    }
}
