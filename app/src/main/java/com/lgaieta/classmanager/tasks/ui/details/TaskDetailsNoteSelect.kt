package com.lgaieta.classmanager.tasks.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NewStudentNoteSelect(
    onSelectedNumbersChange: (numberList: List<Int>) -> Unit,
    selectedNumbers: List<Int>
) {
    var isOpen by rememberSaveable { mutableStateOf(false) }
    var selectedDialogNumbers by rememberSaveable { mutableStateOf(selectedNumbers) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Selecciona los números entre 0 y 10",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
        if (selectedNumbers.isEmpty()) {
            Text(
                text = "Haz click en el botón + para elegir los números.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            selectedNumbers.map { selectedNumber ->
                Card(onClick = {
                    onSelectedNumbersChange(selectedNumbers.filterNot { it == selectedNumber })
                    selectedDialogNumbers = selectedNumbers.filterNot { it == selectedNumber }
                }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedNumber.toString(),
                            modifier = Modifier.padding(16.dp)
                        )

                    }
                }
            }
        }
        Button(
            onClick = { isOpen = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Añadir números"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Añadir números")
        }
    }

    if (isOpen) {
        Dialog(onDismissRequest = {
            isOpen = false
            selectedDialogNumbers = selectedNumbers
        }) {
            Card {
                Column {
                    Text(
                        text = "Selecciona números entre 0 y 10",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(24.dp).fillMaxWidth()
                    )
                    Divider(modifier = Modifier.fillMaxWidth())
                    // Aquí es donde agregamos el teclado numérico
                    Keypad(
                        onNumberSelected = { number ->
                            if (!selectedDialogNumbers.contains(number)) {
                                selectedDialogNumbers += number
                            } else {
                                selectedDialogNumbers = selectedDialogNumbers.filterNot { it == number }
                            }
                        }
                    )
                    Divider(modifier = Modifier.fillMaxWidth())
                    Row(modifier = Modifier.padding(24.dp)) {
                        Button(
                            onClick = {
                                onSelectedNumbersChange(selectedDialogNumbers)
                                isOpen = false
                            },
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Añadir números",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Keypad(onNumberSelected: (Int) -> Unit) {
    // Creamos los botones de los números en forma de teclado
    val numbers = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9),
        listOf(0)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        numbers.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                row.forEach { number ->
                    Button(
                        onClick = { onNumberSelected(number) },
                        modifier = Modifier.size(60.dp),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = number.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
