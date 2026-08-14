package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import kotlinx.coroutines.delay
import com.example.ui.theme.*

@Composable
fun SplashScreen(onNavigateToHome: () -> Unit) {
    var step by remember { mutableStateOf(0) }
    
    LaunchedEffect(Unit) {
        delay(1500) // Pitch black
        step = 1 // Headlights appear
        delay(2500)
        step = 2 // Car accelerates
        delay(1000)
        step = 3 // System Online
        delay(1500)
        step = 4 // AI Core
        delay(1500)
        step = 5 // Target Identified
        delay(1500)
        step = 6 // Yuvraj
        delay(2000)
        step = 7 // Happy Birthday
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack),
        contentAlignment = Alignment.Center
    ) {
        // Step 1 & 2: Headlights
        AnimatedVisibility(
            visible = step in 1..2,
            enter = fadeIn(tween(2000)),
            exit = fadeOut(tween(500)) + scaleOut(targetScale = 3f, animationSpec = tween(500))
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(80.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Headlight()
                Headlight()
            }
        }

        // Steps 3+: Text Sequence
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = step >= 3, enter = fadeIn()) {
                Text("> SYSTEM ONLINE", color = NeonCyan, fontFamily = FontFamily.Monospace)
            }
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(visible = step >= 4, enter = fadeIn()) {
                Text("> AI CORE: VACH AI", color = GlowingWhite, fontFamily = FontFamily.Monospace)
            }
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(visible = step >= 5, enter = fadeIn()) {
                Text("> TARGET IDENTIFIED", color = SuccessGreen, fontFamily = FontFamily.Monospace)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            AnimatedVisibility(visible = step >= 6, enter = scaleIn(spring(dampingRatio = 0.5f)) + fadeIn()) {
                Text(
                    "YUVRAJ",
                    color = GlowingWhite,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Black,
                        letterSpacing = 12.sp
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            AnimatedVisibility(visible = step >= 7, enter = fadeIn(tween(1000)) + slideInVertically { 50 }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "🎉 HAPPY BIRTHDAY, YUVRAJ! 🎉",
                        color = NeonCyan,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Welcome to your birthday protocol.",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = onNavigateToHome,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = NeonCyan,
                            contentColor = DeepBlack
                        ),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("ENTER EXPERIENCE →", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun Headlight() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(150), repeatMode = RepeatMode.Reverse),
        label = ""
    )
    Box(
        modifier = Modifier
            .width(60.dp)
            .height(16.dp)
            .graphicsLayer {
                shape = RoundedCornerShape(percent = 50)
                clip = true
            }
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color.White.copy(alpha = alpha), NeonCyan.copy(alpha = alpha))
                )
            )
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(percent = 50),
                spotColor = NeonCyan
            )
    )
}
