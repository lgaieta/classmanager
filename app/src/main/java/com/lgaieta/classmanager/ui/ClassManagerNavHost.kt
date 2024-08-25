package com.lgaieta.classmanager.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lgaieta.classmanager.ClassManagerApplication
import com.lgaieta.classmanager.register.ui.RegisterScreen
import com.lgaieta.classmanager.subjects.ui.details.SubjectDetailsScreen
import com.lgaieta.classmanager.subjects.ui.details.SubjectDetailsViewModel
import com.lgaieta.classmanager.subjects.ui.list.SubjectsListScreen
import com.lgaieta.classmanager.subjects.ui.list.SubjectsListViewModel
import com.lgaieta.classmanager.subjects.ui.new.NewSubjectScreen
import com.lgaieta.classmanager.subjects.ui.new.NewSubjectViewModel

enum class ClassManagerScreen {
    SubjectsList,
    NewSubject,
    SubjectDetails,
    Login,
    Register,
    Home,
}

private const val SUBJECT_ID_ARGUMENT = "id"

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
                onNewSubjectClick = { navController.navigate(ClassManagerScreen.NewSubject.name) },
                onSubjectClick = { id -> navController.navigate("${ClassManagerScreen.SubjectDetails.name}/${id}") }
            )
        }
        composable(
            route = "${ClassManagerScreen.SubjectDetails.name}/{${SUBJECT_ID_ARGUMENT}}",
            arguments = listOf(navArgument(SUBJECT_ID_ARGUMENT) { type = NavType.IntType })
        ) { backStackEntry ->
            val subjectId =
                backStackEntry.arguments?.getInt(SUBJECT_ID_ARGUMENT) ?: return@composable

            val subjectDetailsViewModel =
                viewModel<SubjectDetailsViewModel>(factory = viewModelFactory {
                    SubjectDetailsViewModel(
                        offlineSubjectRepository =
                        ClassManagerApplication.subjectModelsContainer.offlineSubjectRepository,
                        subjectId = subjectId,
                    )
                })

            SubjectDetailsScreen(
                modifier = modifier,
                subjectDetailsViewModel = subjectDetailsViewModel
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