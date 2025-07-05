package umc.hackathon.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import umc.hackathon.R

enum class MainTabRoute(
    val route: String,
    @StringRes val label: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    Home("home", R.string.tab_home, Icons.Filled.Home, Icons.Outlined.Home),
    Search("search", R.string.tab_search, Icons.Filled.Search, Icons.Outlined.Search),
    Mypage("mypage", R.string.tab_mypage, Icons.Filled.Person, Icons.Outlined.Person);
}

