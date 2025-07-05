package umc.hackathon.presentation.ui.main.mypage.component

import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.DefaultTab.AlbumsTab.value
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    gender: String = "",
    age: String = "",
    onGenderChange: (String) -> Unit = {},
    onAgeChange: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = UMCHackathonTheme.colorScheme.white
        ),
        elevation = CardDefaults.cardElevation(0.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(204.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "성별",
                    style = UMCHackathonTheme.typography.Bold.copy(fontSize = 16.sp),
                    color = UMCHackathonTheme.colorScheme.black
                )
                Text(
                    text = "나이",
                    style = UMCHackathonTheme.typography.Bold.copy(fontSize = 16.sp),
                    color = UMCHackathonTheme.colorScheme.black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = gender == "남성",
                    onClick = { onGenderChange("남성") }
                )
                Text(text = "남성")

                Spacer(modifier = Modifier.width(8.dp))

                RadioButton(
                    selected = gender == "여성",
                    onClick = { onGenderChange("여성") }
                )
                Text(text = "여성")

                Spacer(modifier = Modifier.width(64.dp))

                TextField(
                    value = age,
                    onValueChange = onAgeChange,
                    modifier = Modifier
                        .width(80.dp)
                        .height(48.dp)
                        .background(color= UMCHackathonTheme.colorScheme.white),
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(text = "세")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun showInfoCard() {
    UMCHackathonTheme {
        InfoCard()
    }
}
