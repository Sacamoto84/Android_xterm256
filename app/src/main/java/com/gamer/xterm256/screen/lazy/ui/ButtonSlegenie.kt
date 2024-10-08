package com.example.terminalm3.screen.lazy.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamer.xterm256.R
import com.gamer.xterm256.console


@Composable
fun ButtonSlegenie(modifier: Modifier = Modifier) {

    //По кнопке включаем слежение
    val slegenie = console.tracking

    // Кнопка включения слежения
    Button(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(0.6f)
        .padding(
            //start = 8.dp,
            top = 8.dp, bottom = 8.dp)
        .then(modifier),
        contentPadding = PaddingValues(0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (slegenie) Color(0xFF8AAF4A) else Color.DarkGray
        ),
        onClick = {
            console.tracking = !console.tracking
        }) {
        console.recompose()
        Text(
            text = "${console.lastCount}",
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.jetbrains)),
            fontSize = 18.sp
        )
    }
}


