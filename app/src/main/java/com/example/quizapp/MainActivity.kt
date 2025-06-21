package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.ViewModel.QuizViewModel
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.data.Question


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
    val selectedIndex = viewModel.selectedIndex.value
    val score = viewModel.score
    Log.d("selectedIndex", selectedIndex.toString())
    Column(

        modifier = Modifier.fillMaxSize().background(color = Color.Magenta),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column  {
            if(question?.id ==  null){
                Text("this q is over ")
                Text("and your score is $score")
            }
            Text(text = question?.question?: " ")
                      question?.options?.forEachIndexed{ index , option ->
                Button(onClick = {viewModel.answerSelect(index)})
                {
                    Text(text = option )
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun pre() {
    val viewModel = QuizViewModel()
    QuizApp(viewModel)
    
}