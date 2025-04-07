package pe.edu.upeu.navegationjpc.ui.theme.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pe.edu.upeu.navegationjpc.ui.theme.presentation.screens.BarcodeScanningScreen
import pe.edu.upeu.navegationjpc.ui.theme.presentation.screens.HomeScreen
import pe.edu.upeu.navegationjpc.ui.theme.presentation.screens.ProfileScreen
import pe.edu.upeu.navegationjpc.ui.theme.presentation.screens.SettingsScreen
import pe.edu.upeu.navegationjpc.ui.theme.presentation.screens.CalcUPeU
import pe.edu.upeu.navegationjpc.ui.theme.presentation.screens.RingtoneScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("profile") { ProfileScreen() }
        composable("settings") { SettingsScreen() }
        composable("calc") { CalcUPeU() }
        composable("barcode") { BarcodeScanningScreen(navController) }
        composable("ringtone") { RingtoneScreen() }

    }
}