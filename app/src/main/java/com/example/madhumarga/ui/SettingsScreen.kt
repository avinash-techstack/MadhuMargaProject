package com.example.madhumarga.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {

    var notificationsEnabled by remember {
        mutableStateOf(true)
    }

    var darkMode by remember {
        mutableStateOf(false)
    }

    var autoBackup by remember {
        mutableStateOf(true)
    }

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

                        Column {

                            Text(
                                text = "⚙ Settings",
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Customize Your App",
                                fontSize = 13.sp
                            )
                        }
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
                    .padding(16.dp),

                verticalArrangement =
                    Arrangement.spacedBy(18.dp)
            ) {

                Card(

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(22.dp),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Text(
                            text = "🔔 App Preferences",

                            style = MaterialTheme
                                .typography
                                .titleLarge,

                            fontWeight = FontWeight.Bold,

                            color = Color(0xFF5D4037)
                        )

                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )

                        SettingSwitchItem(
                            title = "Enable Notifications",
                            subtitle = "Receive hive activity alerts",
                            checked = notificationsEnabled,
                            onCheckedChange = {
                                notificationsEnabled = it
                            }
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp)
                        )

                        SettingSwitchItem(
                            title = "Dark Mode",
                            subtitle = "Reduce eye strain at night",
                            checked = darkMode,
                            onCheckedChange = {
                                darkMode = it
                            }
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp)
                        )

                        SettingSwitchItem(
                            title = "Auto Backup",
                            subtitle = "Backup hive data automatically",
                            checked = autoBackup,
                            onCheckedChange = {
                                autoBackup = it
                            }
                        )
                    }
                }

                Card(

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(22.dp),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Text(
                            text = "🐝 About App",

                            style = MaterialTheme
                                .typography
                                .titleLarge,

                            fontWeight = FontWeight.Bold,

                            color = Color(0xFF5D4037)
                        )

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        InfoRow(
                            title = "Application",
                            value = "MadhuMarga"
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        InfoRow(
                            title = "Version",
                            value = "1.1.0"
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        InfoRow(
                            title = "Developer",
                            value = "Avinash Kumar"
                        )

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFF8E1)
                            ),

                            shape = RoundedCornerShape(16.dp)
                        ) {

                            Text(
                                text =
                                    "MadhuMarga helps beekeepers monitor hive health, honey collection, and inspection history efficiently.",

                                modifier = Modifier.padding(14.dp),

                                style = MaterialTheme.typography.bodyMedium,

                                color = Color(0xFF5D4037)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SettingSwitchItem(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement =
            Arrangement.SpaceBetween,

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,

                style = MaterialTheme.typography.titleMedium,

                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = subtitle,

                style = MaterialTheme.typography.bodySmall,

                color = Color.Gray
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun InfoRow(
    title: String,
    value: String
) {

    Row(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        Text(
            text = title,

            fontWeight = FontWeight.SemiBold,

            color = Color.Gray
        )

        Text(
            text = value,

            fontWeight = FontWeight.Medium
        )
    }
}