package com.example.madhumarga.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "🐝",
                fontSize = 70.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "MadhuMarga",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037)
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Smart Beekeeping Assistant",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF8D6E63)
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(24.dp),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {

                    OutlinedTextField(
                        value = email,

                        onValueChange = {
                            email = it
                        },

                        label = {
                            Text("Email")
                        },

                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(16.dp),

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    OutlinedTextField(
                        value = password,

                        onValueChange = {
                            password = it
                        },

                        label = {
                            Text("Password")
                        },

                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(16.dp),

                        visualTransformation =
                            if (passwordVisible)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),

                        trailingIcon = {

                            val image =
                                if (passwordVisible)
                                    Icons.Default.Visibility
                                else
                                    Icons.Default.VisibilityOff

                            IconButton(
                                onClick = {
                                    passwordVisible = !passwordVisible
                                }
                            ) {

                                Icon(
                                    image,
                                    contentDescription = null
                                )
                            }
                        },

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(30.dp)
                    )

                    Button(
                        onClick = onLoginSuccess,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),

                        shape = RoundedCornerShape(18.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF4B400)
                        )
                    ) {

                        Text(
                            text = "Login",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    TextButton(
                        onClick = { }
                    ) {

                        Text(
                            text = "Don't have an account? Sign up",
                            color = Color(0xFF6A1B9A)
                        )
                    }
                }
            }
        }
    }
}