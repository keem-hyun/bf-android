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
import kotlin.math.abs
import androidx.compose.ui.graphics.Color as ComposeColor

@OptIn(ExperimentalPagerApi::class)
@Composable
fun JobPager() {
    val pagerState = rememberPagerState()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val jobList = listOf(1, 2, 3)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            count = jobList.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            itemSpacing = 12.dp,
            modifier = Modifier.height(320.dp)
        ) { page ->
            Column(
                modifier = Modifier
                    .width(screenWidth - 32.dp)
            ) {
                JobPostListItem(
                    "주식회사 레진엔터테인먼트", 19, "[장애인 전형] 사내카페 지원", "외식·음료 > 커피전문점",
                    "서울특별시 강남구", "주 5일", "정규직", "월급 110만원"
                )
                Spacer(modifier = Modifier.height(8.dp))
                JobPostListItem(
                    "주식회사 레진엔터테인먼트", 19, "[장애인 전형] 사내카페 지원", "외식·음료 > 커피전문점",
                    "서울특별시 강남구", "주 5일", "정규직", "월급 110만원"
                )
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

@Preview(showBackground = true)
@Composable
fun showJobPager(){
    UMCHackathonTheme {
        JobPager()
    }
}