package umc.hackathon.presentation.ui.main.mypage

import JobPager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.collections.immutable.toPersistentList
import umc.hackathon.core.component.ActionBar
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import umc.hackathon.presentation.ui.main.mypage.component.MypageHeader
import umc.hackathon.presentation.ui.main.mypage.component.SettingSheet

@Composable
fun MypageScreen(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    val selectedIndex = remember { mutableStateOf(0) }
    val contents = listOf("1", "2").toPersistentList()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(color = UMCHackathonTheme.colorScheme.white)
            .fillMaxSize()
    ) {
//        Text("Mypage 화면입니다")
//        Button(onClick = { navController.navigate(MainTabRoute.Search.route) }) {
//            Text("검색으로 이동")
//        }
        ActionBar()
        MypageHeader(name = "신하람", info = "시각장애·청각장애")



            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = UMCHackathonTheme.colorScheme.gray100)

            ) {
                item {
                    Text(
                        text = "나의 지원 내역",
                        color = UMCHackathonTheme.colorScheme.black,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        style = UMCHackathonTheme.typography.Bold

                    )
                }
                item {
                    JobPager()
                }
                item{
                    Text(
                        text = "설정",
                        color = UMCHackathonTheme.colorScheme.black,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        style = UMCHackathonTheme.typography.Bold

                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item{
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ){
                        SettingSheet()
                    }
                }
            }



    }
}

@Preview(showBackground = true)
@Composable
fun showMypage() {
    UMCHackathonTheme {
        MypageScreen(
            paddingValues = PaddingValues(),
            navController = NavController(LocalContext.current)
        )

    }

}