package com.lgaieta.classmanager.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lgaieta.classmanager.home.ui.HomeScreen
import com.lgaieta.classmanager.register.ui.RegisterScreen
import com.lgaieta.classmanager.students.ui.StudentsListScreen
import com.lgaieta.classmanager.tasks.ui.list.TasksListScreen
import com.lgaieta.classmanager.ui.navigation.SUBJECT_ID_ARGUMENT
import com.lgaieta.classmanager.ui.navigation.SubjectNavigationScreens
import com.lgaieta.classmanager.ui.navigation.TASK_ID_ARGUMENT
import com.lgaieta.classmanager.ui.navigation.TaskNavigationScreens
import com.lgaieta.classmanager.ui.navigation.subjectNavigationScreens

enum class ClassManagerScreen {
    SubjectsList,
    NewSubject,
    NewSubjectTime,
    EditSubject,
    SubjectDetails,
    Register,
    Home,
    StudentsList,
    TaskList,
    NewTask,
    EditTask,
    TaskDetails,


}

fun getDefaultBottomNavBarActions(navController: NavHostController) = BottomNavBarActions(
    onHomeClick = { navController.navigate(ClassManagerScreen.TaskList.name) },
    onSubjectsClick = { navController.navigate(ClassManagerScreen.SubjectsList.name) },
    onStudentsClick = { navController.navigate(ClassManagerScreen.StudentsList.name) }
)

@Composable
fun ClassManagerNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = ClassManagerScreen.SubjectsList.name,
    ) {
        composable(route = ClassManagerScreen.Home.name) {
            HomeScreen(
                modifier = modifier,
                bottomNavBarActions = getDefaultBottomNavBarActions(navController)
            )
        }
        composable(route = ClassManagerScreen.StudentsList.name) {
            StudentsListScreen(
                modifier = modifier,
                bottomNavBarActions = getDefaultBottomNavBarActions(navController)
            )
        }
        composable(route = ClassManagerScreen.Register.name) {
            RegisterScreen(
                modifier = modifier,
                onRegister = {
                    navController.navigate(ClassManagerScreen.SubjectsList.name)
                }
            )
        }
        composable(route = ClassManagerScreen.SubjectsList.name) {
            SubjectNavigationScreens.SubjectsListScreenInitializer(
                navController = navController,
                modifier = modifier
            )
        }
        composable(
            route = "${ClassManagerScreen.SubjectDetails.name}/{${SUBJECT_ID_ARGUMENT}}",
            arguments = listOf(navArgument(SUBJECT_ID_ARGUMENT) { type = NavType.IntType })
        ) { backStackEntry ->
            SubjectNavigationScreens.SubjectDetailsScreenInitializer(
                navController = navController,
                backStackEntry = backStackEntry,
                modifier = modifier
            )
        }
        composable(
            route = "${ClassManagerScreen.EditSubject.name}/{${SUBJECT_ID_ARGUMENT}}",
            arguments = listOf(navArgument(SUBJECT_ID_ARGUMENT) { type = NavType.IntType })
        ) { backStackEntry ->
            SubjectNavigationScreens.EditSubjectScreenInitializer(
                navController = navController,
                backStackEntry = backStackEntry,
                modifier = modifier
            )
        }
        composable(route = ClassManagerScreen.NewSubject.name) {
            SubjectNavigationScreens.NewSubjectScreenInitializer(navController = navController)
        }


        composable(route = ClassManagerScreen.TaskList.name) {
            TaskNavigationScreens.TasksListScreenInitializer(
                navController = navController,
                modifier = modifier
            )
        }
        composable(
            route = "${ClassManagerScreen.TaskDetails.name}/{${TASK_ID_ARGUMENT}}",
            arguments = listOf(navArgument(TASK_ID_ARGUMENT) { type = NavType.IntType })
        ) { backStackEntry ->
            TaskNavigationScreens.TaskDetailsScreenInitializer(
                navController = navController,
                backStackEntry = backStackEntry,
                modifier = modifier
            )
        }
        composable(
            route = "${ClassManagerScreen.EditTask.name}/{${TASK_ID_ARGUMENT}}",
            arguments = listOf(navArgument(TASK_ID_ARGUMENT) { type = NavType.IntType })
        ) { backStackEntry ->
            TaskNavigationScreens.EditTaskScreenInitializer(
                navController = navController,
                backStackEntry = backStackEntry,
                modifier = modifier
            )
        }
        composable(route = ClassManagerScreen.NewTask.name) {
            TaskNavigationScreens.NewTaskScreenInitializer(navController = navController)
        }
        subjectNavigationScreens(navController)
    }
}