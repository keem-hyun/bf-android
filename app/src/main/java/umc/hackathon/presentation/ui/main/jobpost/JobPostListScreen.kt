package umc.hackathon.presentation.ui.main.jobpost

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import umc.hackathon.R
import umc.hackathon.core.component.ActionBar
import umc.hackathon.core.component.DropDownStyleButton
import umc.hackathon.core.component.GenericBottomSheet
import umc.hackathon.core.component.IconCircleButton
import umc.hackathon.core.component.JobPostListItem
import umc.hackathon.core.component.SearchBar
import umc.hackathon.core.component.SelectItem
import umc.hackathon.core.component.Switch
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import umc.hackathon.data.datasource.MockJobPostingDataSource
import umc.hackathon.presentation.ui.main.jobpost.filters.JobRegionFilter
import umc.hackathon.presentation.ui.main.jobpost.filters.JobTypeFilter
import umc.hackathon.presentation.ui.main.search.SearchScreen

@Preview
@Composable
fun JobPostListScreenPreview() {
    UMCHackathonTheme {
        JobPostListScreen()
    }
}

@Composable
fun JobPostListRoute(
    paddingValues: PaddingValues,
    navController: NavController
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        JobPostListScreen(
            onSearchNavigate = {
                navController.navigate("search")
            },
            onJobPostClick = { jobId ->
                navController.navigate("jobpost_detail/$jobId")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobPostListScreen(
    onSearchNavigate: () -> Unit = {},
    onJobPostClick: (Int) -> Unit = {}
) {
    var selectedJobTypes by remember {
        mutableStateOf<List<SelectItem>>(emptyList())
    }

    var selectedRegions by remember {
        mutableStateOf<List<SelectItem>>(emptyList())
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var personalized by remember {
        mutableStateOf(false)
    }
    
    // 프로필 기반 추천 필터 조건들
    val recommendedJobTypes = listOf(
        SelectItem("재택근무", "재택근무"),
        SelectItem("정규직", "정규직")
    )
    
    val recommendedRegions = listOf(
        SelectItem("서울", "서울"),
        SelectItem("재택근무", "재택근무")
    )

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    var filterType by remember {
        mutableStateOf(JobPostListFilterEnum.NONE)
    }

    GenericBottomSheet(
        containerColor = UMCHackathonTheme.colorScheme.white,
        showBottomSheet = showBottomSheet,
        onDismiss = {
            showBottomSheet = false
        },
        content = {
            when (filterType) {
                JobPostListFilterEnum.NONE -> {
                    Text(
                        text = "필터를 선택해주세요.",
                        modifier = Modifier.padding(16.dp),
                        style = UMCHackathonTheme.typography.Bold
                    )
                }

                JobPostListFilterEnum.JOB_TYPE -> {
                    JobTypeFilter {
                        selectedJobTypes = it
                        showBottomSheet = false
                    }
                }

                JobPostListFilterEnum.REGION -> {
                    JobRegionFilter {
                        selectedRegions = it
                        showBottomSheet = false
                    }
                }

                JobPostListFilterEnum.WORK_HOURS -> {

                }

                JobPostListFilterEnum.SALARY -> {

                }

                JobPostListFilterEnum.EMPLOYMENT_TYPE -> {

                }

                JobPostListFilterEnum.ACADEMIC_ABILITY -> {

                }

                JobPostListFilterEnum.DISABILITY_TYPE -> {

                }
            }
        }
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(UMCHackathonTheme.colorScheme.gray100)
    ) {
        Card(
            Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ), shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 24.dp,
                bottomStart = 24.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = UMCHackathonTheme.colorScheme.white
            )
        ) {
            Column {
                ActionBar()

                SearchBar(
                    searchText = searchText,
                    onSearchTextChange = { searchText = it },
                    onSearchClick = { onSearchNavigate() },
                    placeholderText = "무슨 일 하고 싶으세요? 검색해볼까요?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp)
                )

                Spacer(Modifier.height(16.dp))


                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            item {
                                if (selectedJobTypes.isEmpty()) {
                                    // 선택된 항목이 없을 때
                                    DropDownStyleButton(
                                        text = "직종",
                                        highlited = false,
                                    ) {
                                        filterType = JobPostListFilterEnum.JOB_TYPE
                                        showBottomSheet = true
                                    }
                                } else {
                                    // 선택된 항목이 1개 이상일 때
                                    val buttonText = when (val count = selectedJobTypes.size) {
                                        1 -> selectedJobTypes.first().text // 1개일 경우, 해당 항목의 텍스트를 그대로 사용
                                        2 -> selectedJobTypes.joinToString(" · ") { it.text } // 2개일 경우, 중간점(·)으로 연결
                                        else -> "직종 $count" // 3개 이상일 경우, "직종"과 개수를 표시
                                    }

                                    DropDownStyleButton(
                                        text = buttonText,
                                        highlited = true,
                                    ) {
                                        filterType = JobPostListFilterEnum.JOB_TYPE
                                        showBottomSheet = true
                                    }
                                }
                            }
                            item {
                                if (selectedRegions.isEmpty()) {
                                    // 선택된 지역이 없을 때
                                    DropDownStyleButton(
                                        text = "지역",
                                        highlited = false,
                                    ) {
                                        filterType = JobPostListFilterEnum.REGION
                                        showBottomSheet = true
                                    }
                                } else {
                                    // 선택된 지역이 1개 이상일 때
                                    val buttonText = when (val count = selectedRegions.size) {
                                        1 -> selectedRegions.first().text // 1개일 경우: 지역명 (예: "서울")
                                        2 -> selectedRegions.joinToString(" · ") { it.text } // 2개일 경우: "서울 · 경기"
                                        else -> "지역 $count" // 3개 이상일 경우: "지역 3"
                                    }

                                    DropDownStyleButton(
                                        text = buttonText,
                                        highlited = true, // 항목이 선택되었으므로 highlited는 true로 설정
                                    ) {
                                        filterType = JobPostListFilterEnum.REGION
                                        showBottomSheet = true
                                    }
                                }
                            }

                            item {
                                DropDownStyleButton(
                                    text = "근무시간",
                                    highlited = false,
                                ) {
                                }
                            }

                            item {
                                DropDownStyleButton(
                                    text = "월급",
                                    highlited = false,
                                ) {
                                }
                            }
                        }
                    }

                    Spacer(Modifier.width(8.dp))

                    IconCircleButton(painterResource(R.drawable.ic_filter)) { }
                }

                Spacer(Modifier.height(16.dp))
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Text("프로필 기반 추천")

            Spacer(Modifier.width(10.dp))

            Switch(personalized, { isEnabled ->
                personalized = isEnabled
                // 프로필 기반 추천이 켜지면 추천 태그들을 적용
                if (isEnabled) {
                    selectedJobTypes = recommendedJobTypes
                    selectedRegions = recommendedRegions
                } else {
                    selectedJobTypes = emptyList()
                    selectedRegions = emptyList()
                }
            })
        }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val allJobPostings = MockJobPostingDataSource.getMockJobPostings()
            
            // 프로필 기반 추천 필터 적용
            val filteredJobPostings = if (personalized) {
                // 추천 시나리오: 재택근무 + 정규직/계약직 + 특정 지역 우선
                allJobPostings.filter { jobPosting ->
                    jobPosting.location.contains("재택근무") || 
                    jobPosting.contractType == "정규직" ||
                    jobPosting.location.contains("서울")
                }.sortedByDescending { jobPosting ->
                    // 우선순위: 재택근무 > 정규직 > 서울 지역
                    when {
                        jobPosting.location.contains("재택근무") -> 3
                        jobPosting.contractType == "정규직" -> 2
                        jobPosting.location.contains("서울") -> 1
                        else -> 0
                    }
                }
            } else {
                allJobPostings
            }
            
            items(filteredJobPostings) { jobPosting ->
                JobPostListItem(
                    companyName = jobPosting.company,
                    remainingDays = jobPosting.id,
                    title = jobPosting.title,
                    category = jobPosting.category,
                    workLocation = jobPosting.location,
                    workHours = jobPosting.workHours,
                    employmentType = jobPosting.contractType,
                    salary = jobPosting.salary,
                    onClick = { onJobPostClick(jobPosting.id) }
                )
            }
        }
    }
}