package com.faraji.opeque.feature_home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faraji.opeque.core.domain.use_cases.GetMenuUseCase
import com.faraji.opeque.core.presentation.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetMenuUseCase
) : ViewModel() {

    private val _textFieldState = mutableStateOf(CustomTextFieldState())
    val textFieldState = _textFieldState

    private val _tabState = mutableStateOf(0)
    val tabState: State<Int> = _tabState

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    val tabs = listOf(
        TabItem.DeliverySlide,
        TabItem.PickUpSlide,
        TabItem.SortSlide,
        TabItem.PerksSlide
    )

    init {
        getMenuItems()
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.ClickedOnItem -> {
                viewModelScope.launch {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            uiText = UiText.DynamicString("Clicked on: ${event.title}")
                        )
                    )
                }
            }
            is HomeScreenEvent.SearchedForFood -> {
                if (event.query.isNotEmpty())
                    viewModelScope.launch {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                uiText = UiText.DynamicString("Searched for: ${event.query}")
                            )
                        )
                    }
            }
            is HomeScreenEvent.EnteredQuery -> {
                _textFieldState.value = textFieldState.value.copy(
                    text = event.query
                )
            }
            is HomeScreenEvent.OnTabClicked -> {
                _tabState.value = event.index
            }
        }
    }

    private fun getMenuItems() {
        viewModelScope.launch {
            when (val result = useCase.invoke()) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        menuItems = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            UiText.unknownError()
                        )
                    )
                }
            }
        }
    }

}