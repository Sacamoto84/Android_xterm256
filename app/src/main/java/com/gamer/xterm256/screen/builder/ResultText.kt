package com.gamer.xterm256.screen.builder

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultText()
{
    val addBold = if (selectBold) "01" else ""

    val addItalic = when {
        !selectBold -> {
            if (selectItalic) "03"
            else ""
        }

        selectBold -> {
            if (selectItalic) ";03" else ""
        }

        else -> ""
    }

    val addUnderline = when {

        !selectBold && !selectItalic -> {
            if (selectUnderline) "04"
            else ""
        }

        else -> {
            if (selectUnderline) ";04"
            else ""
        }
    }

    val addReverse = when {
        !selectBold && !selectItalic && !selectUnderline -> {
            if (selectReverse) "07"
            else ""
        }

        else -> {
            if (selectReverse) ";07"
            else ""
        }
    }

    val add =
            if (!selectBold && !selectItalic && !selectUnderline && !selectReverse) "" else ";"

    val addForeground = when {
        selectFg -> add + "38;05;$colorFg"
        else -> ""
    }

    val addB =
            if (!selectBold && !selectItalic && !selectUnderline && !selectReverse && !selectFg) "" else ";"

    val addBackground = when {
        selectBg -> addB + "48;05;$colorBg"
        else -> ""
    }

    textResult =
            textStart + addBold + addItalic + addUnderline + addReverse + addForeground + addBackground + "m"

    ColorText(str = textResult, modifier = Modifier.fillMaxWidth().height(76.dp))
}