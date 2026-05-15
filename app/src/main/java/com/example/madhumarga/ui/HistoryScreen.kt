package com.example.madhumarga.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.madhumarga.data.HiveLog
import com.example.madhumarga.viewmodel.HiveViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: HiveViewModel
) {

    val logs by viewModel.allLogs.collectAsState()

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
                                text = "📜 Hive History",
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Inspection & Activity Records",
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

            if (logs.isEmpty()) {

                val sampleLogs = listOf(

                    Pair(
                        "🐝 Hive inspection completed",
                        "All hive activities are normal"
                    ),

                    Pair(
                        "🍯 Honey collected: 6kg",
                        "Harvest completed successfully"
                    ),

                    Pair(
                        "⚠ Low activity alert generated",
                        "Hive requires inspection"
                    )
                )

                LazyColumn(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(16.dp)
                ) {

                    items(sampleLogs) { item ->

                        Card(

                            modifier = Modifier.fillMaxWidth(),

                            shape = RoundedCornerShape(22.dp),

                            elevation =
                                CardDefaults.cardElevation(
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
                                    text = item.first,

                                    style = MaterialTheme
                                        .typography
                                        .titleMedium,

                                    fontWeight = FontWeight.Bold,

                                    color = Color(0xFF4E342E)
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                Text(
                                    text = item.second,

                                    style = MaterialTheme
                                        .typography
                                        .bodyMedium,

                                    color = Color.Gray
                                )

                                Spacer(
                                    modifier = Modifier.height(12.dp)
                                )

                                HorizontalDivider()

                                Spacer(
                                    modifier = Modifier.height(10.dp)
                                )

                                Text(
                                    text = formatDate(
                                        System.currentTimeMillis()
                                    ),

                                    style = MaterialTheme
                                        .typography
                                        .bodySmall,

                                    color = Color.DarkGray
                                )
                            }
                        }
                    }
                }

            } else {

                LazyColumn(

                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),

                    contentPadding = PaddingValues(16.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(16.dp)
                ) {

                    items(logs) { log ->

                        LogItem(log)
                    }
                }
            }
        }
    }
}

@Composable
fun LogItem(
    log: HiveLog
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

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Text(
                    text = "🐝 Hive ID: ${log.hiveId}",

                    style = MaterialTheme
                        .typography
                        .titleMedium,

                    fontWeight = FontWeight.Bold,

                    color = Color(0xFF4E342E)
                )

                Text(
                    text = formatDate(log.date),

                    style = MaterialTheme
                        .typography
                        .bodySmall,

                    color = Color.Gray
                )
            }

            Spacer(
                modifier = Modifier.height(14.dp)
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

                    Text(
                        text = "🩺 Health Status",

                        fontWeight = FontWeight.SemiBold,

                        color = Color(0xFF5D4037)
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = log.healthStatus
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = "📝 Inspection Notes",

                        fontWeight = FontWeight.SemiBold,

                        color = Color(0xFF5D4037)
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = log.inspectionNotes
                    )

                    if (log.treatmentAdministered.isNotEmpty()) {

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Text(
                            text = "💊 Treatment",

                            fontWeight = FontWeight.SemiBold,

                            color = Color(0xFF5D4037)
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            text = log.treatmentAdministered
                        )
                    }
                }
            }
        }
    }
}

fun formatDate(
    timestamp: Long
): String {

    val sdf = SimpleDateFormat(
        "dd MMM yyyy, HH:mm",
        Locale.getDefault()
    )

    return sdf.format(Date(timestamp))
}