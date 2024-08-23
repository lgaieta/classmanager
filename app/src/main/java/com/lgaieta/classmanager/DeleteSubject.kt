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
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import androidx.compose.runtime.*

import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color.Companion.Black
import com.lgaieta.classmanager.subjects.ui.Subject


@Composable
fun DeleteSubject(subject: Subject, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(HorizontalPagePadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ){
                Box(modifier = Modifier
                    .padding(16.dp)
                ){
                    Text(text = "¿Estas seguro que deseas eliminar esta materia?", fontSize = 16.sp, color = Black)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                        Button(
                            onClick = { /* Acción para cancelar */ },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .padding(top =  60.dp)

                        ) {
                            Text(text = "Cancelar", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = Black)
                        }
                        Button(
                            onClick = { /* Acción para eliminar  */ },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .padding(top =  60.dp)
                        ) {
                            Text(text = "Eliminar", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = Black)
                        }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewDeleteSubjectComponent() {
    DeleteSubject(
        subject = Subject(
            name = "Modelos y sistemas",
            details = "7mo 2da E.E.S.T. Nª1",
            firstTime = "13:00",
            secondTime = "15:00"
        )
    )
}
