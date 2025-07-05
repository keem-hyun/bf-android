package umc.hackathon.presentation.ui.main.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import umc.hackathon.core.navigation.MainTabRoute

@Composable
fun MypageScreen(
    paddingValues: PaddingValues,
    navController: NavController
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        Text("Mypage 화면입니다")
        Button(onClick = { navController.navigate(MainTabRoute.Search.route) }) {
            Text("검색으로 이동")
        }
    }
}
