package umc.hackathon.presentation.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import umc.hackathon.R
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.geometry.Offset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.em
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import umc.hackathon.core.component.ActionBar
import umc.hackathon.core.component.SearchBar
import umc.hackathon.model.JobPosting

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    val colors = UMCHackathonTheme.colorScheme

    val currentJob = viewModel.currentJob
    val currentJobIndex by viewModel.currentJobIndex

    // 애니메이션 상태
    var isAnimating by remember { mutableStateOf(false) }
    var swipeDirection by remember { mutableStateOf(0) } // -1: left, 1: right, 0: none
    var showOldCard by remember { mutableStateOf(true) }
    var animatingFromIndex by remember { mutableStateOf(0) }

    val animationProgress by animateFloatAsState(
        targetValue = if (isAnimating) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        finishedListener = {
            // 애니메이션이 완료되면 상태를 리셋
            isAnimating = false
            swipeDirection = 0
            showOldCard = true
        }
    )

    // 스크롤
    val scrollState = rememberScrollState()

    // 카드 영역에서의 스크롤 방향 추적
    var isInCardArea by remember { mutableStateOf(false) }

    // NestedScrollConnection으로 카드 영역의 스크롤과 전체 스크롤 분리
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // 카드 영역에서는 세로 스크롤을 제한하지 않음
                return Offset.Zero
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(colors.gray100)
    ) {
        ActionBar(
            onNotificationClick = { /* 알림 클릭 처리 */ },
        )

        Box(
            modifier = Modifier
                .background(colors.white)
                .padding(horizontal = 16.dp, vertical = 19.dp)
        ) {
            SearchBar(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                onSearchClick = { navController.navigate("search") }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(
                    colors.white,
                    RoundedCornerShape(24.dp)
                )
        )


        Spacer(modifier = Modifier.height(25.dp))

        // 스크롤 가능한 영역 (인사말부터 아래)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .nestedScroll(nestedScrollConnection)
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Column {
                    Text(
                        text = "신하람님,",
                        style = UMCHackathonTheme.typography.Bold.copy(
                            fontSize = 24.sp,
                            color = colors.black
                        )
                    )
                    Text(
                        text = "배프가 맞춤 공고를 추천해드려요!",
                        style = UMCHackathonTheme.typography.Bold.copy(
                            fontSize = 16.sp,
                            color = colors.black
                        )
                    )
                }
            }


            Spacer(modifier = Modifier.height(25.dp))

            // Job listings section with z-index stacking
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                // Next card (appearing from behind) - only show during left swipe
                if (animatingFromIndex < viewModel.jobPostings.value.size - 1 && swipeDirection == -1) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 40.dp)
                            .scale(
                                lerp(0.8f, 1f, animationProgress)
                            )
                            .graphicsLayer {
                                alpha = lerp(0f, 1f, animationProgress)
                                translationX = lerp(300f, 0f, animationProgress)
                            }
                            .background(
                                colors.white,
                                RoundedCornerShape(30.dp)
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // 애니메이션 중에는 빈 카드만 표시
                    }
                }

                // Previous card (appearing from behind) - only show during right swipe
                if (animatingFromIndex > 0 && swipeDirection == 1) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 40.dp)
                            .scale(
                                lerp(0.8f, 1f, animationProgress)
                            )
                            .graphicsLayer {
                                alpha = lerp(0f, 1f, animationProgress)
                                translationX = lerp(-300f, 0f, animationProgress)
                            }
                            .background(
                                colors.white,
                                RoundedCornerShape(30.dp)
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // 애니메이션 중에는 빈 카드만 표시
                    }
                }
                // Third card (bottom layer) - extends beyond padding
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(355.dp)
                        .scale(
                            lerp(1f, 0.9f, animationProgress)
                        )
                        .graphicsLayer {
                            alpha = lerp(1f, 0.4f, animationProgress)
                        }
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(12.dp),
                            ambientColor = Color(0x1AABABAB),
                            spotColor = Color(0x1AABABAB)
                        )
                        .background(
                            colors.gray300,
                            RoundedCornerShape(30.dp)
                        )
                )

                // Second card (middle layer)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(433.dp)
                        .padding(horizontal = 16.dp)
                        .scale(
                            lerp(1f, 0.95f, animationProgress)
                        )
                        .graphicsLayer {
                            alpha = lerp(1f, 0.6f, animationProgress)
                        }
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(12.dp),
                            ambientColor = Color(0x1AABABAB),
                            spotColor = Color(0x1AABABAB)
                        )
                        .background(
                            Color(0xFFEBEBEB),
                            RoundedCornerShape(12.dp)
                        )
                )

                // First card (top layer)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 40.dp)
                        .scale(
                            lerp(1f, 0.8f, animationProgress)
                        )
                        .graphicsLayer {
                            alpha = lerp(1f, 0f, animationProgress)
                            translationX = when (swipeDirection) {
                                -1 -> lerp(0f, -300f, animationProgress) // left swipe
                                1 -> lerp(0f, 300f, animationProgress)   // right swipe
                                else -> 0f
                            }
                        }
                        .pointerInput(currentJobIndex) {
                            detectHorizontalDragGestures(
                                onDragStart = {
                                    isInCardArea = true
                                },
                                onDragEnd = {
                                    isInCardArea = false
                                }
                            ) { change, dragAmount ->
                                // 가로 드래그만 처리하므로 세로 스크롤은 자연스럽게 통과됨
                                if (kotlin.math.abs(dragAmount) > 50 && !isAnimating) {
                                    if (dragAmount < 0) {
                                        // 왼쪽으로 스와이프
                                        animatingFromIndex = currentJobIndex
                                        isAnimating = true
                                        swipeDirection = -1
                                        showOldCard = false
                                        viewModel.nextJob()
                                    } else {
                                        // 오른쪽으로 스와이프
                                        animatingFromIndex = currentJobIndex
                                        isAnimating = true
                                        swipeDirection = 1
                                        showOldCard = false
                                        viewModel.previousJob()
                                    }
                                }
                            }
                        }
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(12.dp),
                            ambientColor = Color(0x1AABABAB),
                            spotColor = Color(0x1AABABAB)
                        )
                        .background(
                            colors.white,
                            RoundedCornerShape(30.dp)
                        )
                        .padding(16.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    // 애니메이션 중에는 데이터를 숨기고, 완료 후에만 표시
                    if (!isAnimating) {
                        currentJob?.let { job ->
                            JobPostingCard(job = job, navController = navController)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun JobPostingCard(
    job: JobPosting,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val colors = UMCHackathonTheme.colorScheme
    var isBookmarked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = job.title,
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 22.sp,
                    color = colors.black,
                    lineHeight = 1.5.em
                ),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            IconButton(
                onClick = { 
                    isBookmarked = !isBookmarked
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isBookmarked) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark
                    ),
                    contentDescription = "북마크",
                    tint = if (isBookmarked) colors.mainYellow300 else colors.gray300,
                    modifier = Modifier.size(18.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = job.company,
            style = UMCHackathonTheme.typography.Regular.copy(
                fontSize = 15.sp,
                color = colors.mainGreen300
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Tags
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        colors.white,
                        RoundedCornerShape(20.dp)
                    )

                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(20.dp),
                        color = colors.mainGreen200
                    )
                    .padding(horizontal = 15.dp, vertical = 8.dp)
            ) {
                Text(
                    text = job.category,
                    style = UMCHackathonTheme.typography.Regular.copy(
                        fontSize = 13.sp,
                        color = colors.mainGreen300
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Location and contract info
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            JobInfoChip(text = job.location)
            JobInfoChip(text = job.contractType)
            JobInfoChip(text = job.workHours)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Salary info
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "월급",
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 16.sp,
                    color = colors.mainGreen300
                )
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = job.salary,
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 18.sp,
                    color = colors.gray600
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Additional info
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "모집분야 및 지원자격",
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 16.sp,
                    color = colors.mainGreen300
                )
            )

            JobDetailItem(label = "모집직무", value = job.jobType)
            JobDetailItem(label = "담당업무", value = job.duties)
            JobDetailItem(label = "채용인원", value = job.recruitCount)
            JobDetailItem(label = "경력사항", value = job.experience)
            JobDetailItem(label = "학력사항", value = job.education)
            JobDetailItem(label = "장애유형", value = job.disabilityType)
        }

        Spacer(modifier = Modifier.height(29.dp))

        // 공고 보러가기 버튼
        Button(
            onClick = { navController.navigate("jobpost_detail/${job.id}") },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.mainGreen300
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "공고 보러가기",
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 15.sp,
                    color = colors.white
                ),
            )
        }
    }
}

@Composable
private fun JobInfoChip(
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = UMCHackathonTheme.colorScheme

    Box(
        modifier = modifier
            .background(
                colors.gray200,
                RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            style = UMCHackathonTheme.typography.Regular.copy(
                fontSize = 13.sp,
                color = colors.gray500
            )
        )
    }
}

@Composable
private fun JobDetailItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    val colors = UMCHackathonTheme.colorScheme

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            style = UMCHackathonTheme.typography.Regular.copy(
                fontSize = 15.sp,
                color = colors.gray600
            ),
            modifier = Modifier.width(80.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = value,
            style = UMCHackathonTheme.typography.Regular.copy(
                fontSize = 15.sp,
                color = colors.gray600
            ),
            modifier = Modifier.weight(1f),
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    UMCHackathonTheme {
        HomeScreen(
            paddingValues = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}

