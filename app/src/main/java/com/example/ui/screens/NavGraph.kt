package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.compose.*
import kotlinx.coroutines.*
import com.example.ui.theme.*

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "splash",
        enterTransition = { fadeIn(tween(700)) },
        exitTransition = { fadeOut(tween(700)) },
        popEnterTransition = { fadeIn(tween(700)) },
        popExitTransition = { fadeOut(tween(700)) }
    ) {
        composable("splash") {
            SplashScreen(onNavigateToHome = {
                navController.navigate("dashboard") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable("dashboard") {
            DashboardScreen(
                onNavigateToVach = { navController.navigate("vach") },
                onNavigateToCode = { navController.navigate("code") },
                onNavigateToM4 = { navController.navigate("m4") },
                onNavigateToSurprise = { navController.navigate("surprise") },
                onNavigateToAbout = { navController.navigate("about") },
                onNavigateToShayri = { navController.navigate("shayri") },
                onNavigateToGoldenMission = { navController.navigate("golden_mission") },
                onNavigateToFinal = { navController.navigate("final") }
            )
        }
        composable("golden_mission") {
            GoldenMissionScreen(onBack = { navController.navigateUp() })
        }
        composable("about") {
            AboutScreen(onBack = { navController.navigateUp() })
        }
        composable("shayri") {
            ShayriScreen(onBack = { navController.navigateUp() })
        }
        composable("vach") {
            VachAiScreen(onBack = { navController.navigateUp() })
        }
        composable("code") {
            CodingChallengeScreen(
                onBack = { navController.navigateUp() },
                onSuccess = { 
                    // Unlock logic could be added here
                }
            )
        }
        composable("m4") {
            M4ExperienceScreen(onBack = { navController.navigateUp() })
        }
        composable("surprise") {
            SecretSurpriseScreen(
                onBack = { navController.navigateUp() },
                onNext = { navController.navigate("final") }
            )
        }
        composable("final") {
            FinalCelebrationScreen(
                onFinish = {
                    navController.navigate("status") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }
        composable("status") {
            FinalStatusScreen(
                onReplay = {
                    navController.navigate("splash") {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}
