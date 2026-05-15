package com.example.madhumarga.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.madhumarga.data.Hive
import com.example.madhumarga.data.HiveLog
import com.example.madhumarga.viewmodel.HiveViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHiveScreen(
    viewModel: HiveViewModel,
    onBack: () -> Unit
) {

    var location by remember {
        mutableStateOf("")
    }

    var honey by remember {
        mutableStateOf("")
    }

    var queenPresent by remember {
        mutableStateOf(true)
    }

    var activityLevel by remember {
        mutableStateOf("Normal")
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
                        Text(
                            text = "🐝 Add New Hive",
                            fontWeight = FontWeight.Bold
                        )
                    },

                    navigationIcon = {

                        IconButton(
                            onClick = onBack
                        ) {

                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
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
                    .padding(20.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),

                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Hive Information",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5D4037)
                )

                Text(
                    text = "Manage your beekeeping smartly",
                    color = Color(0xFF8D6E63),
                    fontSize = 16.sp
                )

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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),

                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {

                        OutlinedTextField(
                            value = location,

                            onValueChange = {
                                location = it
                            },

                            label = {
                                Text("Hive Location")
                            },

                            modifier = Modifier.fillMaxWidth(),

                            shape = RoundedCornerShape(16.dp)
                        )

                        OutlinedTextField(
                            value = honey,

                            onValueChange = {
                                honey = it
                            },

                            label = {
                                Text("Honey Collected (kg)")
                            },

                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),

                            modifier = Modifier.fillMaxWidth(),

                            shape = RoundedCornerShape(16.dp)
                        )

                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFF3CD)
                            ),

                            shape = RoundedCornerShape(18.dp)
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),

                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Checkbox(
                                    checked = queenPresent,

                                    onCheckedChange = {
                                        queenPresent = it
                                    }
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Queen Bee Present",
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        Column {

                            Text(
                                text = "Activity Level",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            val activities =
                                listOf("Low", "Normal", "High")

                            Row(
                                horizontalArrangement =
                                    Arrangement.spacedBy(10.dp)
                            ) {

                                activities.forEach { level ->

                                    FilterChip(
                                        selected =
                                            activityLevel == level,

                                        onClick = {
                                            activityLevel = level
                                        },

                                        label = {
                                            Text(level)
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Button(
                            onClick = {

                                if (location.isNotBlank()) {

                                    val newHive = Hive(
                                        location = location,
                                        queenPresent = queenPresent,
                                        activityLevel = activityLevel,
                                        honeyCollected =
                                            honey.toIntOrNull() ?: 0
                                    )

                                    viewModel.addHive(newHive)

                                    viewModel.addLog(

                                        HiveLog(
                                            hiveId = 0,

                                            inspectionNotes =
                                                "Initial setup at $location",

                                            healthStatus = "New"
                                        )
                                    )

                                    onBack()
                                }
                            },

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(58.dp),

                            enabled = location.isNotBlank(),

                            shape = RoundedCornerShape(18.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF4B400)
                            )
                        ) {

                            Text(
                                text = "Save Hive",
                                fontSize = 18.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}