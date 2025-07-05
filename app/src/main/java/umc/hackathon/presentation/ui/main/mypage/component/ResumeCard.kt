package umc.hackathon.presentation.ui.main.mypage.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Composable
fun ResumeCard(
    modifier: Modifier = Modifier,
    title: String,
    content: String
) {
    var isSelected by remember { mutableStateOf(false) }

    val borderColor = if (isSelected) UMCHackathonTheme.colorScheme.mainGreen300
    else UMCHackathonTheme.colorScheme.gray200

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { isSelected = !isSelected },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = UMCHackathonTheme.colorScheme.white
        ),
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(end = 20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = title,
                    color = UMCHackathonTheme.colorScheme.black,
                    style = UMCHackathonTheme.typography.Bold.copy(fontSize = 16.sp),
                )
                Text(
                    text = content,
                    color = UMCHackathonTheme.colorScheme.gray500,
                    style = UMCHackathonTheme.typography.Regular.copy(fontSize = 15.sp),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_resume_plus),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun showResumeCard(){
    UMCHackathonTheme {
        ResumeCard(title = "이력서 제목", content = "이력서 내용")

    }
}