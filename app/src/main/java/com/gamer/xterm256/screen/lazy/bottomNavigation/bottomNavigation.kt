package com.example.terminalm3.screen.lazy.bottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.terminalm3.screen.lazy.ui.ButtonClear
import com.example.terminalm3.screen.lazy.ui.ButtonSetting
import com.example.terminalm3.screen.lazy.ui.ButtonSlegenie

val colorBg = Color(0xFF1B1B1B)

@Composable
fun BottomNavigationLazy(navController: NavController) {

    Box(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            //.background(Color.Transparent)
            .background(colorBg),

        contentAlignment = Alignment.Center,
    )
    {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically

        ) {
            ButtonSlegenie()//Modifier.weight(1f))
            //Кнопка сброса списка
            //Spacer(modifier = Modifier.width(8.dp))
            ButtonClear() //Кнопка очистка списка
            //Spacer(modifier = Modifier.width(16.dp))
            //ButtonReset() //Кнопка перегрузки контроллера
            //Spacer(modifier = Modifier.width(16.dp))
            ButtonSetting(navController)
            //Spacer(modifier = Modifier.width(16.dp))

        }
    }
}






@Composable
private fun ButtonReset() {

//    val context = LocalContext.current
//
//    IconButton(
//        modifier = Modifier.size(34.dp),
//        colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xFF505050)),
//        onClick = {
//            val s =
//                sendUDP("Reset", ip = ipToBroadCast(readLocalIP(context)), port = 8889)
//            if (s == "OK") {
//               console.consoleAdd("Команда перезагрузки контроллера")
//               console.consoleAdd(" ")
//            } else {
//                if (s == "sendto failed: ENETUNREACH (Network is unreachable)")
//                    console.consoleAdd("Отсуствует Wifi сеть", color = Color.Red)
//                else
//                    console.consoleAdd(s, color = Color.Red)
//            }
//        }
//    ) {
//        Icon(
//            painter = painterResource(R.drawable.reset),
//            tint = Color.LightGray,
//            contentDescription = null
//        )
//
//    }

}



