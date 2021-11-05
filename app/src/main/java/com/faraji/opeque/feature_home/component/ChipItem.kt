package com.faraji.opeque.feature_home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faraji.opeque.core.presentation.ui.theme.DarkPurple
import com.faraji.opeque.core.presentation.ui.theme.White

@Composable
fun ChipItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    title: String,
    icon: Int,
    onChipClicked: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = 1.dp,
                color = DarkPurple,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = if (isSelected) DarkPurple else Color.Transparent
            )
            .clickable {
                onChipClicked()
            },
        contentAlignment = Alignment.Center

    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = if (isSelected) White else DarkPurple,
                modifier = Modifier.size(15.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.body2.copy(
                    color = if (isSelected) White else DarkPurple,
                    fontSize = 12.sp
                )
            )
        }
    }
}