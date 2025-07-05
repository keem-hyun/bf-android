package umc.hackathon.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Preview
@Composable
fun JobPostListItemPreview() {
    UMCHackathonTheme {
        JobPostListItem(
            "주식회사 레진엔터테인먼트", 19, "[장애인 전형] 사내카페 지원", "외식·음료 > 커피전문점",
            "서울특별시 강남구", "주 5일", "정규직", "월급 110만원"
        )
    }
}

@Composable
fun JobPostListItem(
    companyName: String,
    remainingDays: Int = 0,
    title: String,
    category: String,
    workLocation: String,
    workHours: String,
    employmentType: String,
    salary: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    onBookmarkClick: () -> Unit = { }
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = UMCHackathonTheme.colorScheme.white
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.Top) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = companyName, style = UMCHackathonTheme.typography.Regular.copy(
                        color = UMCHackathonTheme.colorScheme.mainGreen300, fontSize = 13.sp
                    )
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                Text(
                    text = "지원기간 D-${remainingDays}일",
                    style = UMCHackathonTheme.typography.Regular.copy(
                        color = UMCHackathonTheme.colorScheme.mainGreen200, fontSize = 13.sp
                    )
                )

                Spacer(Modifier.width(10.dp))

                Image(
                    modifier = Modifier
                        .size(14.dp, 18.dp)
                        .clickable {
                            onBookmarkClick.invoke()
                        },
                    painter = painterResource(R.drawable.ic_bookmark),
                    contentDescription = null,
                )
            }

            Spacer(Modifier.height(2.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = UMCHackathonTheme.typography.Bold.copy(
                    color = UMCHackathonTheme.colorScheme.black, fontSize = 16.sp
                )
            )

            Spacer(Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = UMCHackathonTheme.colorScheme.mainGreen200,
                        shape = RoundedCornerShape(
                            20.dp
                        )
                    )
                    .padding(12.dp, 8.dp)
            ) {
                Text(
                    text = category, style = UMCHackathonTheme.typography.Regular.copy(
                        fontSize = 13.sp, color = UMCHackathonTheme.colorScheme.mainGreen300
                    )
                )
            }

            Spacer(Modifier.height(8.dp))

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    JobPostListItemTag(workLocation)
                    JobPostListItemTag(workHours)
                    JobPostListItemTag(employmentType)
                }

                Text(
                    text = salary, style = UMCHackathonTheme.typography.Bold.copy(
                        color = UMCHackathonTheme.colorScheme.mainGreen300, fontSize = 18.sp
                    )
                )
            }
        }
    }
}

@Composable
fun JobPostListItemTag(text: String) {
    Box(
        Modifier
            .background(
                color = UMCHackathonTheme.colorScheme.gray200,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(12.dp, 8.dp)
    ) {
        Text(
            text = text, style = UMCHackathonTheme.typography.Regular.copy(
                fontSize = 11.sp,
                color = UMCHackathonTheme.colorScheme.gray500
            )
        )
    }
}