package com.example.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TARGET PROFILE", color = NeonCyan, fontFamily = FontFamily.Monospace) },
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
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "🖤 About Yuvraj",
                color = GlowingWhite,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                "Yuvraj ek aisa banda hai jise coding, technology aur naye ideas explore karna pasand hai. 💻🤖\n\n" +
                "Abhi-abhi usne apna AI project VACH AI banana start kiya hai. 🚀\n" +
                "Project abhi starting phase mein hai, lekin idea bada hai… aur dekhte hain aage jaakar ye AI kya-kya kamaal karta hai. 👀😂\n\n" +
                "Yuvraj ka style bhi simple nahi hai—favorite color BLACK 🖤 aur favorite car M4 Competition 🏎️🔥.\n\n" +
                "Coding mein kabhi code chalega, kabhi error aayega, aur kabhi ek chhoti si mistake ke liye poora code dobara dekhna padega. 😂💀\n" +
                "Lekin asli developer wahi hai jo error dekhkar laptop band nahi karta… balki \"ek baar aur try karta hoon\" bolta hai. 💻😂\n\n" +
                "Abhi VACH AI ki journey bas shuru hui hai.\n" +
                "Aaj ek idea hai, kal ek project hoga, aur future mein pata nahi kya ban jayega. 🚀\n\n" +
                "Keep coding. Keep experimenting. Keep building. 🖤🤖",
                color = Color.LightGray,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 24.sp
            )
        }
    }
}
