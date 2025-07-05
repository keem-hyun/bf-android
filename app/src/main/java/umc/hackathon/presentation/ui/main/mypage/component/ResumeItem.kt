package umc.hackathon.presentation.ui.main.mypage.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
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


@Composable
fun ResumeItem(
    item : String
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp)
    ) {
        Text(
            text = item,
            color = UMCHackathonTheme.colorScheme.black,
            style = UMCHackathonTheme.typography.SemiBold.copy(fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.weight(1F))

        Icon(
            painter = painterResource(id = R.drawable.ic_resume_plus),
            contentDescription = null

        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewResumeItem(){
    ResumeItem(item = "직종")
}