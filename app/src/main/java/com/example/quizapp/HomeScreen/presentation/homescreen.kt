package com.example.quizapp.HomeScreen.presentation

import android.icu.text.CaseMap
import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(){

    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text("app")},
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Icon(Icons.Default.Search , contentDescription = "Scearch")
            }
        }
    ) {
            innerPadding ->
        // This is the main content of your screen
        // You MUST apply the padding provided by the Scaffold
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = "This is the screen content."
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun prev() {
    homeScreen()
}