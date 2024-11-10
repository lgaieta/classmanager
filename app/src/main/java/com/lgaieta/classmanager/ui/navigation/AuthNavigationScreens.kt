package com.lgaieta.classmanager.ui.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lgaieta.classmanager.models.SessionManager
import com.lgaieta.classmanager.register.ui.RegisterScreen
import com.lgaieta.classmanager.register.ui.RegisterViewModel
import com.lgaieta.classmanager.ui.viewModelFactory


fun NavGraphBuilder.authNavigationScreens(navController: NavHostController) {
    composable(route = ClassManagerScreen.Register.name) {
        val registerViewModel: RegisterViewModel = viewModel(factory = viewModelFactory {
            RegisterViewModel(
                sessionManager = SessionManager(),
                afterRegister = {
                    navController.navigate(
                        ClassManagerScreen.SubjectsList.name
                    )
                })
        })
        RegisterScreen(registerViewModel = registerViewModel)
    }
}