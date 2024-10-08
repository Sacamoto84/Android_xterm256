package com.gamer.xterm256.screen.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.terminalm3.screen.lazy.bottomNavigation.colorBg
import com.gamer.xterm256.R

@Composable
fun BottomNavigationInfo(navController: NavController) {

    Column {
        Box(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Transparent)
            ,
            contentAlignment = Alignment.Center,
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(8.dp))

                Button(modifier = Modifier
                    .height(34.dp)
                    .fillMaxWidth()
                    .weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0096FF)),
                    onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(R.drawable.back1),
                        tint = Color.White,
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

    }

}

