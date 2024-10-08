package com.example.terminalm3.screen.lazy.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gamer.xterm256.R


@Composable
fun ButtonSetting(navController: NavController) {
    IconButton(
        modifier = Modifier.size(34.dp),
        colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xFF505050)),
        onClick = {
            navController.navigate("info")
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.settings1),
            tint = Color.LightGray,
            contentDescription = null
        )
    }
}