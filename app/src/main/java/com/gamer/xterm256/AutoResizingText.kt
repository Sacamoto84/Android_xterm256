package com.gamer.xterm256

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun AutoResizingText(
    text: String,
    //fontSizeRange = (18..36).sp,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = 1,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    modifier: Modifier = Modifier
) {
    var textSize by remember { mutableStateOf(64.sp) }
    var currentMaxLines by remember { mutableStateOf(1) }
    var readyToDraw by remember { mutableStateOf(false) }

    Text(
        modifier = modifier.drawWithContent {
            if (readyToDraw) drawContent()
        },
        text = text,
        color = color,
        textAlign = textAlign,
        fontSize = textSize,
        overflow = overflow,
        maxLines = currentMaxLines,
        style = style, softWrap = false,


        onTextLayout = { textLayoutResult ->



            val maxCurrentLineIndex = textLayoutResult.lineCount - 1

            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex) && currentMaxLines <= maxLines) {

                val reducedTextSize = textSize * 0.9f

                textSize = if (reducedTextSize < 18.sp) {

                    if (currentMaxLines < maxLines) {
                        currentMaxLines++
                        64.sp
                    } else {

                        readyToDraw = true
                        textSize

                    }

                } else {

                    reducedTextSize

                }

            } else {

                readyToDraw = true
            }
        }
    )
}


