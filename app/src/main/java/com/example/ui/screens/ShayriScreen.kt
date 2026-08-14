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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShayriScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("POETRY PROTOCOL", color = NeonCyan, fontFamily = FontFamily.Monospace) },
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
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "🖤 Yuvraj Ke Naam",
                color = GlowingWhite,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Text(
                "Naya idea hai, nayi kahani hai,\n" +
                "VACH AI ki abhi toh shuruaat purani nahi. 🤖\n" +
                "Aaj coding ka safar bas shuru hua hai,\n" +
                "Kal kya banega, iska kisi ko pata nahi. 🚀",
                color = Color.LightGray,
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )

            Text(
                "Black hai style, M4 hai khwaab, 🖤🏎️\n" +
                "Coding ke saath banayega apna jawaab,\n" +
                "Aaj AI banana seekh raha hai yaar,\n" +
                "Kal shayad khud banaye technology ka bazaar. 😂💻",
                color = Color.LightGray,
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )

            Text(
                "Errors aayenge, bugs bhi satayenge,\n" +
                "Kabhi code chalega, kabhi confuse karayenge,\n" +
                "Bas rukna mat, ye journey hai khaas,\n" +
                "VACH AI ke saath badhta rahe tera har ek step aur har ek raaz. 🤖🚀",
                color = Color.LightGray,
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "🎂 Happy Birthday, Yuvraj!",
                color = SuccessGreen,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                "Teri AI journey abhi shuru hui hai…\n" +
                "Ab dekhte hain future mein kya-kya banta hai! 🔥",
                color = NeonCyan,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }
    }
}
