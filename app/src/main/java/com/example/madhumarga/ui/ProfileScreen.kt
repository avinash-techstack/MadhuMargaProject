package com.example.madhumarga.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF8E1),
                        Color(0xFFFFECB3)
                    )
                )
            )
    ) {

        Scaffold(
            containerColor = Color.Transparent,

            topBar = {

                TopAppBar(

                    title = {
                        Text(
                            text = "Profile",
                            fontWeight = FontWeight.Bold
                        )
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }

        ) { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(
                            4.dp,
                            Color(0xFFF4B400),
                            CircleShape
                        ),

                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,

                        modifier = Modifier.size(90.dp),

                        tint = Color(0xFF6A1B9A)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Avinash Kumar",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4E342E)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Smart Beekeeper",
                    color = Color(0xFF8D6E63),
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(24.dp),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        ProfileInfoRow(
                            label = "📧 Email",
                            value = "avinashkumar@example.com"
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp)
                        )

                        ProfileInfoRow(
                            label = "📍 Location",
                            value = "Bengaluru, Karnataka"
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp)
                        )

                        ProfileInfoRow(
                            label = "🐝 Total Hives",
                            value = "4"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {

                        Toast.makeText(
                            context,
                            "Logged Out Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        onLogout()
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD32F2F)
                    )
                ) {

                    Text(
                        text = "Logout",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileInfoRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween,

        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF5D4037)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}