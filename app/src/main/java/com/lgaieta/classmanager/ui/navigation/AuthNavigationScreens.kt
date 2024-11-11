package com.lgaieta.classmanager.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lgaieta.classmanager.auth.ui.account.AccountScreen
import com.lgaieta.classmanager.auth.ui.account.AccountViewModel
import com.lgaieta.classmanager.auth.ui.login.LoginScreen
import com.lgaieta.classmanager.auth.ui.register.LoginViewModel
import com.lgaieta.classmanager.services.FirebaseSessionManager
import com.lgaieta.classmanager.auth.ui.register.RegisterScreen
import com.lgaieta.classmanager.auth.ui.register.RegisterViewModel
import com.lgaieta.classmanager.ui.viewModelFactory

fun NavGraphBuilder.authNavigationScreens(navController: NavHostController) {
    composable(route = ClassManagerScreen.Register.name) {
        val sessionManager = FirebaseSessionManager()
        if (sessionManager.isLoggedIn()) {
            LaunchedEffect(Unit) {
                navController.navigate(ClassManagerScreen.SubjectsList.name)
            }
        } else {
            val registerViewModel: RegisterViewModel = viewModel(factory = viewModelFactory {
                RegisterViewModel(
                    sessionManager = sessionManager,
                    afterRegister = {
                        navController.navigate(ClassManagerScreen.SubjectsList.name)
                    },
                    onLoginClick = { navController.navigate(ClassManagerScreen.Login.name) }
                )
            })
            RegisterScreen(registerViewModel = registerViewModel)
        }
    }
    composable(route = ClassManagerScreen.Login.name) {
        val sessionManager = FirebaseSessionManager()
        if (sessionManager.isLoggedIn()) {
            LaunchedEffect(Unit) {
                navController.navigate(ClassManagerScreen.SubjectsList.name)
            }
        } else {
            val loginViewModel: LoginViewModel = viewModel(factory = viewModelFactory {
                LoginViewModel(
                    sessionManager = sessionManager,
                    afterLogin = {
                        navController.navigate(ClassManagerScreen.SubjectsList.name)
                    },
                    onRegisterClick = {
                        navController.navigate(ClassManagerScreen.Register.name)
                    }
                )
            })
            LoginScreen(loginViewModel = loginViewModel)
        }
    }

    composable(route = ClassManagerScreen.Account.name) {
        val accountViewModel: AccountViewModel = viewModel(factory = viewModelFactory {
            AccountViewModel(
                sessionManager = FirebaseSessionManager(),
                afterLogout = { navController.navigate(ClassManagerScreen.Login.name) }
            )
        })

        AccountScreen(
            bottomNavBarActions = getDefaultBottomNavBarActions(navController),
            accountViewModel = accountViewModel
        )
    }
}