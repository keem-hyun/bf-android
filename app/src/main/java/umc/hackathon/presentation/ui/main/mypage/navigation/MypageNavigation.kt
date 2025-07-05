package umc.hackathon.presentation.ui.main.mypage.navigation

import androidx.navigation.NavController
import umc.hackathon.presentation.ui.main.mypage.ResumeScreen

class MypageNavigation {
    fun navigateToResumeScreen(navController: NavController) {
        navController.navigate("resume_screen")
    }
}
