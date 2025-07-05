package umc.hackathon.presentation.ui.main.main

import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import umc.hackathon.core.navigation.MainTabRoute
import umc.hackathon.presentation.ui.main.community.CommunityScreen
import umc.hackathon.presentation.ui.main.home.HomeScreen
import umc.hackathon.presentation.ui.main.jobpost.JobPostListRoute
import umc.hackathon.presentation.ui.main.jobpost.JobPostListScreen
import umc.hackathon.presentation.ui.main.jobpost.JobPostDetailScreen
import umc.hackathon.presentation.ui.main.mypage.MypageScreen
import umc.hackathon.presentation.ui.main.search.SearchScreen
import umc.hackathon.presentation.ui.main.apply.JobApplyScreen
import umc.hackathon.presentation.ui.main.mypage.ResumeScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues()
) {
    NavHost(
        navController = navController,
        startDestination = MainTabRoute.Home.route,
        modifier = modifier
    ) {
        composable(MainTabRoute.Home.route) {
            HomeScreen(paddingValues = padding, navController = navController)
        }
        composable(MainTabRoute.JobPost.route) {
            JobPostListRoute(paddingValues = padding, navController = navController)
        }
        composable(MainTabRoute.Mypage.route) {
            MypageScreen(paddingValues = padding, navController = navController)
        }
        composable("search") {
            SearchScreen(paddingValues = padding, navController = navController)
        }

        composable("jobpost_detail/{jobId}") { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")?.toIntOrNull() ?: 1
            JobPostDetailScreen(
                paddingValues = padding, 
                navController = navController,
                jobId = jobId
            )
        }
        composable("job_apply/{jobId}") { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")?.toIntOrNull() ?: 1
            JobApplyScreen(
                paddingValues = padding,
                navController = navController,
                jobId = jobId
            )
        }
        composable("resume_screen") {
            ResumeScreen(
                paddingValues = PaddingValues(),
                navController = navController
            )
        }

    }
}


fun NavController.navigateWithOption(route: MainTabRoute) {
    val options = navOptions {
        launchSingleTop = true
        restoreState = true
        popUpTo(MainTabRoute.Home.route) {
            saveState = true
        }
    }
    this.navigate(route.route, options)
}

