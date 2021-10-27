package com.faraji.opeque.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faraji.opeque.R
import com.faraji.opeque.core.presentation.ui.theme.LightGray
import com.faraji.opeque.core.presentation.ui.theme.TextGray

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    maxLength: Int = 40,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.onBackground
    ),
    onSearchAction: () -> Unit = {},
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChanged: (String) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = TextGray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = text,
            onValueChange = {
                if (it.length <= maxLength)
                    onValueChanged(it)
            },
            textStyle = textStyle,
            maxLines = maxLines,
            placeholder = {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.search_food))
                        }
                        append(" ")
                        val nearText = stringResource(id = R.string.near)
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.surface
                            )
                        ) {
                            append(nearText)
                        }
                        append(" ")
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.santa_monica_ca))
                        }

                    },
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 14.sp
                    ),
                    maxLines = 1
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchAction()
                }
            ),
            singleLine = singleLine,
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }

            } else null,

            )


    }
}