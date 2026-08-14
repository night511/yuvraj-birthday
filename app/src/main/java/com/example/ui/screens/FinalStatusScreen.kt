package com.example.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.ui.theme.*

@Composable
fun FinalStatusScreen(onReplay: () -> Unit) {
    Scaffold(
        containerColor = DeepBlack
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "> SYSTEM STATUS REPORT",
                color = NeonCyan,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceBlack, RoundedCornerShape(8.dp))
                    .border(1.dp, DarkBorder, RoundedCornerShape(8.dp))
                    .padding(24.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    StatusRow("USER", "YUVRAJ.exe")
                    StatusRow("VERSION", "+1", SuccessGreen)
                    StatusRow("STATUS", "LEGENDARY", NeonCyan)
                    StatusRow("AI", "VACH AI")
                    StatusRow("CODING", "ACTIVE")
                    StatusRow("FAV COLOR", "BLACK")
                    StatusRow("DREAM CAR", "M4 COMPETITION")
                    StatusRow("BUGS", "STILL DETECTED 💀", ErrorRed)
                    StatusRow("CAKE", "REQUIRED 🎂")
                    StatusRow("FUTURE", "LOADING... 🚀", PrimaryBlue)
                }
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Button(
                onClick = onReplay,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GlassBlack, contentColor = GlowingWhite),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, DarkBorder)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("REPLAY PROTOCOL", fontFamily = FontFamily.Monospace)
            }
        }
    }
}

@Composable
fun StatusRow(label: String, value: String, valueColor: Color = GlowingWhite) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            "$label:",
            color = Color.Gray,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.weight(0.4f)
        )
        Text(
            value,
            color = valueColor,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.6f)
        )
    }
}
