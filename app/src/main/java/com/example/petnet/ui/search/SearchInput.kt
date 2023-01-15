package com.example.petnet.auth


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.petnet.R
import com.example.petnet.ui.theme.Montserrat

@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    search:  String,
    onSearchChanged: (search: String) -> Unit,
    onNextClicked: () -> Unit,
    onClose: () -> Unit,
)
{
    OutlinedTextField(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxWidth(),
        value = search,
        onValueChange = { search ->
            onSearchChanged(search)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFF03A9F4), focusedLabelColor = Color(0xFF03A9F4), cursorColor = Color(0xFF03A9F4), textColor = Color.DarkGray),
        label = {
            Text(text = stringResource(
                id = R.string.label_search
            ),
                fontFamily = Montserrat,
                fontWeight = FontWeight.Light
            )
        },
        textStyle = TextStyle(fontFamily = Montserrat,
            fontWeight = FontWeight.Light),
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.DarkGray
            )
        }, trailingIcon = {
            if(search.isNotEmpty()){
                Button(modifier = Modifier.wrapContentSize(Alignment.Center),onClick = { onClose() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    elevation = ButtonDefaults.elevation(0.dp)) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.DarkGray
                    )
                }

            }


        },

        keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,

    ),
        keyboardActions = KeyboardActions (
                onSearch = {
                    onNextClicked()
                },
        )

    )
}