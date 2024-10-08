package com.lgaieta.classmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.lgaieta.classmanager.ui.BottomNavBarActions

enum class ClassManagerScreen {
    SubjectsList,
    NewSubject,
    NewSubjectTime,
    EditSubject,
    SubjectDetails,
    Home,
    StudentsList,
    TaskList,
    NewTask,
    EditTask,
    TaskDetails,
    StudentDetails,
    EditStudent,
    NewStudent
}

fun getDefaultBottomNavBarActions(navController: NavHostController) = BottomNavBarActions(
    onHomeClick = { navController.navigate(ClassManagerScreen.Home.name) },
    onSubjectsClick = { navController.navigate(ClassManagerScreen.SubjectsList.name) },
    onStudentsClick = { navController.navigate(ClassManagerScreen.StudentsList.name) }
)

@Composable
fun ClassManagerNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = ClassManagerScreen.SubjectsList.name,
    ) {
        studentNavigationScreens(navController)
        taskNavigationScreens(navController)
        subjectNavigationScreens(navController)
    }
}