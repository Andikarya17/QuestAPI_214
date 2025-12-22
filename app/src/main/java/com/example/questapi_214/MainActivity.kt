package com.example.questapi_214

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.questapi_214.ui.theme.QuestAPI_214Theme
import com.example.questapi_214.view.uicontroller.DataSiswaApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuestAPI_214Theme {
                DataSiswaApp()
            }
        }
    }
}