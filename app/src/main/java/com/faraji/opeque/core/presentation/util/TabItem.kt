package com.faraji.opeque.core.presentation.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.faraji.opeque.R
import com.faraji.opeque.feature_home.slides.delivery.DeliverySlideScreen
import com.faraji.opeque.feature_home.slides.perks.PerksSlideScreen
import com.faraji.opeque.feature_home.slides.pick_up.PickUpSlideScreen
import com.faraji.opeque.feature_home.slides.sort.SortSlideScreen

typealias ComposableFun = @Composable () -> Unit

@ExperimentalFoundationApi
sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun) {
    @ExperimentalComposeUiApi
    object DeliverySlide :
        TabItem(
            R.drawable.delivery,
            "Delivery",
            {
                DeliverySlideScreen()
            }
        )

    object PickUpSlide :
        TabItem(
            R.drawable.run,
            "Pickup",
            {
                PickUpSlideScreen()
            }
        )

    object SortSlide :
        TabItem(
            R.drawable.sort,
            "Sort",
            {
                SortSlideScreen()
            }
        )

    object PerksSlide :
        TabItem(
            R.drawable.diamond,
            "Perks",
            {
                PerksSlideScreen()
            }
        )
}