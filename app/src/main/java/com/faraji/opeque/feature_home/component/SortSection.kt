package com.faraji.opeque.feature_home.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.faraji.opeque.feature_home.util.MenuOrder
import com.faraji.opeque.feature_home.util.OrderType

@Composable
fun SortSection(
    modifier: Modifier = Modifier,
    menuOrder: MenuOrder = MenuOrder.Price(OrderType.Ascending),
    onOrderChange: (MenuOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomRadioButton(
                text = "Price",
                selected = menuOrder is MenuOrder.Price,
                onSelect = { onOrderChange(MenuOrder.Price(menuOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = "Delivery Time",
                selected = menuOrder is MenuOrder.DeliveryTime,
                onSelect = { onOrderChange(MenuOrder.DeliveryTime(menuOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = "Rating",
                selected = menuOrder is MenuOrder.Rating,
                onSelect = { onOrderChange(MenuOrder.Rating(menuOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth()) {
            CustomRadioButton(
                text = "Ascending",
                selected = menuOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(menuOrder.copy(OrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = "Descending",
                selected = menuOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(menuOrder.copy(OrderType.Descending)) }
            )
        }
    }
}