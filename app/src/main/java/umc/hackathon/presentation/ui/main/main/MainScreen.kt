package umc.hackathon.presentation.ui.main.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import umc.hackathon.core.navigation.MainTabRoute
import umc.hackathon.presentation.ui.main.main.component.MainBottomBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var currentTab by rememberSaveable { mutableStateOf(MainTabRoute.Home) }

    Scaffold(
        modifier = Modifier
            .background(color = UMCHackathonTheme.colorScheme.white)
            .statusBarsPadding(),
        bottomBar = {
            MainBottomBar(
                tabs = MainTabRoute.entries.toList(),
                currentTab = currentTab,
                onTabSelected = {
                    currentTab = it
                    navController.navigateWithOption(it)
                }
            )
        }
    ) { padding ->
        MainNavHost(
            navController = navController,
            padding = padding
        )
    }
}
