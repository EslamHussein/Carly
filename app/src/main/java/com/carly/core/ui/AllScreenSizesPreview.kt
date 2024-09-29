package com.carly.core.ui

import androidx.compose.ui.tooling.preview.Preview

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Preview(name = "Small screen", widthDp = 320, heightDp = 640)
@Preview(name = "Normal screen", widthDp = 360, heightDp = 740)
@Preview(name = "Large screen", widthDp = 600, heightDp = 960)
annotation class AllScreenSizesPreview
