package umc.hackathon.presentation.ui.main.mypage

import JobPager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.collections.immutable.toPersistentList
import umc.hackathon.core.component.ActionBar
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import umc.hackathon.data.datasource.MockJobPostingDataSource
import umc.hackathon.model.JobPosting
import umc.hackathon.presentation.ui.main.mypage.component.AddButton
import umc.hackathon.presentation.ui.main.mypage.component.MypageHeader
import umc.hackathon.presentation.ui.main.mypage.component.SettingSheet
import umc.hackathon.presentation.ui.main.mypage.navigation.MypageNavigation

@Composable
fun MypageScreen(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    val selectedIndex = remember { mutableIntStateOf(0) }
    val contents = listOf("1", "2").toPersistentList()
    val jobDataSource = remember { MockJobPostingDataSource() }
    val jobList by produceState(initialValue = emptyList<JobPosting>()) {
        value = jobDataSource.getRecommendJobPostings()
    }


    val mypageNavigation = MypageNavigation()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = UMCHackathonTheme.colorScheme.white)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ActionBar()
            MypageHeader(name = "신하람", info = "시각장애·청각장애")

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
                    Spacer(modifier = Modifier.height(12.dp))
                }
                item {
                    JobPager(jobList = jobList)
                }
                item {
                    Text(
                        text = "설정",
                        color = UMCHackathonTheme.colorScheme.black,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        style = UMCHackathonTheme.typography.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        SettingSheet()
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp, end = 16.dp)
        ) {
            AddButton(
                isExpanded = selectedIndex.value == 0,
                onClick = {
                    mypageNavigation.navigateToResumeScreen(navController)
                }
            )
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
