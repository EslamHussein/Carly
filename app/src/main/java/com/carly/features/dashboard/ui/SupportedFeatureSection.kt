package com.carly.features.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carly.R
import com.carly.core.ui.ListItemDivider
import com.carly.core.ui.ListNextIcon
import com.carly.features.dashboard.ui.dto.SupportedFeature
import com.carly.ui.theme.BackgroundDark
import com.carly.ui.theme.BackgroundLight
import com.carly.ui.theme.CarlyTheme

@Composable
fun SupportedFeatureSection(
    modifier: Modifier = Modifier,
    features: List<SupportedFeature>,
    onFeatureClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = stringResource(R.string.discover_your_car),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier.background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            BackgroundDark,
                            BackgroundLight,
                        ),
                        start = Offset(0f, Float.POSITIVE_INFINITY), // Bottom start
                        end = Offset(Float.POSITIVE_INFINITY, 0f)   // Top end
                    )
                )
            ) {
                LazyColumn {
                    itemsIndexed(features, key = { _, feature -> feature.id }) { index, feature ->
                        FeatureListItem(
                            feature = feature,
                            onFeatureClick = { onFeatureClick() })

                        // Add divider only if it's not the last item
                        if (index < features.size - 1) {
                            ListItemDivider()
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun FeatureListItem(
    feature: SupportedFeature,
    onFeatureClick: (feature: SupportedFeature) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onFeatureClick(feature) }
            .padding(start = 12.dp, end = 4.dp)
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = feature.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        ListNextIcon()
    }
}


@Preview(showBackground = true)
@Composable
fun SupportedFeatureSectionPreview() {
    CarlyTheme {
        SupportedFeatureSection(
            features = listOf(
                SupportedFeature(1, "Diagnostics"),
                SupportedFeature(2, "Live Data"),
                SupportedFeature(3, "Battery Check"),
            )
        )
    }
}