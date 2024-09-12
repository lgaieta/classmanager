package com.lgaieta.classmanager.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lgaieta.classmanager.ui.navigation.ClassManagerNavHost

@Composable
fun ClassManagerApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    ClassManagerNavHost(navController = navController, modifier = modifier)
}