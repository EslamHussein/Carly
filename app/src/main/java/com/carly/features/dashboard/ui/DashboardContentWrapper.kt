package com.carly.features.dashboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.carly.R
import com.carly.core.ui.AllScreenSizesPreview
import com.carly.ui.theme.CarlyTheme
import com.elvishew.xlog.XLog


@Composable
fun DashboardContentWrapper(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Image(
            painter = painterResource(id = R.drawable.dashboard_background_image),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally

        ) {
            Icon(
                modifier = Modifier
                    .size(150.dp),
                painter = painterResource(id = R.drawable.company_image),
                contentDescription = "Company Image",
                tint = MaterialTheme.colorScheme.onSecondary
            )
            content()
        }
    }
}

@AllScreenSizesPreview
@Preview(showBackground = true)
@Composable
private fun DashboardContentWrapperPreview() {
    CarlyTheme {
        DashboardContentWrapper {
            Box(
                Modifier
                    .fillMaxSize()
            )

        }
    }
}
