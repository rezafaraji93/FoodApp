package com.faraji.opeque.feature_home.slides.delivery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faraji.opeque.R
import com.faraji.opeque.core.presentation.components.CustomTextField
import com.faraji.opeque.core.presentation.util.UiEvent
import com.faraji.opeque.core.presentation.util.asString
import com.faraji.opeque.feature_home.component.MenuItem
import com.faraji.opeque.feature_home.presentation.HomeScreenEvent
import com.faraji.opeque.feature_home.presentation.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@Composable
fun DeliverySlideScreen(
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.all_restaurants),
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                state.menuItems?.let { menuItems ->
                    items(menuItems) { item ->
                        MenuItem(
                            menuItem = item,
                            onItemClicked = { viewModel.onEvent(HomeScreenEvent.ClickedOnItem(item.title)) },
                        )
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }
    }
}