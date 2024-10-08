package com.gamer.xterm256.screen.builder

import androidx.compose.foundation.background

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.gamer.xterm256.ColorPalette

@Composable
fun ColorText(str :String, fontSize :TextUnit = 32.sp, modifier : Modifier,  fontFamily: FontFamily = FontFamily.Default){



    Text(
        text = str,
        modifier = Modifier.

          then(modifier)
            .background(

                when{

                    selectReverse && selectFg && selectBg -> ColorPalette.color[colorFg]
                    !selectReverse && selectFg && selectBg -> ColorPalette.color[colorBg]

                    selectReverse && selectFg && !selectBg  -> ColorPalette.color[colorFg]
                    !selectReverse && selectFg && !selectBg  -> Color.Black


                    selectReverse && !selectFg && selectBg ->  Color(0xFFDDDDDD)
                    !selectReverse && !selectFg && selectBg -> ColorPalette.color[colorBg]

                    selectReverse && !selectFg && !selectBg  -> Color(0xFFDDDDDD)
                    !selectReverse && !selectFg && !selectBg  -> Color.Black

                    else -> Color(0xFFDDDDDD)

                }



            ),
        textAlign = TextAlign.Center,
        fontSize = fontSize,
        fontWeight = if (selectBold) FontWeight.Bold else FontWeight.Normal,
        color =

        when{


            selectReverse && selectFg && selectBg -> ColorPalette.color[colorBg]
            !selectReverse && selectFg && selectBg -> ColorPalette.color[colorFg]

            selectReverse && selectFg && !selectBg  -> Color.Black
            !selectReverse && selectFg && !selectBg  -> ColorPalette.color[colorFg]


            selectReverse && !selectFg && selectBg -> ColorPalette.color[colorBg]
            !selectReverse && !selectFg && selectBg -> Color(0xFFDDDDDD)

            selectReverse && !selectFg && !selectBg  -> Color.Black
            !selectReverse && !selectFg && !selectBg  -> Color(0xFFDDDDDD)

            else -> Color(0xFFDDDDDD)

        }





        ,
        fontStyle = if (selectItalic) FontStyle.Italic else FontStyle.Normal
        , textDecoration = if (selectUnderline) TextDecoration.Underline else TextDecoration.None,
        lineHeight = fontSize, fontFamily = fontFamily
    )


}