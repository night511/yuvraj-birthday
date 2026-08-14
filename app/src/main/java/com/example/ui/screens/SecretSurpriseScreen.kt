package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import kotlinx.coroutines.delay
import com.example.ui.theme.*

@Composable
fun SecretSurpriseScreen(onBack: () -> Unit, onNext: () -> Unit) {
    var step by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        delay(1000)
        step = 1
        delay(3000)
        step = 2
        delay(3000)
        step = 3
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
            .clickable(enabled = step >= 3) { onNext() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = step >= 1, enter = fadeIn()) {
                Text(
                    "« I have detected a classified birthday message. »",
                    color = NeonCyan,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            AnimatedVisibility(visible = step >= 2, enter = fadeIn(tween(2000))) {
                Text(
                    "YUVRAJ, YOU'RE NOT JUST USING AI...\n\nYOU'RE BUILDING IT.",
                    color = GlowingWhite,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            AnimatedVisibility(visible = step >= 3, enter = fadeIn(tween(2000))) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Keep coding. Keep creating. Keep turning crazy ideas into real projects. 🚀",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(60.dp))
                    Text("Tap anywhere to continue", color = DarkBorder, fontSize = 12.sp)
                }
            }
        }
    }
}
