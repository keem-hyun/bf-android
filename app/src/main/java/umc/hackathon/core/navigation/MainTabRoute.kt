package umc.hackathon.core.navigation

import androidx.annotation.StringRes
import umc.hackathon.R

enum class MainTabRoute(
    val route: String,
    @StringRes val label: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int,
) {
    Home("home", R.string.tab_home, R.drawable.ic_nav_home_filled,  R.drawable.ic_nav_home),
    JobPost("jobpost", R.string.tab_jobpost,  R.drawable.ic_nav_reco_filled,  R.drawable.ic_nav_reco),
    Community("community", R.string.tab_community,  R.drawable.ic_nav_comu_filled, R.drawable.ic_nav_comu),
    Mypage("mypage", R.string.tab_mypage,  R.drawable.ic_nav_mypage_filled,  R.drawable.ic_nav_mypage) ;
}

