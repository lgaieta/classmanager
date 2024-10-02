package com.lgaieta.classmanager.ui.navigation


import EditTaskViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.lgaieta.classmanager.ClassManagerApplication
import com.lgaieta.classmanager.tasks.ui.details.TaskDetailsScreen
import com.lgaieta.classmanager.tasks.ui.details.TaskDetailsViewModel
import com.lgaieta.classmanager.tasks.ui.edit.EditTaskScreen
import com.lgaieta.classmanager.tasks.ui.list.TasksListScreen
import com.lgaieta.classmanager.tasks.ui.list.TasksListViewModel
import com.lgaieta.classmanager.tasks.ui.new.NewTaskScreen
import com.lgaieta.classmanager.tasks.ui.new.NewTaskViewModel
import com.lgaieta.classmanager.ui.ClassManagerScreen
import com.lgaieta.classmanager.ui.getDefaultBottomNavBarActions

const val TASK_ID_ARGUMENT = "id"

class TaskNavigationScreens {
    companion object {
        @Composable
        fun TasksListScreenInitializer(
            navController: NavHostController,
            modifier: Modifier = Modifier
        ) {
            val tasksListViewModel =
                viewModel<TasksListViewModel>(factory = viewModelFactory {
                    TasksListViewModel(
                        offlineTaskRepository = ClassManagerApplication.taskModelsContainer.offlineTaskRepository
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
                        offlineTaskRepository =
                        ClassManagerApplication.taskModelsContainer.offlineTaskRepository,
                        taskId = taskId,
                        afterDelete = { navController.navigate(ClassManagerScreen.TaskList.name) },
                        afterEdit = { id -> navController.navigate("${ClassManagerScreen.EditTask.name}/${id}") }
                    )
                })

            TaskDetailsScreen(
                modifier = modifier,
                taskDetailsViewModel = taskDetailsViewModel,
            )
        }

        @Composable
        fun NewTaskScreenInitializer(
            navController: NavHostController,
            modifier: Modifier = Modifier
        ) {
            val newTaskViewModel =
                viewModel<NewTaskViewModel>(factory = viewModelFactory {
                    NewTaskViewModel(
                        offlineTaskRepository = ClassManagerApplication.taskModelsContainer.offlineTaskRepository
                    )
                })

            NewTaskScreen(
                modifier = modifier,
                newTaskViewModel = newTaskViewModel,
                afterCreate = { navController.navigate(ClassManagerScreen.TaskList.name) }
            )
        }

        @Composable
        fun EditTaskScreenInitializer(
            navController: NavController,
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
                        afterEdit = { navController.popBackStack() }
                    )
                })

            EditTaskScreen(
                modifier = modifier,
                editTaskViewModel = editTaskViewModel
            )
        }
    }
}
