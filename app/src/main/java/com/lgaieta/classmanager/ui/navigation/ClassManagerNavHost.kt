package com.lgaieta.classmanager.ui.navigation

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
import com.lgaieta.classmanager.ui.BottomNavBarActions

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
        taskNavigationScreens(navController)
        subjectNavigationScreens(navController)
    }
}