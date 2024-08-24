package com.lgaieta.classmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Creates a custom ViewModel factory.
 *
 * This function is generic and works with any ViewModel type. It takes a lambda `initializer`
 * that returns an instance of the ViewModel when invoked.
 * It's useful to pass a ViewModel instance with constructor parameters declared.
 *
 * @param initializer A lambda function that initializes and returns the ViewModel instance with dependencies passed in.
 * @return A ViewModelProvider.Factory that can be used to instantiate the ViewModel.
 */
fun <VM : ViewModel> viewModelFactory(initializer: () -> VM): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return initializer() as T
        }
    }
}