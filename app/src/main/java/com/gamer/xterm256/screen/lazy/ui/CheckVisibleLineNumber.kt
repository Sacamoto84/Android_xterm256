package com.example.terminalm3.screen.lazy.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gamer.xterm256.console
import com.gamer.xterm256.shared

@Composable
fun CheckVisibleLineNumber() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
        //modifier = Modifier.background(Color.Red)
    ) {
        Checkbox(
            checked = console.lineVisible,
            onCheckedChange = {
                console.lineVisible = it
                shared.edit().putBoolean("lineVisible", it).apply()
            },
            colors = CheckboxDefaults.colors(uncheckedColor = Color.LightGray)
        )
        Text(text = "Вывести номер строки", color = Color.White)
    }

}