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
import com.lgaieta.classmanager.ui.theme.Background200
import com.lgaieta.classmanager.ui.theme.Background100
import com.lgaieta.classmanager.ui.theme.Buttons200
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import androidx.compose.runtime.*
import androidx.compose.foundation.border

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.graphics.Color.Companion.Black


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
                    .background(Background100)
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
                            colors = ButtonDefaults.buttonColors(containerColor = Background200),
                            modifier = Modifier
                                .padding(top =  60.dp)

                        ) {
                            Text(text = "Cancelar", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = Black)
                        }
                        Button(
                            onClick = { /* Acción para eliminar  */ },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Background200),
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
