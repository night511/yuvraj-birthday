package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import kotlinx.coroutines.delay
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodingChallengeScreen(onBack: () -> Unit, onSuccess: () -> Unit) {
    var input by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }
    val keyboard = LocalSoftwareKeyboardController.current

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            status = "> CODE ACCEPTED ✓\n> COMPILATION SUCCESSFUL ✓\n> BIRTHDAY ACCESS GRANTED ✓"
            delay(2000)
            onSuccess()
            onBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SECURITY PROTOCOL", color = NeonCyan, fontFamily = FontFamily.Monospace) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "COMPLETE THE CODE TO PROCEED:",
                color = GlowingWhite,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(24.dp))
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceBlack, RoundedCornerShape(8.dp))
                    .border(1.dp, DarkBorder, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("name = \"", color = SuccessGreen, fontFamily = FontFamily.Monospace)
                        BasicTextField(
                            value = input,
                            onValueChange = { input = it },
                            textStyle = LocalTextStyle.current.copy(
                                color = NeonCyan,
                                fontFamily = FontFamily.Monospace
                            ),
                            modifier = Modifier.width(100.dp)
                        )
                        Text("\"", color = SuccessGreen, fontFamily = FontFamily.Monospace)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("print(\"Happy Birthday \" + name)", color = PrimaryBlue, fontFamily = FontFamily.Monospace)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = {
                    keyboard?.hide()
                    if (input.trim().equals("Yuvraj", ignoreCase = true)) {
                        isSuccess = true
                    } else {
                        status = "> ERROR: INVALID NAME. WHO IS $input?"
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NeonCyan, contentColor = DeepBlack)
            ) {
                Text("RUN CODE", fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            if (status.isNotEmpty()) {
                Text(
                    status,
                    color = if (isSuccess) SuccessGreen else ErrorRed,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

// Minimal BasicTextField wrapper to fit inline
@Composable
fun BasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: androidx.compose.ui.text.TextStyle,
    modifier: Modifier = Modifier
) {
    androidx.compose.foundation.text.BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        modifier = modifier,
        singleLine = true,
        cursorBrush = SolidColor(NeonCyan)
    )
}
