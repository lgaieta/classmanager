package com.lgaieta.classmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.lgaieta.classmanager.services.FirebaseSessionManager
import com.lgaieta.classmanager.ui.BottomNavBarActions

enum class ClassManagerScreen {
    Register,
    Login,
    SubjectsList,
    NewSubject,
    EditSubject,
    SubjectDetails,
    Account,
    StudentsList,
    TaskList,
    NewTask,
    EditTask,
    TaskDetails,
    StudentDetails,
    EditStudent,
    NewStudent,
    AddStudents
}

fun getDefaultBottomNavBarActions(navController: NavHostController) = BottomNavBarActions(
    onSubjectsClick = { navController.navigate(ClassManagerScreen.SubjectsList.name) },
    onTasksClick = { navController.navigate(ClassManagerScreen.TaskList.name) },
    onStudentsClick = { navController.navigate(ClassManagerScreen.StudentsList.name) },
    onAccountClick = { navController.navigate(ClassManagerScreen.Account.name) }
)

@Composable
fun ClassManagerNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = if (FirebaseSessionManager().isLoggedIn()) ClassManagerScreen.SubjectsList.name else ClassManagerScreen.Login.name,
    ) {
        authNavigationScreens(navController)
        studentNavigationScreens(navController)
        taskNavigationScreens(navController)
        subjectNavigationScreens(navController)
    }
}