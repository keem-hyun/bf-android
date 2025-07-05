package umc.hackathon.core.component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.Pretendard
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Composable
fun ActionBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onNotificationClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
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
                // Logo
                Text(
                    text = "배프",
                    style = UMCHackathonTheme.typography.Bold.copy(
                        fontSize = 24.sp,
                        color = colors.mainGreen300)
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

            // Search bar
            OutlinedTextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 17.dp, vertical = 17.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFFFFCDA),
                                Color(0xFFB3F2DA)
                            )
                        ),
                        shape = RoundedCornerShape(18.dp)
                    ),
                placeholder = {
                    Text(
                        "무슨 일 하고 싶으세요? 검색해볼까요?",
                        style = UMCHackathonTheme.typography.Regular.copy(
                            fontSize = 15.sp,
                            color = colors.gray500)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "검색",
                            tint = colors.mainGreen300
                        )
                    }
                },
                shape = RoundedCornerShape(18.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.mainGreen200,
                    unfocusedBorderColor = colors.mainGreen200,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
        }
    }
} 