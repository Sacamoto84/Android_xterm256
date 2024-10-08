package com.gamer.xterm256.screen.builder

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamer.xterm256.R

@Composable
fun GradientButton(
    onClick: () -> Unit, text: String = "", modifier: Modifier = Modifier, select: Boolean = false
) {

    val gradientColors = if (select) listOf(Color(0xFFCCCCCC), Color(0xFFE2E2E2))
    else listOf(Color(0xFFFEFEFE), Color(0xFFE7E7E7))

    val cornerRadius = 8.dp

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .then(modifier) //.padding(start = 32.dp, end = 32.dp),
        , onClick = onClick, contentPadding = PaddingValues(), colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ), shape = RoundedCornerShape(cornerRadius)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(
                    brush = Brush.verticalGradient(colors = gradientColors),
                    shape = RoundedCornerShape(cornerRadius)
                )
                .border(
                    1.dp, Color(0xFFD0D0D0), RoundedCornerShape(cornerRadius)
                )


            //.padding(horizontal = 16.dp, vertical = 8.dp)
            , contentAlignment = Alignment.Center
        ) {
            Text(
                text = text, fontSize = 18.sp, color = Color.Black, fontFamily = FontFamily(Font(R.font.barlowsemisondensedmedium))
            )
        }
    }
}