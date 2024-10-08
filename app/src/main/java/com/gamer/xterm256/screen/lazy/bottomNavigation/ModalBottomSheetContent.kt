package com.gamer.xterm256.screen.lazy.bottomNavigation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.terminalm3.screen.lazy.ui.CardFontSize
import com.example.terminalm3.screen.lazy.ui.CheckVisibleCRLF
import com.example.terminalm3.screen.lazy.ui.CheckVisibleLineNumber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetContent(navController: NavHostController, scaffoldState: BottomSheetScaffoldState) {
    Column {

        if (scaffoldState.bottomSheetState.targetValue == SheetValue.PartiallyExpanded) {
            Canvas(modifier = Modifier.fillMaxWidth().height(4.dp), onDraw = {
                drawLine(
                    Color.Gray,
                    Offset(size.width * 0.4f, size.height),
                    Offset(size.width * 0.6f, size.height),
                    strokeWidth = 2.dp.toPx()
                )
            })
        }

        CheckVisibleLineNumber()
        CheckVisibleCRLF()
        CardFontSize()

    }
}
