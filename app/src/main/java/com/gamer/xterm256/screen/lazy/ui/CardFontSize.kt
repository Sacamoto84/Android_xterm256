package com.example.terminalm3.screen.lazy.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamer.xterm256.console
import com.gamer.xterm256.shared
import com.siddroid.holi.colors.MaterialColor

@Composable
fun CardFontSize() {

    OutlinedCard(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp, start = 8.dp, end = 8.dp
            ),
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialColor.GREY_900)
    ) {

        Column {

            Text(
                text = "Размер шрифта: " + console.fontSize.value.toInt().toString(),
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Slider(
                value = console.fontSize.value, onValueChange = {
                    console.fontSize = it.toInt().sp

                    shared.edit().putInt("fontSize", it.toInt()).apply()

                }, valueRange = 10f..36f, steps = 26, modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )

        }

    }


}