package com.lgaieta.classmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lgaieta.classmanager.ClassManagerApplication
import com.lgaieta.classmanager.students.ui.details.StudentDetailsScreen
import com.lgaieta.classmanager.students.ui.details.StudentDetailsViewModel
import com.lgaieta.classmanager.students.ui.edit.EditStudentScreen
import com.lgaieta.classmanager.students.ui.edit.EditStudentViewModel
import com.lgaieta.classmanager.students.ui.list.StudentsListScreen
import com.lgaieta.classmanager.students.ui.list.StudentsListViewModel
import com.lgaieta.classmanager.students.ui.new.NewStudentScreen
import com.lgaieta.classmanager.students.ui.new.NewStudentViewModel
import com.lgaieta.classmanager.ui.viewModelFactory

const val STUDENT_ID_ARGUMENT = "id"

fun NavGraphBuilder.studentNavigationScreens(navController: NavHostController) {
    composable(
        route = "${ClassManagerScreen.StudentDetails.name}/{$STUDENT_ID_ARGUMENT}",
        arguments = listOf(navArgument(STUDENT_ID_ARGUMENT) {
            type = androidx.navigation.NavType.LongType
        })
    ) { backStackEntry ->
        StudentNavigationScreens.StudentDetailsScreenInitializer(
            navController = navController,
            backStackEntry = backStackEntry,
        )
    }
    composable(route = ClassManagerScreen.StudentsList.name) {
        StudentNavigationScreens.StudentsListScreenInitializer(
            navController = navController,
        )
    }
    composable(route = ClassManagerScreen.NewStudent.name) {
        StudentNavigationScreens.NewStudentScreenInitializer(navController = navController)
    }
    composable(
        route = "${ClassManagerScreen.EditStudent.name}/{$STUDENT_ID_ARGUMENT}",
        arguments = listOf(navArgument(STUDENT_ID_ARGUMENT) {
            type = androidx.navigation.NavType.LongType
        })
    ) { backstackEntry ->
        StudentNavigationScreens.EditStudentScreenInitializer(
            navController = navController,
            backStackEntry = backstackEntry
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
                onNewStudentClick = { navController.navigate(ClassManagerScreen.NewStudent.name) },
                onStudentClick = { id -> navController.navigate("${ClassManagerScreen.StudentDetails.name}/${id}") },
                studentsListViewModel = studentsListViewModel
            )
        }

        @Composable
        fun StudentDetailsScreenInitializer(
            navController: NavHostController,
            backStackEntry: NavBackStackEntry,
        ) {
            val studentId =
                backStackEntry.arguments?.getLong(SUBJECT_ID_ARGUMENT) ?: return

            val studentDetailsViewModel =
                viewModel<StudentDetailsViewModel>(factory = viewModelFactory {
                    StudentDetailsViewModel(
                        offlineStudentRepository =
                        ClassManagerApplication.studentModelsContainer.offlineStudentRepository,
                        studentId = studentId,
                        afterDelete = { navController.popBackStack() },
                        afterEdit = { id -> navController.navigate("${ClassManagerScreen.EditStudent.name}/$id") }
                    )
                })

            StudentDetailsScreen(
                studentDetailsViewModel = studentDetailsViewModel,
                bottomNavBarActions = getDefaultBottomNavBarActions(navController)
            )
        }

        @Composable
        fun NewStudentScreenInitializer(
            navController: NavHostController,
            modifier: Modifier = Modifier
        ) {
            val newStudentViewModel =
                viewModel<NewStudentViewModel>(factory = viewModelFactory {
                    NewStudentViewModel(
                        offlineStudentRepository = ClassManagerApplication.studentModelsContainer.offlineStudentRepository,
                        offlineSubjectRepository = ClassManagerApplication.subjectModelsContainer.offlineSubjectRepository
                    )
                })

            NewStudentScreen(
                modifier = modifier,
                bottomNavBarActions = getDefaultBottomNavBarActions(navController),
                newStudentViewModel = newStudentViewModel,
                afterCreate = { navController.popBackStack() }
            )
        }

        @Composable
        fun EditStudentScreenInitializer(
            backStackEntry: NavBackStackEntry,
            navController: NavHostController
        ) {
            val studentId = backStackEntry.arguments?.getLong(STUDENT_ID_ARGUMENT) ?: return

            val editStudentViewModel = viewModel<EditStudentViewModel>(factory = viewModelFactory {
                EditStudentViewModel(
                    offlineStudentRepository =
                    ClassManagerApplication.studentModelsContainer.offlineStudentRepository,
                    offlineSubjectRepository = ClassManagerApplication.subjectModelsContainer.offlineSubjectRepository,
                    studentId = studentId,
                    afterEdit = { navController.popBackStack() }
                )
            })

            EditStudentScreen(
                editStudentViewModel = editStudentViewModel,
                bottomNavBarActions = getDefaultBottomNavBarActions(navController)
            )
        }
    }
}