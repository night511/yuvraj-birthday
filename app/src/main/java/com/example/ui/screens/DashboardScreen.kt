package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.ui.theme.*

@Composable
fun DashboardScreen(
    onNavigateToVach: () -> Unit,
    onNavigateToCode: () -> Unit,
    onNavigateToM4: () -> Unit,
    onNavigateToSurprise: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToShayri: () -> Unit,
    onNavigateToGoldenMission: () -> Unit,
    onNavigateToFinal: () -> Unit
) {
    var audioMuted by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = DeepBlack
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "SYSTEM HUB",
                    style = MaterialTheme.typography.headlineMedium,
                    color = NeonCyan,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = { audioMuted = !audioMuted }) {
                    Icon(
                        imageVector = if (audioMuted) Icons.Default.VolumeOff else Icons.Default.VolumeUp,
                        contentDescription = "Toggle Audio",
                        tint = NeonCyan
                    )
                }
            }
            
            Divider(color = DarkBorder)

            DashboardCard(
                title = "ABOUT TARGET",
                subtitle = "Profile Analysis",
                icon = Icons.Default.Person,
                onClick = onNavigateToAbout
            )
            
            DashboardCard(
                title = "POETRY PROTOCOL",
                subtitle = "Shayri Decrypted",
                icon = Icons.Default.MenuBook,
                onClick = onNavigateToShayri
            )
            
            DashboardCard(
                title = "VACH AI CORE",
                subtitle = "Initialize neural network chat",
                icon = Icons.Default.SmartToy,
                onClick = onNavigateToVach
            )
            
            DashboardCard(
                title = "SECURITY PROTOCOL",
                subtitle = "Coding Challenge Override",
                icon = Icons.Default.Code,
                onClick = onNavigateToCode
            )
            
            DashboardCard(
                title = "M4 PERFORMANCE",
                subtitle = "Launch Vehicle Simulator",
                icon = Icons.Default.DirectionsCar,
                onClick = onNavigateToM4
            )
            
            DashboardCard(
                title = "VACH AI: GOLDEN MISSION",
                subtitle = "Secret Birthday Reward",
                icon = Icons.Default.Star,
                onClick = onNavigateToGoldenMission
            )
            
            DashboardCard(
                title = "CLASSIFIED",
                subtitle = "Run Secret Protocol",
                icon = Icons.Default.Lock,
                onClick = onNavigateToSurprise,
                isWarning = true
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = onNavigateToFinal,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NeonCyan, contentColor = DeepBlack),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("INITIATE BIRTHDAY SEQUENCE", fontWeight = FontWeight.Black)
            }
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    isWarning: Boolean = false
) {
    val borderColor = if (isWarning) ErrorRed else DarkBorder
    val iconColor = if (isWarning) ErrorRed else NeonCyan
    
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceBlack),
        border = BorderStroke(1.dp, borderColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    title,
                    color = GlowingWhite,
                    fontWeight = FontWeight.Bold,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                )
                Text(
                    subtitle,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}
