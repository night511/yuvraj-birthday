package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.ui.theme.*

val GoldenColor = Color(0xFFFFD700)
val DarkGolden = Color(0xFFB8860B)
val LightGolden = Color(0xFFFFF8DC)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoldenMissionScreen(onBack: () -> Unit) {
    var missionStarted by remember { mutableStateOf(false) }
    var currentChallenge by remember { mutableStateOf(1) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GOLDEN MISSION", color = GoldenColor, fontFamily = FontFamily.Monospace) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, tint = GoldenColor, contentDescription = "Back")
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
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (MissionSecuritySystem.isLocked()) {
                LockScreenView(onUnlock = {
                    MissionSecuritySystem.unlock()
                    missionStarted = false
                    currentChallenge = 1
                })
            } else if (!missionStarted) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    "👑 VACH AI: GOLDEN MISSION",
                    color = GoldenColor,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "VACH AI has secured a classified birthday reward. 🤖\n\nOnly Yuvraj can unlock it.",
                    color = LightGolden,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = { missionStarted = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GoldenColor, contentColor = DeepBlack),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("START MISSION →", fontWeight = FontWeight.Black, fontSize = 18.sp)
                }
            } else {
                if (currentChallenge <= 3) {
                    Text(
                        "> MISSION ACTIVE",
                        color = GoldenColor,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                
                AnimatedVisibility(visible = currentChallenge == 1) {
                    ChallengeView(
                        title = "CHALLENGE 1 — GOLDEN LOGIC",
                        question = "2x + 6 = 16\n\nWhat is x?",
                        options = listOf("4", "5", "6", "8"),
                        correctAnswer = "5",
                        onSuccess = { currentChallenge = 2 },
                        onFail = {
                            MissionSecuritySystem.lock()
                            onBack()
                        }
                    )
                }

                AnimatedVisibility(visible = currentChallenge == 2) {
                    ChallengeView(
                        title = "CHALLENGE 2 — GOLDEN CODE",
                        question = "What will this code print?\n\nx = 10\ny = 5\nprint(x - y)",
                        options = listOf("2", "5", "10", "15"),
                        correctAnswer = "5",
                        onSuccess = { currentChallenge = 3 },
                        onFail = {
                            MissionSecuritySystem.lock()
                            onBack()
                        }
                    )
                }

                AnimatedVisibility(visible = currentChallenge == 3) {
                    ChallengeView(
                        title = "CHALLENGE 3 — GOLDEN LOGIC",
                        question = "A number is divisible by both 3 and 5.\nWhich number could it be?",
                        options = listOf("20", "25", "30", "32"),
                        correctAnswer = "30",
                        onSuccess = { currentChallenge = 4 },
                        onFail = {
                            MissionSecuritySystem.lock()
                            onBack()
                        }
                    )
                }

                AnimatedVisibility(visible = currentChallenge == 4) {
                    FinalRewardView()
                }
            }
        }
    }
}

@Composable
fun ChallengeView(
    title: String,
    question: String,
    options: List<String>,
    correctAnswer: String,
    onSuccess: () -> Unit,
    onFail: () -> Unit
) {
    val shuffledOptions = remember { options.shuffled() }
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceBlack),
        border = BorderStroke(1.dp, DarkGolden)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                title,
                color = GoldenColor,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Text(
                question,
                color = LightGolden,
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.Monospace
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                shuffledOptions.forEach { option ->
                    Button(
                        onClick = {
                            if (option == correctAnswer) {
                                onSuccess()
                            } else {
                                onFail()
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GlassBlack, contentColor = GoldenColor),
                        border = BorderStroke(1.dp, DarkGolden),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(option, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun FinalRewardView() {
    val clipboardManager = LocalClipboardManager.current
    var copied by remember { mutableStateOf(false) }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            "✓ ALL CHALLENGES COMPLETED",
            color = SuccessGreen,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            "🔓 VACH AI ACCESS GRANTED",
            color = GoldenColor,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "👑 GOLDEN BIRTHDAY REWARD UNLOCKED 👑",
            color = GoldenColor,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center
        )
        
        Card(
            colors = CardDefaults.cardColors(containerColor = GlassBlack),
            border = BorderStroke(2.dp, GoldenColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "KGTG5Z19ZSPACHPP",
                color = GoldenColor,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        
        Button(
            onClick = {
                clipboardManager.setText(AnnotatedString("KGTG5Z19ZSPACHPP"))
                copied = true
            },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GoldenColor, contentColor = DeepBlack),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("COPY CODE 📋", fontWeight = FontWeight.Black, fontSize = 18.sp)
        }
        
        if (copied) {
            Text(
                "✓ CODE COPIED SUCCESSFULLY!",
                color = SuccessGreen,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
fun LockScreenView(onUnlock: () -> Unit) {
    var code by remember { mutableStateOf("") }
    var showKeyboard by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.Lock, contentDescription = "Locked", tint = ErrorRed, modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "SYSTEM LOCKED",
            color = ErrorRed,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "SECURITY PROTOCOL ENGAGED",
            color = Color.Gray,
            fontFamily = FontFamily.Monospace
        )
        
        Spacer(modifier = Modifier.height(60.dp))

        // Hidden trigger
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.Transparent)
                .clickable(
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                    indication = null
                ) { showKeyboard = true }
        )

        if (showKeyboard) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                code.padEnd(3, '_'),
                color = GoldenColor,
                fontFamily = FontFamily.Monospace,
                fontSize = 24.sp,
                letterSpacing = 8.sp
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            val keys = listOf(
                listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
                listOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
                listOf("Z", "X", "C", "V", "B", "N", "M", "DEL")
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                keys.forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        row.forEach { key ->
                            Button(
                                onClick = {
                                    if (key == "DEL") {
                                        if (code.isNotEmpty()) code = code.dropLast(1)
                                    } else {
                                        if (code.length < 3) code += key
                                        val decoded = String(byteArrayOf(66, 77, 87))
                                        if (code.equals(decoded, ignoreCase = true)) {
                                            code = ""
                                            showKeyboard = false
                                            onUnlock()
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .height(48.dp)
                                    .widthIn(min = if (key == "DEL") 60.dp else 32.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = GlassBlack, contentColor = GoldenColor),
                                border = BorderStroke(1.dp, DarkGolden),
                                contentPadding = PaddingValues(0.dp),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(key, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
