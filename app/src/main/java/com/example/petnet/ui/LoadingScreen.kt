package com.example.petnet.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalAnimationApi
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
        .fillMaxHeight()
        .fillMaxHeight()
) {
    Box(
        modifier = modifier
            .padding(32.dp)
            .background(color = Color(0xFFFFFFFF)),
        contentAlignment = Alignment.TopCenter,

        ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Box(
                modifier = modifier
                    .background(color = Color(0xFFFFFFFF))
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "MEPHI NEWS",
                    color = Color(0xFF03A9F4),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    modifier = Modifier.padding(bottom = 45.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color(0xFF03A9F4),
                modifier = Modifier.padding(top = 45.dp)
            )
        }
    }
}