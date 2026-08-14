package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Speed
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun M4ExperienceScreen(onBack: () -> Unit) {
    var engineStarted by remember { mutableStateOf(false) }
    var launchMode by remember { mutableStateOf(false) }
    
    val infiniteTransition = rememberInfiniteTransition(label = "")
    
    val shake by infiniteTransition.animateFloat(
        initialValue = if (launchMode) -10f else if (engineStarted) -2f else 0f,
        targetValue = if (launchMode) 10f else if (engineStarted) 2f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (launchMode) 50 else 100, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("M4 PERFORMANCE PROTOCOL", color = NeonCyan, fontFamily = FontFamily.Monospace) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, tint = NeonCyan, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceBlack)
            )
        },
        containerColor = DeepBlack
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .graphicsLayer { translationX = shake }
        ) {
            // Speed Lines Effect
            if (launchMode) {
                SpeedLines()
            }
            
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                
                // Car Placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(
                            Brush.verticalGradient(listOf(Color.Transparent, SurfaceBlack)),
                            RoundedCornerShape(16.dp)
                        )
                        .border(1.dp, if (launchMode) ErrorRed else DarkBorder, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (engineStarted || launchMode) {
                        Row(horizontalArrangement = Arrangement.spacedBy(100.dp)) {
                            Headlight(color = if (launchMode) ErrorRed else NeonCyan)
                            Headlight(color = if (launchMode) ErrorRed else NeonCyan)
                        }
                    }
                    Text(
                        "M4 COMPETITION",
                        color = GlassBlack,
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Black
                    )
                }
                
                Spacer(modifier = Modifier.height(40.dp))
                
                // HUD
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    HudElement("RPM", if (launchMode) "7200" else if (engineStarted) "1200" else "0")
                    HudElement("SPEED", if (launchMode) "240" else "0", if (launchMode) ErrorRed else NeonCyan)
                    HudElement("GEAR", if (launchMode) "5" else if (engineStarted) "1" else "P")
                }
                
                Spacer(modifier = Modifier.height(60.dp))
                
                if (!engineStarted) {
                    Button(
                        onClick = { engineStarted = true },
                        modifier = Modifier.size(120.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = ErrorRed)
                    ) {
                        Text("START\nENGINE", textAlign = androidx.compose.ui.text.style.TextAlign.Center, fontWeight = FontWeight.Black)
                    }
                } else if (!launchMode) {
                    Button(
                        onClick = { launchMode = true },
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ErrorRed, contentColor = GlowingWhite)
                    ) {
                        Icon(Icons.Default.Speed, contentDescription = null)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("LAUNCH MODE", fontWeight = FontWeight.Black, letterSpacing = 2.sp)
                    }
                } else {
                    Text(
                        "BIRTHDAY MODE: FULL THROTTLE 🏎️💨",
                        color = ErrorRed,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HudElement(label: String, value: String, color: Color = GlowingWhite) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.displaySmall, color = color, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
        Text(label, color = Color.Gray, style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
fun Headlight(color: Color) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(10.dp)
            .graphicsLayer {
                shape = RoundedCornerShape(percent = 50)
                clip = true
            }
            .background(color)
            .shadow(elevation = 30.dp, spotColor = color)
    )
}

@Composable
fun SpeedLines() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(tween(200, easing = LinearEasing)),
        label = ""
    )
    Canvas(modifier = Modifier.fillMaxSize()) {
        for (i in 0..20) {
            drawLine(
                color = Color.White.copy(alpha = 0.2f),
                start = Offset(0f, (i * 100f + offset) % size.height),
                end = Offset(0f, (i * 100f + offset) % size.height + 150f),
                strokeWidth = 2f
            )
        }
    }
}
