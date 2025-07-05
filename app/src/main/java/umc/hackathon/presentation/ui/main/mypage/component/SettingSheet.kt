package umc.hackathon.presentation.ui.main.mypage.component

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme


@Composable
fun SettingSheet(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    ){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = UMCHackathonTheme.colorScheme.white
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 28.dp, vertical = 22.dp)
        ){
            Text(
                text = "서비스 설정",
                color = UMCHackathonTheme.colorScheme.gray400,
                style = UMCHackathonTheme.typography.SemiBold.copy(fontSize = 14.sp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(start=6.dp),
                    text = "알림 설정",
                    color = UMCHackathonTheme.colorScheme.black,
                    style = UMCHackathonTheme.typography.SemiBold.copy(fontSize = 15.sp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter= painterResource(id= R.drawable.ic_mypage_setting_arrow),
                    contentDescription = null
                )
                
            }

            Text(
                text = "고객 센터",
                color = UMCHackathonTheme.colorScheme.gray400,
                style = UMCHackathonTheme.typography.SemiBold.copy(fontSize = 14.sp)
            )
            Text(
                modifier = Modifier
                    .padding(start=6.dp),
                text = "1:1 문의",
                color = UMCHackathonTheme.colorScheme.black,
                style = UMCHackathonTheme.typography.SemiBold.copy(fontSize = 15.sp)
            )
            Text(
                modifier = Modifier
                    .padding(start=6.dp),
                text = "공지사항",
                color = UMCHackathonTheme.colorScheme.black,
                style = UMCHackathonTheme.typography.SemiBold.copy(fontSize = 15.sp)
            )
            Text(
                text = "기타",
                color = UMCHackathonTheme.colorScheme.gray400,
                style = UMCHackathonTheme.typography.SemiBold.copy(fontSize = 14.sp)
            )
            Text(
                modifier = Modifier
                    .padding(start=6.dp),
                text = "회원탈퇴",
                color = UMCHackathonTheme.colorScheme.black,
                style = UMCHackathonTheme.typography.SemiBold.copy(fontSize = 15.sp)
            )
            Text(
                modifier = Modifier
                    .padding(start=6.dp),
                text = "로그아웃",
                color = UMCHackathonTheme.colorScheme.black,
                style = UMCHackathonTheme.typography.SemiBold.copy(fontSize = 15.sp)
            )

        }

    }

}

@Preview(showBackground = true)
@Composable
fun SettingSheetPreview(){
    UMCHackathonTheme {
        SettingSheet()
    }

}