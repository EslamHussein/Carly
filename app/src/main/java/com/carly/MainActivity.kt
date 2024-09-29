package com.carly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carly.features.addcar.ui.AddNewCarScreen
import com.carly.features.dashboard.ui.DashboardScreen
import com.carly.features.navigation.CarSelectionDestination
import com.carly.features.navigation.DashboardDestination
import com.carly.ui.theme.CarlyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            CarlyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = DashboardDestination,
                ) {
                    composable<DashboardDestination> {
                        DashboardScreen(navController = navController)
                    }
                    composable<CarSelectionDestination> {
                        AddNewCarScreen(navController = navController)
                    }
                }
            }

        }
    }
}
