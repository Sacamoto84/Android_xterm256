package com.gamer.xterm256.screen.builder

import android.widget.GridLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.gamer.xterm256.AutoResizingText
import com.gamer.xterm256.ColorPalette
import com.gamer.xterm256.R
import com.gamer.xterm256.drawColoredShadow


var selectBold by mutableStateOf(false)
var selectItalic by mutableStateOf(false)
var selectUnderline by mutableStateOf(false)
var selectReverse by mutableStateOf(false)

var selectFg by mutableStateOf(true)
var selectBg by mutableStateOf(false)

const val textStart = "\\033["

var textResult by mutableStateOf("\\033[38;05;10m")

var colorFg by mutableIntStateOf(10)
var colorBg by mutableIntStateOf(93)

var showDialogFg by mutableStateOf(false)
var showDialogBg by mutableStateOf(false)


val gradientColors = listOf(Color(0xFF7b4397), Color(0xFFdc2430))


fun textColorForPalitra(value: Int): Color = when (value) {
    in 0..4, in 16..27, in 52..57, in 232..243 -> Color(0xFFBBBBBB)
    else -> Color.Black
}

@Composable
fun ScreenBuilder(navController: NavHostController) {


    if (showDialogFg) {
        Dialog(onDismissRequest = { showDialogFg = false }) {
            ColorPicker {
                colorFg = it
                showDialogFg = false
            }
        }
    }

    if (showDialogBg) {
        Dialog(onDismissRequest = { showDialogBg = false }) {
            ColorPicker {
                colorBg = it
                showDialogBg = false
            }
        }
    }



    Scaffold(

        topBar = {
            Text(
                text = "xterm256 helper",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color(0xFF4D4C47), Color(0xFF3B2C33)
                            )
                        )
                    ),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium)),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },

        bottomBar = {

            //GradientButton(onClick = { navController.navigate("Information") }, text = "Info")

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.button4),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .size(64.dp)
                        .shadow(8.dp, clip = true, shape = CircleShape)
                        .clickable(onClick = { navController.navigate("info") }),
                    contentScale = ContentScale.Inside
                ) //Text(text = "Helper", color = Color.White)
            }


        }

    ) {


        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            alpha = 0.3f,
            modifier = Modifier.fillMaxSize()
        )


        Column(
            Modifier
                .padding(top = it.calculateTopPadding(),bottom = it.calculateBottomPadding())
                .verticalScroll(
                    rememberScrollState()
                )
        ) {

            TextView()
            ResultText()






            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {

                //////////////////

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                            .background(Color(0xFFEEEEEE))
                            .clickable(onClick = { showDialogFg = true }),
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Box(
                            modifier = Modifier
                                .width(96.dp)
                                .height(72.dp)
                                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 0.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                                .background(ColorPalette.color[colorFg]),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(
                                text = colorFg.toString(),
                                color = textColorForPalitra(colorFg),
                                fontWeight = FontWeight.Bold,
                                fontSize = 48.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium)),
                                modifier = Modifier.offset(0.dp, -(7).dp)
                            )
                        }

                        Icon(
                            imageVector = Icons.Rounded.ArrowDropDown,
                            contentDescription = "Person Icon"
                        )

                    } //////////////////


                    Checkbox(
                        checked = selectFg,
                        onCheckedChange = { selectFg = selectFg.not() },
                    )

                    Text(
                        text = "Foreground",
                        fontSize = 36.sp,
                        fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium)),
                        modifier = Modifier.padding(end = 8.dp)
                    )

                }

                //////////////////
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                            .background(Color(0xFFEEEEEE))
                            .clickable(onClick = { showDialogBg = true }),
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Box(
                            modifier = Modifier
                                .width(96.dp)
                                .height(72.dp)
                                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 0.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                                .background(ColorPalette.color[colorBg]),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(
                                text = colorBg.toString(),
                                color = textColorForPalitra(colorBg),
                                fontWeight = FontWeight.Bold,
                                fontSize = 48.sp,
                                fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium)),
                                modifier = Modifier.offset(0.dp, -(7).dp)
                            )
                        }

                        Icon(
                            imageVector = Icons.Rounded.ArrowDropDown,
                            contentDescription = "Person Icon"
                        )

                    }


                    Checkbox(checked = selectBg, onCheckedChange = { selectBg = selectBg.not() })
                    Text(
                        text = "Background",
                        fontSize = 36.sp,
                        fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium)),
                        modifier = Modifier.padding(end = 8.dp)
                    )

                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {

                GradientButton(
                    onClick = { selectBold = selectBold.not() },
                    text = "Bold",
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp),
                    select = selectBold

                )
                GradientButton(
                    onClick = { selectItalic = selectItalic.not() },
                    text = "Italic",
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp),
                    select = selectItalic
                )
                GradientButton(
                    onClick = { selectUnderline = selectUnderline.not() },
                    text = "Underline",
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp),
                    select = selectUnderline
                )
                GradientButton(
                    onClick = { selectReverse = selectReverse.not() },
                    text = "Reverse",
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp),
                    select = selectReverse,

                    )

            }



            Text(
                text = "Escape sequences",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
            )

            Text(
                text = sequences("\\e["),
                fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium)),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = sequences("\\033["),
                fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium)),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = sequences("\\u001b["),
                fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium)),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = sequences("\\x1b["),
                fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium)),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp)
            )

        }


    }


}

fun sequences(prefix: String): AnnotatedString {

    val seqBody = textResult.removePrefix("\\033[").removeSuffix("m")

    val s = AnnotatedString.Builder()

    s.append("• ")
    s.pushStyle(SpanStyle(background = Color(0xFFBBBBBB)))
    s.append(prefix)
    s.pop()
    s.append(seqBody)
    s.pushStyle(SpanStyle(background = Color(0xFFBBBBBB)))
    s.append("m")

    return s.toAnnotatedString()
}