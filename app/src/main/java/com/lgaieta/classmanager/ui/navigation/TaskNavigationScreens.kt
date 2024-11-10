package com.lgaieta.classmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lgaieta.classmanager.ClassManagerApplication
import com.lgaieta.classmanager.tasks.ui.details.TaskDetailsScreen
import com.lgaieta.classmanager.tasks.ui.details.TaskDetailsViewModel
import com.lgaieta.classmanager.tasks.ui.edit.EditTaskScreen
import com.lgaieta.classmanager.tasks.ui.edit.EditTaskViewModel
import com.lgaieta.classmanager.tasks.ui.list.TasksListScreen
import com.lgaieta.classmanager.tasks.ui.list.TasksListViewModel
import com.lgaieta.classmanager.tasks.ui.new.NewTaskScreen
import com.lgaieta.classmanager.tasks.ui.new.NewTaskViewModel
import com.lgaieta.classmanager.ui.viewModelFactory

const val TASK_ID_ARGUMENT = "id"

fun NavGraphBuilder.taskNavigationScreens(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(
        route = ClassManagerScreen.TaskList.name
    ) {backStackEntry ->
        TaskNavigationScreens.TasksListScreenInitializer(
            navController = navController,
            backStackEntry = backStackEntry,
            modifier = modifier,
        )
    }
    composable(
        route = "${ClassManagerScreen.TaskDetails.name}/{$TASK_ID_ARGUMENT}",
        arguments = listOf(navArgument(TASK_ID_ARGUMENT) {
            type = NavType.IntType
        })
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
    composable(
        route = "${ClassManagerScreen.NewTask.name}/{${TASK_ID_ARGUMENT}}",
        arguments = listOf(navArgument(TASK_ID_ARGUMENT) { type = NavType.IntType })
    ) { backStackEntry ->
        TaskNavigationScreens.NewTaskScreenInitializer(
            navController = navController,
            backStackEntry = backStackEntry,
        )
    }
}


class TaskNavigationScreens {
    companion object {
        @Composable
        fun TasksListScreenInitializer(
            navController: NavHostController,
            backStackEntry: NavBackStackEntry,
            modifier: Modifier = Modifier
        ) {
            val taskId =
                backStackEntry.arguments?.getInt(TASK_ID_ARGUMENT) ?: return

            val tasksListViewModel =
                viewModel<TasksListViewModel>(factory = viewModelFactory {
                    TasksListViewModel(
                        offlineTaskRepository = ClassManagerApplication.taskModelsContainer.offlineTaskRepository,
                        taskId = taskId
                        )
                })
            TasksListScreen(
                modifier = modifier,
                tasksListViewModel = tasksListViewModel,
                onNewTaskClick = { navController.navigate(ClassManagerScreen.NewTask.name) },
                onTaskClick = { id -> navController.navigate("${ClassManagerScreen.TaskDetails.name}/${id}") },
                bottomNavBarActions = getDefaultBottomNavBarActions(navController)
            )
        }

        @Composable
        fun TaskDetailsScreenInitializer(
            navController: NavHostController,
            backStackEntry: NavBackStackEntry,
            modifier: Modifier = Modifier
        ) {
            val taskId =
                backStackEntry.arguments?.getInt(TASK_ID_ARGUMENT) ?: return

            val taskDetailsViewModel =
                viewModel<TaskDetailsViewModel>(factory = viewModelFactory {
                    TaskDetailsViewModel(
                        offlineTaskRepository = ClassManagerApplication.taskModelsContainer.offlineTaskRepository,
                        offlineStudentRepository = ClassManagerApplication.studentModelsContainer.offlineStudentRepository,
                        offlineSubjectRepository = ClassManagerApplication.subjectModelsContainer.offlineSubjectRepository,
                        taskId = taskId,
                        afterDelete = { task -> navController.navigate("${ClassManagerScreen.SubjectDetails.name}/${task.subjectId}") },
                        afterEdit = { id -> navController.navigate("${ClassManagerScreen.EditTask.name}/${id}") }
                    )
                })

            TaskDetailsScreen(
                modifier = modifier,
                taskDetailsViewModel = taskDetailsViewModel,
                bottomNavBarActions = getDefaultBottomNavBarActions(navController)
            )
        }

        @Composable
        fun NewTaskScreenInitializer(
            navController: NavHostController,
            backStackEntry: NavBackStackEntry,
            modifier: Modifier = Modifier
        ) {
            val subjectId =
                backStackEntry.arguments?.getInt(SUBJECT_ID_ARGUMENT) ?: return

            val newTaskViewModel =
                viewModel<NewTaskViewModel>(factory = viewModelFactory {
                    NewTaskViewModel(
                        offlineTaskRepository = ClassManagerApplication.taskModelsContainer.offlineTaskRepository,
                        subjectId = subjectId,
                        afterCreate = { navController.navigate("${ClassManagerScreen.SubjectDetails.name}/$subjectId") },
                        afterCancel = { navController.popBackStack() }
                    )
                })


            NewTaskScreen(
                modifier = modifier,
                newTaskViewModel = newTaskViewModel,
                bottomNavBarActions = getDefaultBottomNavBarActions(navController)
            )
        }

        @Composable
        fun EditTaskScreenInitializer(
            navController: NavHostController,
            backStackEntry: NavBackStackEntry,
            modifier: Modifier = Modifier
        ) {
            val taskId =
                backStackEntry.arguments?.getInt(SUBJECT_ID_ARGUMENT) ?: return

            val editTaskViewModel =
                viewModel<EditTaskViewModel>(factory = viewModelFactory {
                    EditTaskViewModel(
                        offlineTaskRepository =
                        ClassManagerApplication.taskModelsContainer.offlineTaskRepository,
                        taskId = taskId,
                        afterEdit = { navController.popBackStack() },
                        afterCancel = { navController.popBackStack() }
                    )
                })

            EditTaskScreen(
                modifier = modifier,
                editTaskViewModel = editTaskViewModel,
                bottomNavBarActions = getDefaultBottomNavBarActions(navController)
            )
        }
    }
}
