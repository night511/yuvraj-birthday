package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import kotlinx.coroutines.delay
import kotlin.random.Random
import com.example.ui.theme.*

@Composable
fun FinalCelebrationScreen(onFinish: () -> Unit) {
    var step by remember { mutableStateOf(0) }
    
    LaunchedEffect(Unit) {
        delay(1000)
        step = 1
        delay(1500)
        step = 2
        delay(3000)
        step = 3
    }

    Box(
        modifier = Modifier.fillMaxSize().background(DeepBlack),
        contentAlignment = Alignment.Center
    ) {
        if (step >= 2) {
            ConfettiBackground()
        }
        
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = step >= 1, enter = fadeIn() + scaleIn()) {
                Text(
                    "YUVRAJ",
                    color = GlowingWhite,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Black,
                        letterSpacing = 8.sp
                    ),
                    modifier = Modifier.drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, NeonCyan.copy(alpha = 0.3f))
                                ),
                                blendMode = BlendMode.SrcAtop
                            )
                        }
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            AnimatedVisibility(visible = step >= 2, enter = slideInVertically { 50 } + fadeIn()) {
                Text(
                    "🎉 HAPPY BIRTHDAY, YUVRAJ! 🎉",
                    color = SuccessGreen,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            AnimatedVisibility(visible = step >= 2, enter = fadeIn(tween(1500))) {
                Text(
                    "May your code compile on the first try, your bugs disappear instantly, your AI become smarter every day, and your dream M4 Competition get closer. Keep building, keep learning, and keep creating the future. 🖤🤖💻🏎️",
                    color = Color.LightGray,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            
            AnimatedVisibility(visible = step >= 3, enter = fadeIn()) {
                Button(
                    onClick = onFinish,
                    colors = ButtonDefaults.buttonColors(containerColor = NeonCyan, contentColor = DeepBlack),
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    Text("VIEW STATUS REPORT", fontWeight = FontWeight.Black)
                }
            }
        }
    }
}

@Composable
fun ConfettiBackground() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val yOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing)),
        label = ""
    )
    Canvas(modifier = Modifier.fillMaxSize()) {
        for (i in 0..100) {
            val random = Random(i)
            val x = random.nextFloat() * size.width
            val radius = random.nextFloat() * 10f + 5f
            val color = listOf(NeonCyan, PrimaryBlue, SuccessGreen, GlowingWhite).random(random)
            val y = (random.nextFloat() * size.height + yOffset) % size.height
            drawCircle(color = color, radius = radius, center = Offset(x, y))
        }
    }
}
