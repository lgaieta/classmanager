package com.lgaieta.classmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lgaieta.classmanager.students.ui.StudentsListScreen

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
            StudentsListScreen(
                modifier = modifier,
                bottomNavBarActions = getDefaultBottomNavBarActions(navController)
            )
        }
    }
}