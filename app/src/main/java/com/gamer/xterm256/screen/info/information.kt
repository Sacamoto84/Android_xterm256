package com.gamer.xterm256.screen.info

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gamer.xterm256.ColorPalette
import com.gamer.xterm256.R
import com.gamer.xterm256.listSortedColor
import kotlinx.coroutines.flow.MutableStateFlow

var textColorView = MutableStateFlow(Color.Transparent) //Цвет

private var textColorView2 = MutableStateFlow(Color.Transparent) //Цвет

@OptIn(
    ExperimentalLayoutApi::class, ExperimentalFoundationApi::class
)
@Composable
fun ScreenInfo(navController: NavController) {

    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = { BottomNavigationInfo(navController) }, containerColor = Color(0xFF090909)
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
                .background(Color(0xFF090909))
                .verticalScroll(scrollState)
        ) {

            Text(
                text = "The text color depending on the color code",
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center, fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium)),
                fontSize = 24.sp
            )

            FlowRow(maxItemsInEachRow = 8) {

                for (i in 0..15) {

                    val textcolor = when (i) {
                        in 0..4 -> Color(0xFFBBBBBB)
                        else -> Color.Black
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .height(30.dp)
                            .background(ColorPalette.color[i])
                            .border(0.5.dp, Color.Black)
                            .combinedClickable(
                                onClick = {
                                    textColorView.value = ColorPalette.color[i]
                                },
                            ),

                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = i.toString(),
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace,
                            color = if (textColorView.collectAsState().value == Color.Transparent) textcolor else textColorView.collectAsState().value
                        )
                    }
                }
            }

            FlowRow(maxItemsInEachRow = 12) {

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
                                onClick = {
                                    textColorView.value = ColorPalette.color[value]
                                },
                            ), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = value.toString(),
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.jetbrains)),
                            fontWeight = FontWeight.ExtraBold,
                            color = if (textColorView.collectAsState().value == Color.Transparent) textcolor else textColorView.collectAsState().value
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "The background color depending on the color code",
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center, fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium)),
                fontSize = 24.sp
            )

            FlowRow(maxItemsInEachRow = 8) {

                (0..255).forEach { value ->

                    val textcolor = ColorPalette.color[value]
                    val colorBg =
                            if (textColorView2.collectAsState().value == Color.Transparent) Color.Black else textColorView2.collectAsState().value

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .height(30.dp)
                            .background(colorBg)
                            .border(0.5.dp, Color.Black) //.tooltipAnchor()
                            .combinedClickable(
                                onClick = {
                                    textColorView2.value = ColorPalette.color[value]
                                },
                            ), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = value.toString(),
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.jetbrains)),
                            fontWeight = FontWeight.W900,
                            color = textcolor
                        )
                    }
                }
            }
        }
    }
}



