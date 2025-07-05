package umc.hackathon.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.HackathonColorScheme
import umc.hackathon.core.designsystem.theme.Pretendard
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Composable
fun ActionBar(
    onNotificationClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val colors = UMCHackathonTheme.colorScheme
    
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.white),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_main_logo),
                    contentDescription = null,
                    tint = Color.Unspecified
                )



                // Notification icon
                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(colors.white)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.alarm),
                        contentDescription = "알림",
                        tint = colors.gray300,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActionbarView(){
    UMCHackathonTheme{
        ActionBar()
    }
}