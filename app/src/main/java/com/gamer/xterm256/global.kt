package com.gamer.xterm256

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.gamer.xterm256.screen.lazy.Console
import kotlinx.coroutines.flow.MutableStateFlow

// ╔═══════════════════════╗
// ║ Глобальные переменные ║
// ╚═══════════════════════╝

// ╔═══════╗
// ║ ФЛАГИ ║
// ╚═══════╝
//🟠🟡🟢🟣🟤🟦🟧🟨🟩🟪🟫

var isInitialized =
        false                                               //🟦Флаг того что произошла инициализация

var isCheckUseCRLF by mutableStateOf(false) //🟦Показывать в конце строки символ CR LF

val isCheckedUseLineVisible by mutableStateOf(false)  //🟦Показывать номер строки
var telnetSlegenie = MutableStateFlow(true)           //🟦Слежение за последней строкой

/////////////////////////////////////////////////////////
val warning = MutableStateFlow(false)
//━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙

//╔═════════╗
//║ CONSOLE ║
//╚═════════╝
val console = Console()








