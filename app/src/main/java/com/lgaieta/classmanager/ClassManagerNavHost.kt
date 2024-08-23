package com.lgaieta.classmanager

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lgaieta.classmanager.subjects.ui.CreateSubjectScreen

enum class ClassManagerScreen {
    SubjectsList,
    CreateSubject,
    Login,
    Register,
    Home,
}

@Composable
fun ClassManagerNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = ClassManagerScreen.Register.name
    ) {
        composable(route = ClassManagerScreen.Register.name) {
            RegisterScreen(
                modifier = modifier,
                onRegister = { navController.navigate(ClassManagerScreen.CreateSubject.name) })
        }
        composable(route = ClassManagerScreen.CreateSubject.name) {
            CreateSubjectScreen(modifier = modifier)
        }
    }
}