package com.example.terminalm3.screen.lazy.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gamer.xterm256.isCheckUseCRLF
import com.gamer.xterm256.shared

@Composable
fun CheckVisibleCRLF() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {

        Checkbox(
            checked = isCheckUseCRLF, onCheckedChange = {
                isCheckUseCRLF = it
                shared.edit().putBoolean("enter", it).apply()
            }, colors = CheckboxDefaults.colors(uncheckedColor = Color.LightGray)
        )

        Text(text = "Вывести символ CR LF", color = Color.White)
    }

}