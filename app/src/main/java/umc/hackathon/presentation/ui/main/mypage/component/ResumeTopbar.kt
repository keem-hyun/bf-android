package umc.hackathon.presentation.ui.main.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.HackathonColorScheme
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme


@Composable
fun ResumeTopbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color=UMCHackathonTheme.colorScheme.white),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(
            bottomEnd = 24.dp,
            bottomStart = 24.dp
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(color = UMCHackathonTheme.colorScheme.mainGreen200)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(104.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_white),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Text(
                    text = "내 이력서 작성하기", color = UMCHackathonTheme.colorScheme.white
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()


                ) {
                    Text(
                        text = "신하람님,",
                        color = UMCHackathonTheme.colorScheme.white,
                        style = UMCHackathonTheme.typography.Bold.copy(fontSize = 28.sp)
                    )
                    Text(
                        text = "이력서에 들어갈 정보를 입력해주세요!",
                        color = UMCHackathonTheme.colorScheme.white,
                        style = UMCHackathonTheme.typography.Bold.copy(fontSize = 14.sp)
                    )

                }
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_resume_button
                    ),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

            }


        }


    }

}

@Preview(showBackground = true)
@Composable
fun showResumeBar() {
    UMCHackathonTheme {
        ResumeTopbar(
            onBackClick = {}
        )
    }
}