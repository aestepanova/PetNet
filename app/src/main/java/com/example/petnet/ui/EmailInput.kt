package com.example.petnet.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.petnet.R
import com.example.petnet.ui.theme.Montserrat

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    email:  String,
    onEmailChanged: (email: String) -> Unit,
    onNextClicked: () -> Unit
) {
    OutlinedTextField(
        modifier = modifier.background(color = Color.White),
        value = email,
        onValueChange = { email ->
            onEmailChanged(email)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFF03A9F4), focusedLabelColor = Color(0xFF03A9F4), cursorColor = Color(0xFF03A9F4), textColor = Color.DarkGray),
        label = {
            Text(text = stringResource(
                id = R.string.label_email
            ),
                fontFamily = Montserrat,
                fontWeight = FontWeight.Light
            )
        },
        textStyle = TextStyle(fontFamily = Montserrat,
            fontWeight = FontWeight.Light),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null
            )
        },

        keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Email,

    ),
        keyboardActions = KeyboardActions (
                onNext = {
                    onNextClicked()
                },
        )

    )
}