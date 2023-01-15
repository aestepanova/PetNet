package com.example.petnet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petnet.R
import com.example.petnet.auth.PasswordRequirements
import com.example.petnet.ui.theme.Montserrat

@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    password: String?,
    onPasswordChanged: (email: String) -> Unit,
    onSubmitForm: ()->Unit,
    onDoneClicked: () -> Unit
) {
    val passwordFocusRequester = remember {FocusRequester()}
    var isPasswordHidden by remember {
        mutableStateOf(true)
    }
    OutlinedTextField(
        visualTransformation = if (isPasswordHidden) {
            PasswordVisualTransformation()
        } else VisualTransformation.None,
        modifier = modifier
            .focusRequester(passwordFocusRequester)
            .background(color = Color.White),
        value = password ?: "",
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFF03A9F4), focusedLabelColor = Color(0xFF03A9F4), cursorColor = Color(0xFF03A9F4), textColor = Color.DarkGray),
        onValueChange = {
            onPasswordChanged(it)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
            )
        },
        textStyle = TextStyle(fontFamily = Montserrat,
            fontWeight = FontWeight.Light),
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable(
                    onClickLabel = if (isPasswordHidden) {
                        stringResource(id =
                        R.string.cd_show_password
                        )
                    } else stringResource(id =
                    R.string.cd_hide_password
                    )
                ) {
                    isPasswordHidden = !isPasswordHidden
                },
                imageVector = if (isPasswordHidden) {
                    Icons.Default.Visibility
                } else Icons.Default.VisibilityOff,
                contentDescription = null
            )
        },
        label = {
            Text(text = stringResource(id = R.string.label_password), fontFamily = Montserrat,
                fontWeight = FontWeight.Light)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions (
            onDone = {
                onDoneClicked()
            }
    )
    )
}
@Composable
fun Requirement(
    modifier: Modifier = Modifier,
    message: String,
    satisfied: Boolean
) {
    val tint = if (satisfied) {
        MaterialTheme.colors.primary
    } else MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
    val requirementStatus = if (satisfied) {
        stringResource(id =
        R.string.password_requirement_satisfied, message)
    } else {
        stringResource(id =
        R.string.password_requirement_not_satisfied, message)
    }
    Row(
        modifier = Modifier.padding(6.dp)
            .semantics(mergeDescendants = true) {
                text = AnnotatedString(requirementStatus)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(12.dp),
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = tint
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.clearAndSetSemantics { },
            text = message,
            fontSize = 12.sp,
            color = tint
        )
    }
}

@Composable
fun PasswordRequirements(
    modifier: Modifier = Modifier,
    satisfiedRequirements: List<PasswordRequirements>
) {
    Column(
        modifier = modifier
    ) {
        PasswordRequirements.values().forEach { requirement ->
            Requirement(
                message = stringResource(
                    id = requirement.label),
                satisfied = satisfiedRequirements.contains(
                    requirement
                )
            )
        }
    }
}
