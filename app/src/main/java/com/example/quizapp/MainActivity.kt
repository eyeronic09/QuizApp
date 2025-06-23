package com.example.quizapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.ViewModel.QuizViewModel
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = QuizViewModel()
            QuizApp(viewModel)

        }
    }


}

@Composable
fun QuizApp(viewModel: QuizViewModel) {
    val question = viewModel.currentQuestion.value
    val timer = viewModel.timeLeft.value
    val isQuizIsRuninng = viewModel._isQuizGoingon.value

    LaunchedEffect(Unit) {
        viewModel.startTimer()
    }
    if (isQuizIsRuninng) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .systemBarsPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = question?.question ?: " ",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your time left: $timer",
                    style = MaterialTheme.typography.titleLarge
                )
                question?.options?.forEachIndexed { index: Int, option: String ->
                    Button(
                        onClick = { viewModel.answerSelect(index) },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                    ) {
                        Text(text = option)
                    }
                }
            }

        }

    }
    if (!isQuizIsRuninng) {
        resultScreen(viewModel)
        return
    }
}

@Composable
fun resultScreen(viewModel: QuizViewModel) {
    var score = viewModel.score
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This your result", fontWeight = FontWeight.ExtraBold, fontSize = 50.sp)
        Text(text = "your is score: $score", fontSize = 50.sp)
    }

}

@Preview(showSystemUi = true)
@Composable
private fun pre() {
    val viewModel = QuizViewModel()
    QuizApp(viewModel)
}