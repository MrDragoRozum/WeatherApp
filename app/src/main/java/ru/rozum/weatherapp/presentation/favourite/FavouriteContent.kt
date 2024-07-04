package ru.rozum.weatherapp.presentation.favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.rozum.weatherapp.presentation.theme.CardGradients
import ru.rozum.weatherapp.presentation.theme.Gradient

@Composable
fun FavouriteContent(modifier: Modifier = Modifier, component: FavouriteComponent) {
    val state by component.model.collectAsState()

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(
            items = state.cityItems,
            key = { _, item -> item.city.id }
        ) { index, item ->
            CityCard(cityItem = item, index = index)
        }
    }
}

@Composable
private fun CityCard(
    cityItem: FavouriteStore.State.CityItem,
    index: Int
) {
    val gradient = getGradientByIndex(index)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = 16.dp,
                shape = MaterialTheme.shapes.extraLarge,
                spotColor = gradient.shadowColor),
        shape = MaterialTheme.shapes.extraLarge,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .sizeIn(minHeight = 196.dp)
                .background(gradient.primaryGradient)
                .drawBehind {
                    drawCircle(
                        brush = gradient.secondaryGradient,
                        center = Offset(
                            x = center.x,
                            y = center.y * 2
                        )
                    )
                }
                .padding(24.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.BottomStart),
                text = cityItem.city.name,
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

private fun getGradientByIndex(index: Int): Gradient {
    val gradient = CardGradients.gradients
    return gradient[index % gradient.size]
}
