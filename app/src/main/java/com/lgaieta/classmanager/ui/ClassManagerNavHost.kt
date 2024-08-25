package com.lgaieta.classmanager.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lgaieta.classmanager.ClassManagerApplication
import com.lgaieta.classmanager.register.ui.RegisterScreen
import com.lgaieta.classmanager.subjects.ui.list.SubjectsListScreen
import com.lgaieta.classmanager.subjects.ui.list.SubjectsListViewModel
import com.lgaieta.classmanager.subjects.ui.new.NewSubjectScreen
import com.lgaieta.classmanager.subjects.ui.new.NewSubjectViewModel

enum class ClassManagerScreen {
    SubjectsList,
    NewSubject,
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
                onRegister = {
                    navController.navigate(ClassManagerScreen.SubjectsList.name)
                }
            )
        }
        composable(route = ClassManagerScreen.SubjectsList.name) {
            val subjectsListViewModel =
                viewModel<SubjectsListViewModel>(factory = viewModelFactory {
                    SubjectsListViewModel(
                        offlineSubjectRepository = ClassManagerApplication.subjectModelsContainer.offlineSubjectRepository
                    )
                })
            SubjectsListScreen(
                modifier = modifier,
                subjectsListViewModel = subjectsListViewModel,
                onNewSubjectClick = { navController.navigate(ClassManagerScreen.NewSubject.name) }
            )
        }
        composable(route = ClassManagerScreen.NewSubject.name) {
            val newSubjectViewModel =
                viewModel<NewSubjectViewModel>(factory = viewModelFactory {
                    NewSubjectViewModel(
                        offlineSubjectRepository = ClassManagerApplication.subjectModelsContainer.offlineSubjectRepository
                    )
                })
            NewSubjectScreen(
                modifier = modifier,
                newSubjectViewModel = newSubjectViewModel,
                afterCreate = { navController.navigate(ClassManagerScreen.SubjectsList.name) }
            )
        }
    }
}