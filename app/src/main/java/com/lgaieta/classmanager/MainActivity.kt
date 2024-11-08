package com.lgaieta.classmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lgaieta.classmanager.ui.ClassManagerApp
import com.lgaieta.classmanager.ui.theme.ClassManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClassManagerTheme {
                ClassManagerApp()
            }
        }
    }
}