package com.faraji.opeque.feature_home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faraji.opeque.R
import com.faraji.opeque.core.presentation.components.CustomTextField
import com.faraji.opeque.core.presentation.util.UiEvent
import com.faraji.opeque.core.presentation.util.asString
import com.faraji.opeque.feature_home.component.MenuItem
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val textFieldState = viewModel.textFieldState.value
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            CustomTextField(
                text = textFieldState.text,
                onValueChanged = {
                    viewModel.onEvent(HomeScreenEvent.EnteredQuery(it))
                },
                leadingIcon = Icons.Default.Search,
                onSearchAction = {
                    keyboardController?.hide()
                    viewModel.onEvent(HomeScreenEvent.SearchedForFood(textFieldState.text))
                }
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.all_restaurants),
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.primary
                )
            )
            LazyColumn {
                state.menuItems?.let { menuItems ->
                    items(menuItems) { item ->
                        MenuItem(
                            menuItem = item,
                            onItemClicked = { viewModel.onEvent(HomeScreenEvent.ClickedOnItem(item.title)) }
                        )
                        Divider(modifier = Modifier.padding(vertical = 16.dp))
                    }
                }

            }
        }
    }
}