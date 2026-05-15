package com.example.madhumarga.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.madhumarga.data.Hive
import com.example.madhumarga.viewmodel.HiveViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: HiveViewModel,
    onAddHiveClick: () -> Unit
) {

    val hiveList by viewModel.hives.collectAsState()

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
                                text = "🐝 Madhu Marga",
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Smart Beekeeping Assistant",
                                fontSize = 13.sp
                            )
                        }
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            },

            floatingActionButton = {

                FloatingActionButton(
                    onClick = onAddHiveClick,

                    containerColor = Color(0xFFF4B400),

                    contentColor = Color.White
                ) {

                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add Hive"
                    )
                }
            }

        ) { padding ->

            if (hiveList.isEmpty()) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),

                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "🐝",
                            fontSize = 70.sp
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Text(
                            text = "No Hives Added Yet",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Tap + button to add your first hive",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray
                        )
                    }
                }

            } else {

                LazyColumn(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),

                    contentPadding = PaddingValues(16.dp),

                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    items(hiveList) { hive ->

                        HiveCard(
                            hive = hive,

                            suggestion = viewModel.getSuggestion(hive),

                            onDelete = {
                                viewModel.deleteHive(hive)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HiveCard(
    hive: Hive,
    suggestion: String,
    onDelete: () -> Unit
) {

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
            modifier = Modifier.padding(18.dp)
        ) {

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "📍 ${hive.location}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4E342E)
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "Hive Monitoring Details",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }

                IconButton(
                    onClick = onDelete
                ) {

                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF8E1)
                ),

                shape = RoundedCornerShape(18.dp)
            ) {

                Column(
                    modifier = Modifier.padding(14.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "🍯 Honey",
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "${hive.honeyCollected} kg"
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "📊 Activity",
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = hive.activityLevel
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "👑 Queen Bee",
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text =
                                if (hive.queenPresent)
                                    "Present"
                                else
                                    "Absent"
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            HorizontalDivider()

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            if (suggestion == "Healthy") {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8F5E9)
                    ),

                    shape = RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = "✅ Hive Status: Healthy",

                        modifier = Modifier.padding(12.dp),

                        color = Color(0xFF2E7D32),

                        fontWeight = FontWeight.Bold
                    )
                }

            } else {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFEBEE)
                    ),

                    shape = RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = "⚠ Immediate Hive Inspection Required",

                        modifier = Modifier.padding(12.dp),

                        color = Color.Red,

                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}