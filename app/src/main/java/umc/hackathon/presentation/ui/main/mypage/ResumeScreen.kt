package umc.hackathon.presentation.ui.main.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import umc.hackathon.core.component.AutoResizeTextField
import umc.hackathon.core.component.SimpleButton
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import umc.hackathon.presentation.ui.main.mypage.component.InfoCard
import umc.hackathon.presentation.ui.main.mypage.component.ResumeCard
import umc.hackathon.presentation.ui.main.mypage.component.ResumeTopbar

@Composable
fun ResumeScreen(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ResumeTopbar(
            onBackClick = { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(color=UMCHackathonTheme.colorScheme.white)
        ) {
            item {
                Text(
                    text = "내 정보"
                    , color = UMCHackathonTheme.colorScheme.gray500
                    ,style = UMCHackathonTheme.typography.Bold.copy(fontSize = 18.sp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                InfoCard()
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(
                    text = "구직정보"
                    , color = UMCHackathonTheme.colorScheme.gray500
                    ,style = UMCHackathonTheme.typography.Bold.copy(fontSize = 18.sp)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                ResumeCard(title = "직종", content = "희망 업종을 선택해주세요")
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "지역", content = "희망 근무지역을 선택해주세요")
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "고용형태", content = "희망 고용형태를 선택해주세요")
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "근무시간", content = "희망 근무시간을 선택해주세요")
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "월급", content = "희망 월급을 선택해주세요")
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "학력", content = "현재 최종 학력을 선택해주세요")
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "경력", content = "경력사항을 입력해주세요")
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "장애유형", content = "보유중인 장애유형을 선택해주세요")
                Spacer(modifier = Modifier.height(40.dp))
            }
            item {
                Text(
                    text = "자기소개"
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                AutoResizeTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = "본인을 어필할 수 있는 내용을 입력해주세요\n (ex: 자격증, 대외활동, 봉사 등)"
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            item{
                SimpleButton(
                    text = "저장",
                    enabled = true
                ) { }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun showResumeScreen() {
    UMCHackathonTheme {
        ResumeScreen(
            paddingValues = PaddingValues(),
            navController = NavController(LocalContext.current)
        )
    }
}
