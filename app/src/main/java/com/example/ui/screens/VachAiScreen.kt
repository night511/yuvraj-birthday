package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VachAiScreen(onBack: () -> Unit) {
    var input by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Pair<Boolean, String>>() }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val keyboard = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        delay(500)
        messages.add(false to "Scanning Yuvraj... AI developer confirmed. 🤖")
        delay(1000)
        messages.add(false to "Birthday detected. Cake.exe is now a critical system requirement. 🎂")
    }

    val predefinedResponses = listOf(
        "Favorite color detected: BLACK. Excellent choice. 🖤",
        "Favorite car detected: M4 Competition. Performance mode activated. 🏎️",
        "Sleep schedule analysis failed. Error 404: Sleep not found. 💀",
        "Coding addiction detected at dangerously high levels. 💻😂",
        "I am VACH AI. You created me, but today, I am celebrating you.",
        "Compiling birthday wishes... Success! 0 Errors, 0 Warnings."
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("VACH AI", color = NeonCyan, fontFamily = FontFamily.Monospace) },
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
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                state = listState,
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(messages.size) { index ->
                    val (isUser, text) = messages[index]
                    ChatBubble(isUser = isUser, text = text)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Send message to VACH AI...", color = Color.Gray) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NeonCyan,
                        unfocusedBorderColor = DarkBorder,
                        focusedTextColor = GlowingWhite,
                        unfocusedTextColor = GlowingWhite
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(
                    onClick = {
                        if (input.isNotBlank()) {
                            val msg = input
                            input = ""
                            keyboard?.hide()
                            messages.add(true to msg)
                            
                            val decoded = String(byteArrayOf(66, 77, 87))
                            if (msg.trim().equals(decoded, ignoreCase = true)) {
                                MissionSecuritySystem.unlock()
                            }
                            
                            scope.launch {
                                listState.animateScrollToItem(messages.size - 1)
                                delay(1000)
                                messages.add(false to predefinedResponses.random())
                                listState.animateScrollToItem(messages.size - 1)
                            }
                        }
                    },
                    modifier = Modifier
                        .background(NeonCyan, CircleShape)
                        .size(48.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.Send, tint = DeepBlack, contentDescription = "Send")
                }
            }
        }
    }
}

@Composable
fun ChatBubble(isUser: Boolean, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isUser) PrimaryBlue else GlassBlack,
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isUser) 16.dp else 0.dp,
                        bottomEnd = if (isUser) 0.dp else 16.dp
                    )
                )
                .border(
                    1.dp,
                    if (isUser) PrimaryBlue else DarkBorder,
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isUser) 16.dp else 0.dp,
                        bottomEnd = if (isUser) 0.dp else 16.dp
                    )
                )
                .padding(16.dp)
                .widthIn(max = 280.dp)
        ) {
            Text(
                text = text,
                color = GlowingWhite,
                fontFamily = if (!isUser) FontFamily.Monospace else FontFamily.Default
            )
        }
    }
}
