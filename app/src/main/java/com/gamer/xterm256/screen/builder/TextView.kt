package com.gamer.xterm256.screen.builder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamer.xterm256.R

@Composable
fun TextView() {

    val textSize = 16.sp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black),
        horizontalAlignment = Alignment.Start
    ) {

        Box(
            Modifier
                .fillMaxWidth()
                .height(32.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFF4D4C47), Color(0xFF3B2C33)
                        )
                    )
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Colored shell",
                color = Color(0xFFDCD5BE),
                fontFamily = FontFamily(Font(R.font.ubuntumonoregular)),
                fontWeight = FontWeight.Bold
            )
        }

        val s = AnnotatedString.Builder()

        s.pushStyle(SpanStyle(color = Color(0xFF4E9608)))
        s.append("~$")
        s.pop()
        s.append("MY_CLR='$textResult' \\ ")
        s.pushStyle(SpanStyle(color = Color(0xFF4E9608)))
        s.append("↵")
        s.pop()
        val ss = """echo """ + """$""" + """{MY_CLR}Hello, world!"""
        s.append("\n $ss ")
        s.pushStyle(SpanStyle(color = Color(0xFF4E9608)))
        s.append("↵ \n")
        s.pop()

        Text(
            s.toAnnotatedString(),
            color = Color(0xFFDDDDDD),
            fontSize = textSize,
            fontFamily = FontFamily(Font(R.font.ubuntumonoregular)),
            modifier = Modifier.padding(top= 8.dp, start = 8.dp, end = 8.dp),overflow = TextOverflow.Visible
        )
        ColorText(
            str = "Hello, world!",
            textSize*2,
            Modifier.padding(start = 8.dp),
            fontFamily = FontFamily(Font(R.font.ubuntumonoregular))
        )
//        Text(
//            text = " ", fontSize = textSize, fontFamily = FontFamily(Font(R.font.ubuntumonoregular))
//        )
    }
}