package com.gamer.xterm256

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.gamer.xterm256.screen.lazy.LineTextAndColor
import com.gamer.xterm256.screen.lazy.PairTextAndColor
import timber.log.Timber


class Initialization(private val context: Context) {


    init {

        init0()

    }

    fun init0() {

        Timber.plant(Timber.DebugTree())
        Timber.i("Привет")

        shared = context.getSharedPreferences("size", Context.MODE_PRIVATE)
        console.fontSize = (shared.getString("size", "12")?.toInt() ?: 12).sp

        //MARK: Вывод символа энтер
        isCheckUseCRLF = shared.getBoolean("enter", false)

        //MARK: Вывод номера строки
        console.lineVisible = shared.getBoolean("lineVisible", true)

        console.fontSize = shared.getInt("fontSize", 18).sp

        decoder.run()
        decoder.addCmd("pong") {

        }

        val version = 281 //BuildConfig.VERSION_NAME

        //Нужно добавить ее в список лази как текущую
        console.messages.add(
            LineTextAndColor(
                text = "Первый нах", pairList = listOf(
                    PairTextAndColor(
                        text = " RTT ", colorText = Color(0xFFFFAA00), colorBg = Color(0xFF812C12)
                    ), PairTextAndColor(
                        text = " Terminal ",
                        colorText = Color(0xFFC6D501),
                        colorBg = Color(0xFF587C2F)
                    ), PairTextAndColor(
                        text = " $version ",
                        colorText = Color(0xFF00E2FF),
                        colorBg = Color(0xFF334292)
                    ), PairTextAndColor(
                        text = ">", colorText = Color(0), colorBg = Color(0xFFFF0000)
                    ), PairTextAndColor(
                        text = "!", colorText = Color(0), colorBg = Color(0xFFFFCC00)
                    ), PairTextAndColor(
                        text = ">", colorText = Color(0), colorBg = Color(0xFF339900)
                    ), PairTextAndColor(
                        text = ">", colorText = Color(0), colorBg = Color(0xFF0033CC), flash = true
                    )
                )
            )
        )

        console.print("▁", flash = true)

    }


}