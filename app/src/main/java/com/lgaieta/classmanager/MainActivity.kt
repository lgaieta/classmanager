package com.lgaieta.classmanager

import ClassManagerTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.lgaieta.classmanager.ui.ClassManagerApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClassManagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ClassManagerApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}