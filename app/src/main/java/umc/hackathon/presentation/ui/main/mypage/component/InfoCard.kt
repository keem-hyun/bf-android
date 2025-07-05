package umc.hackathon.presentation.ui.main.mypage.component

import android.graphics.Paint.Align
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.DefaultTab.AlbumsTab.value
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
    val isSelected = gender.isNotBlank() || age.isNotBlank()
    val borderColor = if (isSelected) UMCHackathonTheme.colorScheme.mainGreen300 else UMCHackathonTheme.colorScheme.gray200
    val selectedColor = UMCHackathonTheme.colorScheme.mainGreen300

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = UMCHackathonTheme.colorScheme.white),
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
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
                    onClick = { onGenderChange("남성") },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = selectedColor
                    )
                )
                Text(text = "남성", color = if (gender == "남성") selectedColor else UMCHackathonTheme.colorScheme.gray500)

                Spacer(modifier = Modifier.width(8.dp))

                RadioButton(
                    selected = gender == "여성",
                    onClick = { onGenderChange("여성") },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = selectedColor
                    )
                )
                Text(text = "여성", color = if (gender == "여성") selectedColor else UMCHackathonTheme.colorScheme.gray500)

                Spacer(modifier = Modifier.width(40.dp))

                BasicTextField(
                    value = age,
                    onValueChange = { newText ->
                        if (newText.all { char -> char.isDigit() }) {
                            onAgeChange(newText)
                        }
                    },
                    textStyle = UMCHackathonTheme.typography.Regular.copy(
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        color = if (age.isNotBlank()) selectedColor else UMCHackathonTheme.colorScheme.gray500
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .width(80.dp)
                                .height(48.dp)
                                .border(1.dp, UMCHackathonTheme.colorScheme.gray400, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "세",
                    color = if (age.isNotBlank()) selectedColor else UMCHackathonTheme.colorScheme.gray500
                )
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
