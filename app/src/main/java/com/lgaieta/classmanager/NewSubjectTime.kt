package com.lgaieta.classmanager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import androidx.compose.runtime.*

import androidx.compose.ui.graphics.Color.Companion.Black
import com.lgaieta.classmanager.subjects.ui.Subject


@Composable
fun NewSubjectTime(subject: Subject, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(HorizontalPagePadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nuevo Horario",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Horarios

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
        ) {
            Text(
                text = "17:00",
                style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Light),
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 40.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Button(
                        onClick = { /* Acción para agregar un nuevo horario */ },
                        shape = RoundedCornerShape(64.dp),
                        modifier = Modifier

                    ) {
                        Text(text = "D", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold), color = Black)
                    }
                    Button(
                        onClick = { /* Acción para agregar un nuevo horario */ },
                        shape = RoundedCornerShape(64.dp),
                        modifier = Modifier

                    ) {
                        Text(text = "L", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold), color = Black)
                    }
                    Button(
                            onClick = { /* Acción para agregar un nuevo horario */ },
                    shape = RoundedCornerShape(64.dp),
                    modifier = Modifier

                    ) {
                    Text(text = "M", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold), color = Black)
                    }
                    Button(
                        onClick = { /* Acción para agregar un nuevo horario */ },
                        shape = RoundedCornerShape(64.dp),
                        modifier = Modifier

                    ) {
                        Text(text = "X", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold), color = Black)
                    }
                    Button(
                        onClick = { /* Acción para agregar un nuevo horario */ },
                        shape = RoundedCornerShape(64.dp),
                        modifier = Modifier

                    ) {
                        Text(text = "J", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold), color = Black)
                    }
                    Button(
                        onClick = { /* Acción para agregar un nuevo horario */ },
                        shape = RoundedCornerShape(64.dp),
                        modifier = Modifier

                    ) {
                        Text(text = "V", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold), color = Black)
                    }
                    Button(
                        onClick = { /* Acción para agregar un nuevo horario */ },
                        shape = RoundedCornerShape(64.dp),
                        modifier = Modifier

                    ) {
                        Text(text = "S", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold), color = Black)
                    }
                }
            }



        }
        // Botón para agregar nueva materia

        Spacer(modifier = Modifier.weight(1f))  // Este Spacer empuja los botones hacia abajo

        // Botón para agregar nueva materia
        Button(
            onClick = { /* Agregar nueva materia */ },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Guardar horario",modifier.padding(vertical = 4.dp), style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
        }

        Button(
            onClick = { /* Acción de cancelar */ },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
        ) {
            Text(text = "Cancelar",modifier.padding(8.dp), style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = Black)
        }


    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewNewSubjectTimePage() {
    NewSubjectTime(
        subject = Subject(
            name = "Modelos y sistemas",
            details = "7mo 2da E.E.S.T. Nª1",
            firstTime = "13:00",
            secondTime = "15:00"
        )
    )
}
