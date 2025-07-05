import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import umc.hackathon.core.component.JobPostListItem
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import umc.hackathon.model.JobPosting
import kotlin.math.abs
import androidx.compose.ui.graphics.Color as ComposeColor

@OptIn(ExperimentalPagerApi::class)
@Composable
fun JobPager(jobList: List<JobPosting>) {
    val pagerState = rememberPagerState()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            count = jobList.chunked(2).size, // 한 페이지에 2개씩
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            itemSpacing = 12.dp,
            modifier = Modifier.height(320.dp)
        ) { page ->
            val items = jobList.chunked(2)[page]
            Column(modifier = Modifier.width(screenWidth - 32.dp)) {
                items.forEach { job ->
                    JobPostListItem(
                        companyName = job.company,
                        remainingDays = 5, // D-day는 mock으로 넣거나 계산 함수 넣어도 좋음
                        title = job.title,
                        category = job.category,
                        workLocation = job.location,
                        workHours = job.workHours,
                        employmentType = job.contractType,
                        salary = job.salary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        AnimatedPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            activeColor = UMCHackathonTheme.colorScheme.mainGreen300,
            inactiveColor = UMCHackathonTheme.colorScheme.gray300
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AnimatedPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: ComposeColor = ComposeColor.Blue,
    inactiveColor: ComposeColor = ComposeColor.Gray,
    indicatorWidth: Dp = 8.dp,
    indicatorHeight: Dp = 8.dp,
    spacing: Dp = 12.dp
) {
    val density = LocalDensity.current


    val currentPage = pagerState.currentPage + pagerState.currentPageOffset

    Canvas(
        modifier = modifier
            .height(indicatorHeight)
            .width(
                indicatorWidth * pagerState.pageCount +
                        spacing * (pagerState.pageCount - 1) +
                        indicatorWidth
            )
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val indicatorWidthPx = with(density) { indicatorWidth.toPx() }
        val indicatorHeightPx = with(density) { indicatorHeight.toPx() }
        val spacingPx = with(density) { spacing.toPx() }


        for (i in 0 until pagerState.pageCount) {
            val startX = i * (indicatorWidthPx + spacingPx)
            val isActive = i == pagerState.currentPage

            drawRoundRect(
                color = if (isActive) activeColor else inactiveColor,
                topLeft = Offset(startX, (canvasHeight - indicatorHeightPx) / 2),
                size = Size(indicatorWidthPx, indicatorHeightPx),
                cornerRadius = CornerRadius(indicatorHeightPx / 2)
            )
        }


        val pageOffset = pagerState.currentPageOffset
        if (abs(pageOffset) > 0.0001f) {
            val currentPageIndex = pagerState.currentPage
            val nextPageIndex = if (pageOffset > 0) currentPageIndex + 1 else currentPageIndex - 1

            if (nextPageIndex >= 0 && nextPageIndex < pagerState.pageCount) {
                val startPageIndex = minOf(currentPageIndex, nextPageIndex)
                val endPageIndex = maxOf(currentPageIndex, nextPageIndex)

                val startX = startPageIndex * (indicatorWidthPx + spacingPx)
                val endX = endPageIndex * (indicatorWidthPx + spacingPx) + indicatorWidthPx


                val progress = abs(pageOffset)
                val connectionWidth = (endX - startX) * progress


                drawRoundRect(
                    color = activeColor,
                    topLeft = Offset(startX, (canvasHeight - indicatorHeightPx) / 2),
                    size = Size(connectionWidth, indicatorHeightPx),
                    cornerRadius = CornerRadius(indicatorHeightPx / 2)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun showJobPager(){
//    UMCHackathonTheme {
//        JobPager(
//        )
//    }
//}