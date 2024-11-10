package com.lgaieta.classmanager.ui.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lgaieta.classmanager.auth.ui.login.LoginScreen
import com.lgaieta.classmanager.auth.ui.register.LoginViewModel
import com.lgaieta.classmanager.services.FirebaseSessionManager
import com.lgaieta.classmanager.auth.ui.register.RegisterScreen
import com.lgaieta.classmanager.auth.ui.register.RegisterViewModel
import com.lgaieta.classmanager.ui.viewModelFactory

fun NavGraphBuilder.authNavigationScreens(navController: NavHostController) {
    composable(route = ClassManagerScreen.Register.name) {
        if (FirebaseSessionManager().isLoggedIn()) navController.navigate(ClassManagerScreen.SubjectsList.name)
        val registerViewModel: RegisterViewModel = viewModel(factory = viewModelFactory {
            RegisterViewModel(
                sessionManager = FirebaseSessionManager(),
                afterRegister = {
                    navController.navigate(
                        ClassManagerScreen.SubjectsList.name
                    )
                },
                onLoginClick = { navController.navigate(ClassManagerScreen.Login.name) }
            )
        })
        RegisterScreen(registerViewModel = registerViewModel)
    }
    composable(route = ClassManagerScreen.Login.name) {
        if (FirebaseSessionManager().isLoggedIn()) navController.navigate(ClassManagerScreen.SubjectsList.name)
        val loginViewModel: LoginViewModel = viewModel(factory = viewModelFactory {
            LoginViewModel(
                sessionManager = FirebaseSessionManager(),
                afterLogin = {
                    navController.navigate(
                        ClassManagerScreen.SubjectsList.name
                    )
                },
                onRegisterClick = {
                    navController.navigate(
                        ClassManagerScreen.Register.name
                    )
                })
        })
        LoginScreen(loginViewModel = loginViewModel)
    }
}