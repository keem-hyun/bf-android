package umc.hackathon.presentation.ui.main.jobpost

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import umc.hackathon.R
import umc.hackathon.core.component.ActionBar
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import umc.hackathon.model.JobPosting

@Composable
fun JobPostDetailScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    jobId: Int,
    viewModel: JobPostDetailViewModel = hiltViewModel()
) {
    val colors = UMCHackathonTheme.colorScheme
    val scrollState = rememberScrollState()
    
    val jobPosting by viewModel.jobPosting.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    
    LaunchedEffect(jobId) {
        viewModel.loadJobPosting(jobId)
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(colors.gray100)
    ) {
        // Custom Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.white)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "뒤로 가기",
                    tint = colors.gray600
                )
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Text(
                text = "공고 상세",
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 18.sp,
                    color = colors.black
                )
            )
        }
        
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = colors.mainGreen300)
                }
            }
            jobPosting != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(scrollState)
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                colors.white,
                                RoundedCornerShape(16.dp)
                            )
                            .padding(20.dp)
                    ) {
                        JobPostDetailCard(job = jobPosting!!)
                    }
                }
            }
            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "공고를 찾을 수 없습니다.",
                        style = UMCHackathonTheme.typography.Regular.copy(
                            fontSize = 16.sp,
                            color = colors.gray600
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun JobPostDetailCard(
    job: JobPosting,
    modifier: Modifier = Modifier
) {
    val colors = UMCHackathonTheme.colorScheme
    var isBookmarked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            JobInfoChip(text = job.location)
            JobInfoChip(text = job.contractType)
            JobInfoChip(text = job.workHours)
        }

        Spacer(modifier = Modifier.height(20.dp))

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

        Button(
            onClick = { /* 지원하기 클릭 처리 */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.mainGreen300
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "지원하기",
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 15.sp,
                    color = colors.white
                )
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