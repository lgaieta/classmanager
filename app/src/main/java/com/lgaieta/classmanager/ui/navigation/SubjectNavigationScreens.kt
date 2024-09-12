package com.lgaieta.classmanager.ui.navigation

import EditSubjectViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lgaieta.classmanager.ClassManagerApplication
import com.lgaieta.classmanager.subjects.ui.details.SubjectDetailsScreen
import com.lgaieta.classmanager.subjects.ui.details.SubjectDetailsViewModel
import com.lgaieta.classmanager.subjects.ui.edit.EditSubjectScreen
import com.lgaieta.classmanager.subjects.ui.list.SubjectsListScreen
import com.lgaieta.classmanager.subjects.ui.list.SubjectsListViewModel
import com.lgaieta.classmanager.subjects.ui.new.NewSubjectScreen
import com.lgaieta.classmanager.subjects.ui.new.NewSubjectTimeScreen
import com.lgaieta.classmanager.subjects.ui.new.NewSubjectViewModel
import com.lgaieta.classmanager.ui.viewModelFactory

const val SUBJECT_ID_ARGUMENT = "id"

fun NavGraphBuilder.subjectNavigationScreens(navController: NavHostController) {
    composable(route = ClassManagerScreen.SubjectsList.name) {
        SubjectNavigationScreens.SubjectsListScreenInitializer(
            navController = navController,
        )
    }
    composable(
        route = "${ClassManagerScreen.SubjectDetails.name}/{$SUBJECT_ID_ARGUMENT}",
        arguments = listOf(navArgument(SUBJECT_ID_ARGUMENT) {
            type = androidx.navigation.NavType.IntType
        })
    ) { backStackEntry ->
        SubjectNavigationScreens.SubjectDetailsScreenInitializer(
            navController = navController,
            backStackEntry = backStackEntry,
        )
    }
    composable(
        route = "${ClassManagerScreen.EditSubject.name}/{$SUBJECT_ID_ARGUMENT}",
        arguments = listOf(navArgument(SUBJECT_ID_ARGUMENT) {
            type = androidx.navigation.NavType.IntType
        })
    ) { backStackEntry ->
        SubjectNavigationScreens.EditSubjectScreenInitializer(
            navController = navController,
            backStackEntry = backStackEntry,
        )
    }
    composable(route = ClassManagerScreen.NewSubject.name) {
        SubjectNavigationScreens.NewSubjectScreenInitializer(navController = navController)
    }
    composable(route = ClassManagerScreen.NewSubjectTime.name) {
        SubjectNavigationScreens.NewSubjectTimeScreenInitializer(navController = navController)
    }
}

class SubjectNavigationScreens {
    companion object {
        @Composable
        fun SubjectsListScreenInitializer(
            navController: NavHostController,
            modifier: Modifier = Modifier
        ) {
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
                onSubjectClick = { id -> navController.navigate("${ClassManagerScreen.SubjectDetails.name}/${id}") },
                bottomNavBarActions = getDefaultBottomNavBarActions(navController)
            )
        }

        @Composable
        fun SubjectDetailsScreenInitializer(
            navController: NavHostController,
            backStackEntry: NavBackStackEntry,
            modifier: Modifier = Modifier
        ) {
            val subjectId =
                backStackEntry.arguments?.getInt(SUBJECT_ID_ARGUMENT) ?: return

            val subjectDetailsViewModel =
                viewModel<SubjectDetailsViewModel>(factory = viewModelFactory {
                    SubjectDetailsViewModel(
                        offlineSubjectRepository =
                        ClassManagerApplication.subjectModelsContainer.offlineSubjectRepository,
                        subjectId = subjectId,
                        afterDelete = { navController.navigate(ClassManagerScreen.SubjectsList.name) },
                        afterEdit = { id -> navController.navigate("${ClassManagerScreen.EditSubject.name}/${id}") }
                    )
                })

            SubjectDetailsScreen(
                modifier = modifier,
                subjectDetailsViewModel = subjectDetailsViewModel,
            )
        }

        @Composable
        fun NewSubjectScreenInitializer(
            navController: NavHostController,
            modifier: Modifier = Modifier
        ) {
            val newSubjectViewModel =
                viewModel<NewSubjectViewModel>(factory = viewModelFactory {
                    NewSubjectViewModel(
                        offlineSubjectRepository = ClassManagerApplication.subjectModelsContainer.offlineSubjectRepository
                    )
                })

            NewSubjectScreen(
                modifier = modifier,
                newSubjectViewModel = newSubjectViewModel,
                onNewTime = { navController.navigate(ClassManagerScreen.NewSubjectTime.name) },
                afterCreate = { navController.navigate(ClassManagerScreen.SubjectsList.name) }
            )
        }

        @Composable
        fun EditSubjectScreenInitializer(
            navController: NavController,
            backStackEntry: NavBackStackEntry,
            modifier: Modifier = Modifier
        ) {
            val subjectId =
                backStackEntry.arguments?.getInt(SUBJECT_ID_ARGUMENT) ?: return

            val editSubjectViewModel =
                viewModel<EditSubjectViewModel>(factory = viewModelFactory {
                    EditSubjectViewModel(
                        offlineSubjectRepository =
                        ClassManagerApplication.subjectModelsContainer.offlineSubjectRepository,
                        subjectId = subjectId,
                        afterEdit = { navController.popBackStack() }
                    )
                })

            EditSubjectScreen(
                modifier = modifier,
                editSubjectViewModel = editSubjectViewModel
            )
        }

        @Composable
        fun NewSubjectTimeScreenInitializer(
            navController: NavHostController,
        ) {
            NewSubjectTimeScreen(
            )
        }
    }
}
