package com.faraji.opeque.feature_home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faraji.opeque.core.presentation.components.CustomTextField
import com.faraji.opeque.core.presentation.ui.theme.White
import com.faraji.opeque.core.presentation.util.TabItem
import com.faraji.opeque.core.presentation.util.UiEvent
import com.faraji.opeque.core.presentation.util.asString
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun HomeScreen(
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val textFieldState = viewModel.textFieldState.value
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val tabs = listOf(
        TabItem.DeliverySlide(scaffoldState),
        TabItem.PickUpSlide,
        TabItem.SortSlide,
        TabItem.PerksSlide
    )

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

    Column(
        modifier = Modifier
            .fillMaxWidth()
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
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .border(
                    1.dp,
                    MaterialTheme.colors.primary,
                    RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp)),
            edgePadding = 0.dp
        ) {
            tabs.forEachIndexed { index, tabItem ->
                LeadingIconTab(
                    text = {
                        Text(
                            text = tabItem.title,
                            style = MaterialTheme.typography.body2.copy(
                                color = if (pagerState.currentPage == index) White else MaterialTheme.colors.primary
                            )
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = tabItem.icon),
                            contentDescription = tabItem.title,
                            tint = if (pagerState.currentPage == index) White else MaterialTheme.colors.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.background,
                    modifier = Modifier.background(
                        if (pagerState.currentPage == index) MaterialTheme.colors.primary else White
                    )
                )
            }
        }
        HorizontalPager(
            count = tabs.size,
            state = pagerState
        ) { page ->
            tabs[page].screen()
        }
    }

}