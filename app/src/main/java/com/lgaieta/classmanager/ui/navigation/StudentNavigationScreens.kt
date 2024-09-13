package com.lgaieta.classmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lgaieta.classmanager.ClassManagerApplication
import com.lgaieta.classmanager.students.ui.list.StudentsListScreen
import com.lgaieta.classmanager.students.ui.list.StudentsListViewModel
import com.lgaieta.classmanager.ui.viewModelFactory

fun NavGraphBuilder.studentNavigationScreens(navController: NavHostController) {
    composable(route = ClassManagerScreen.StudentsList.name) {
        StudentNavigationScreens.StudentsListScreenInitializer(
            navController = navController,
        )
    }
}

class StudentNavigationScreens {
    companion object {
        @Composable
        fun StudentsListScreenInitializer(
            navController: NavHostController,
            modifier: Modifier = Modifier
        ) {
            val studentsListViewModel =
                viewModel<StudentsListViewModel>(factory = viewModelFactory {
                    StudentsListViewModel(
                        offlineStudentRepository =
                        ClassManagerApplication.studentModelsContainer.offlineStudentRepository,
                    )
                })

            StudentsListScreen(
                modifier = modifier,
                bottomNavBarActions = getDefaultBottomNavBarActions(navController),
                studentsListViewModel = studentsListViewModel
            )
        }
    }
}