package com.lgaieta.classmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.lgaieta.classmanager.ClassManagerApplication
import com.lgaieta.classmanager.subjects.ui.details.SubjectDetailsScreen
import com.lgaieta.classmanager.subjects.ui.details.SubjectDetailsViewModel
import com.lgaieta.classmanager.subjects.ui.list.SubjectsListScreen
import com.lgaieta.classmanager.subjects.ui.list.SubjectsListViewModel
import com.lgaieta.classmanager.subjects.ui.new.NewSubjectScreen
import com.lgaieta.classmanager.subjects.ui.new.NewSubjectViewModel
import com.lgaieta.classmanager.ui.ClassManagerScreen
import com.lgaieta.classmanager.ui.viewModelFactory

const val SUBJECT_ID_ARGUMENT = "id"

class SubjectNavigationScreens {
    companion object {
        @Composable
        fun SubjectsListScreenInitializer(
            navController: NavController,
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
                onSubjectClick = { id -> navController.navigate("${ClassManagerScreen.SubjectDetails.name}/${id}") }
            )
        }

        @Composable
        fun SubjectDetailsScreenInitializer(
            navController: NavController,
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
                        afterDelete = { navController.navigate(ClassManagerScreen.SubjectsList.name)}
                    )
                })

            SubjectDetailsScreen(
                modifier = modifier,
                subjectDetailsViewModel = subjectDetailsViewModel
            )
        }

        @Composable
        fun NewSubjectScreenInitializer(
            navController: NavController,
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
                afterCreate = { navController.navigate(ClassManagerScreen.SubjectsList.name) }
            )
        }
    }
}
