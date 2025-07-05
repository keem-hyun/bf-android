package umc.hackathon.presentation.ui.main.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Composable
fun MypageHeader(
    name: String,
    info: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color=UMCHackathonTheme.colorScheme.white)
            .height(160.dp)
            .padding(top= 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(4.dp)

    ){

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 4.dp)
                .background(UMCHackathonTheme.colorScheme.white)
        ) {


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mypage_main),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start = 14.dp)
                ) {
                    Text(
                        text = "$name 님",
                        color = UMCHackathonTheme.colorScheme.black,
                        style = UMCHackathonTheme.typography.Bold.copy(fontSize = 20.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = info,
                        color = UMCHackathonTheme.colorScheme.gray500,
                        style = UMCHackathonTheme.typography.Regular.copy(fontSize = 14.sp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // 오른쪽 버튼
                Text(
                    text = "내 정보 수정",
                    modifier = Modifier
                        .background(
                            color = UMCHackathonTheme.colorScheme.gray200,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    color = UMCHackathonTheme.colorScheme.black,
                    style = UMCHackathonTheme.typography.Regular.copy(fontSize = 12.sp)
                )
            }
            Spacer(modifier =Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(42.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = UMCHackathonTheme.colorScheme.mainGreen300,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_nav_home_filled),
                            contentDescription = null,
                            tint = UMCHackathonTheme.colorScheme.mainGreen300,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "최근 본 공고",
                            color = UMCHackathonTheme.colorScheme.mainGreen300,
                            style = UMCHackathonTheme.typography.Regular.copy(fontSize = 14.sp)
                        )
                    }
                }

                Divider(
                    color = UMCHackathonTheme.colorScheme.gray300,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 8.dp)
                        .width(1.dp)
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_nav_comu_filled),
                            contentDescription = null,
                            tint = UMCHackathonTheme.colorScheme.mainGreen300,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "스크랩",
                            color = UMCHackathonTheme.colorScheme.mainGreen300,
                            style = UMCHackathonTheme.typography.Regular.copy(fontSize = 14.sp)
                        )
                    }
                }
            }
        }

    }




}


@Preview(showBackground = true)
@Composable
fun showMypageHeader() {
    UMCHackathonTheme {
        MypageHeader(name = "신하람", info = "시각장애·청각장애")
    }


}