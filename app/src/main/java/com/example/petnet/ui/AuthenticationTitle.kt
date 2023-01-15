package com.example.petnet.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petnet.R
import com.example.petnet.auth.AuthenticationMode
import com.example.petnet.ui.theme.Brown200
import com.example.petnet.ui.theme.Satoshi

@Preview()
@Composable
fun AuthenticationTitle(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode = AuthenticationMode.SIGN_IN
) {
    Text(
        text = stringResource(
            if (authenticationMode == AuthenticationMode.SIGN_IN) {
                R.string.label_sign_in_to_account
            } else {
                R.string.label_sign_up_for_account
            }
        ),
        fontWeight = FontWeight.Black,
        fontSize = 22.sp,
        color = Brown200,
        fontFamily = Satoshi,
        modifier = Modifier.padding(8.dp).wrapContentSize(Alignment.TopCenter)

    )
}