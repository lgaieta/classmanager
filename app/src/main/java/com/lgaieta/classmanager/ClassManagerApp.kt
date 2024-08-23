package com.lgaieta.classmanager

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun ClassManagerApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    ClassManagerNavHost(navController = navController, modifier = modifier)
}