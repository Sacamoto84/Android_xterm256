package com.gamer.xterm256.screen.builder

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamer.xterm256.ColorPalette
import com.gamer.xterm256.R
import com.gamer.xterm256.listSortedColor
import com.gamer.xterm256.screen.info.textColorView
@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun ColorPicker(ret : (Int) -> Unit)
{

    Column(modifier = Modifier.border(2.dp,Color.Black, RoundedCornerShape(8.dp))) {



        FlowRow(modifier = Modifier.clip(
            RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
        ), maxItemsInEachRow = 8) {

            for (i in 0..15) {

                val textcolor = when (i) {
                    in 0..4 -> Color(0xFFBBBBBB)
                    else -> Color.Black
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(32.dp)
                        .background(ColorPalette.color[i])
                        .border(0.5.dp, Color.Black)
                        .combinedClickable(
                            onClick = { //textColorView.value = ColorPalette.color[i]
                                ret.invoke(i)

                            },
                        ),

                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = i.toString(),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Monospace,
                        color = textcolor
                    )
                }
            }
        }

        FlowRow(modifier = Modifier.clip(
            RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
        ),maxItemsInEachRow = 12) {

            listSortedColor.forEach { value ->

                val textcolor = when (value) {
                    in 0..4, in 16..27, in 52..57, in 232..243 -> Color(0xFFBBBBBB)
                    else -> Color.Black
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(30.dp)
                        .background(ColorPalette.color[value])
                        .border(0.5.dp, Color.Black)
                        .combinedClickable(
                            onClick = { //textColorView.value = ColorPalette.color[value]
                                ret.invoke(value)
                            },
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = value.toString(),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.jetbrains)),
                        fontWeight = FontWeight.ExtraBold,
                        color = textcolor
                    )
                }
            }

        }
    }
}

