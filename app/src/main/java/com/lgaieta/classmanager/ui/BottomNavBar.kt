package com.lgaieta.classmanager.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.lgaieta.classmanager.R

@Composable
fun BottomNavBar(actions: BottomNavBarActions) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clickable { actions.onHomeClick() },
            ) {
                Icon(Icons.Filled.Home, contentDescription = "Inicio")
                Text(text = "Inicio", style = MaterialTheme.typography.bodySmall)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f).clickable {
                    actions.onSubjectsClick
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.school_filled_24),
                    contentDescription = "Inicio"
                )
                Text(text = "Materias", style = MaterialTheme.typography.bodySmall)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f).clickable { actions.onStudentsClick }
            ) {
                Icon(
                    painter = painterResource(R.drawable.group_filled_24),
                    contentDescription = "Inicio"
                )
                Text(text = "Alumnos", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

data class BottomNavBarActions(
    val onHomeClick: () -> Unit,
    val onSubjectsClick: () -> Unit,
    val onStudentsClick: () -> Unit
)