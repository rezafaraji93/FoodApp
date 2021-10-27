package com.faraji.opeque.feature_home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.size.OriginalSize
import com.faraji.opeque.R
import com.faraji.opeque.core.domain.models.MenuItem
import com.faraji.opeque.core.presentation.ui.theme.HintGray
import com.faraji.opeque.core.presentation.ui.theme.OrangeAccent
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    menuItem: MenuItem,
    onItemClicked: () -> Unit = {}
) {
    val loadingState = remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                onItemClicked()
            }
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(400.dp, 200.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .placeholder(
                        visible = loadingState.value,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = HintGray)
                    ),
                painter = rememberImagePainter(
                    data = menuItem.imageUrl,
                    builder = {
                        crossfade(true)
                        size(OriginalSize)
                        listener(
                            onSuccess = { _: ImageRequest, _: ImageResult.Metadata ->
                                loadingState.value = false
                            }
                        )
                    }
                ),
                contentScale = ContentScale.FillWidth,
                contentDescription = menuItem.title
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = menuItem.title,
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                     text = stringResource(id = R.string.item_price, "${menuItem.price}"),
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.surface
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Star,
                        tint = OrangeAccent,
                        contentDescription = "rate"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${menuItem.rate}",
                        style = MaterialTheme.typography.body2.copy(
                            color = MaterialTheme.colors.surface
                        )
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(id = R.string.preparation_time, menuItem.preparationTime),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}