package com.gamer.xterm256

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gamer.xterm256.screen.builder.ScreenBuilder
import com.gamer.xterm256.screen.info.ScreenInfo

@Composable
fun BuildNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "builder",
        enterTransition = { fadeIn(animationSpec = tween(200)) },
        exitTransition = { fadeOut(animationSpec = tween(200)) },
        popEnterTransition = { fadeIn(animationSpec = tween(200)) },
        popExitTransition = { fadeOut(animationSpec = tween(200)) },
    ) {

//        composable("home") {
//            ScreenLazy(navController)
//        }

        composable("info") {
            ScreenInfo(navController)
        }

        composable("builder") {
            ScreenBuilder(navController)
        }


    }
}