package com.gamer.xterm256.screen.lazy

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.terminalm3.screen.lazy.bottomNavigation.BottomNavigationLazy
import com.gamer.xterm256.screen.lazy.bottomNavigation.ModalBottomSheetContent
import com.gamer.xterm256.console
import com.gamer.xterm256.screen.lazy.ui.Warning

//var isConfimChange = mutableStateOf(false)

//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun ScreenLazy(navController: NavHostController) {
//
//    val scaffoldState = rememberBottomSheetScaffoldState(
//
//        bottomSheetState = SheetState(
//            skipPartiallyExpanded = false, // pass false here
//            initialValue = SheetValue.PartiallyExpanded,
//            skipHiddenState = true
//        )
//
////        bottomSheetState = SheetState(
////            false,
////            LocalDensity.current,
////            initialValue = SheetValue.PartiallyExpanded,
////            skipHiddenState = true
////        )
//    )
//
//    BottomSheetScaffold(
//        scaffoldState = scaffoldState,
//        sheetShape = androidx.compose.ui.graphics.RectangleShape,
//        sheetContainerColor = Color.DarkGray,
//        containerColor = Color.Black,
//        sheetPeekHeight = 58.dp,
//
//        sheetDragHandle = {
//            BottomNavigationLazy(navController)
//        },
//        // = true,
//        sheetContent = { ModalBottomSheetContent(navController, scaffoldState)
//}
//
//    )
//    {
//
//        Box(
//            Modifier
//                .fillMaxSize()
//                .padding(bottom = it.calculateBottomPadding())
//        ) {
//            console.lazy() //Modifier.padding(4.dp).recomposeHighlighter()
//            Warning()
//        }
//
//    }
//
//}



