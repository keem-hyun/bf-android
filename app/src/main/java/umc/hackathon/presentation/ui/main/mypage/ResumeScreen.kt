package umc.hackathon.presentation.ui.main.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import umc.hackathon.core.component.AutoResizeTextField
import umc.hackathon.core.component.GenericBottomSheet
import umc.hackathon.core.component.SelectItem
import umc.hackathon.core.component.SimpleButton
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import umc.hackathon.presentation.ui.main.jobpost.JobPostListFilterEnum
import umc.hackathon.presentation.ui.main.jobpost.filters.JobRegionFilter
import umc.hackathon.presentation.ui.main.jobpost.filters.JobTypeFilter
import umc.hackathon.presentation.ui.main.jobpost.filters.JobWorkHoursFilter
import umc.hackathon.presentation.ui.main.mypage.component.InfoCard
import umc.hackathon.presentation.ui.main.mypage.component.ResumeCard
import umc.hackathon.presentation.ui.main.mypage.component.ResumeTopbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumeScreen(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    var minHour by remember { mutableStateOf<Int?>(null) }
    var maxHour by remember { mutableStateOf<Int?>(null) }

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    var filterType by remember {
        mutableStateOf(JobPostListFilterEnum.NONE)
    }

    var selectedJobs by remember {
        mutableStateOf(listOf<String>())
    }

    var selectedJobTypes by remember {
        mutableStateOf<List<SelectItem>>(emptyList())
    }

    var selectedRegions by remember {
        mutableStateOf<List<SelectItem>>(emptyList())
    }

    GenericBottomSheet(
        containerColor = UMCHackathonTheme.colorScheme.white,
        showBottomSheet = showBottomSheet,
        onDismiss = {
            showBottomSheet = false
        },
        content = {
            when (filterType) {
                JobPostListFilterEnum.JOB_TYPE -> {
                    JobTypeFilter {
                        showBottomSheet = false
                        selectedJobTypes = it
                    }
                }

                JobPostListFilterEnum.REGION -> {
                    JobRegionFilter {
                        showBottomSheet = false
                        selectedRegions = it
                    }
                }

                JobPostListFilterEnum.WORK_HOURS -> {
                    JobWorkHoursFilter { min, max ->
                        showBottomSheet = false
                        minHour = min
                        maxHour = max
                    }
                }

                JobPostListFilterEnum.SALARY -> {

                }

                JobPostListFilterEnum.EMPLOYMENT_TYPE -> {

                }

                JobPostListFilterEnum.NONE -> {

                }

                JobPostListFilterEnum.ACADEMIC_ABILITY -> {

                }

                JobPostListFilterEnum.DISABILITY_TYPE -> {

                }
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 96.dp)
            .background(color = UMCHackathonTheme.colorScheme.white)
    ) {
        ResumeTopbar(
            onBackClick = { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(color = UMCHackathonTheme.colorScheme.white)

        ) {
            item {
                Text(
                    text = "내 정보",
                    color = UMCHackathonTheme.colorScheme.gray500,
                    style = UMCHackathonTheme.typography.Bold.copy(fontSize = 18.sp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                InfoCard(
                    gender = gender,
                    age = age,
                    onAgeChange = { age = it },
                    onGenderChange = { gender = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(
                    text = "구직정보",
                    color = UMCHackathonTheme.colorScheme.gray500,
                    style = UMCHackathonTheme.typography.Bold.copy(fontSize = 18.sp)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                ResumeCard(title = "직종", content = "희망 업종을 선택해주세요", selectedJobTypes) {
                    filterType = JobPostListFilterEnum.JOB_TYPE
                    showBottomSheet = true
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "지역", content = "희망 근무지역을 선택해주세요", selectedRegions) {
                    filterType = JobPostListFilterEnum.REGION
                    showBottomSheet = true
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "고용형태", content = "희망 고용형태를 선택해주세요", listOf()) {

                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "근무시간", content = "희망 근무시간을 선택해주세요", listOf()) {
                    filterType = JobPostListFilterEnum.WORK_HOURS
                    showBottomSheet = true
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "월급", content = "희망 월급을 선택해주세요", listOf()) {

                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "학력", content = "현재 최종 학력을 선택해주세요", listOf()) {

                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "경력", content = "경력사항을 입력해주세요", listOf()) {

                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ResumeCard(title = "장애유형", content = "보유중인 장애유형을 선택해주세요", listOf()) {

                }
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
            item {
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
