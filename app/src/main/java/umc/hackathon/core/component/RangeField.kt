package umc.hackathon.core.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme


@Preview(showBackground = true)
@Composable
fun RangeFieldPreview() {
    UMCHackathonTheme {
        var startValue by remember { mutableStateOf<Int?>(null) }
        var endValue by remember { mutableStateOf<Int?>(100) }

        RangeField(
            startValue = startValue,
            endValue = endValue,
            onStartValueChange = {
                startValue = it
            },
            onEndValueChange = {
                endValue = it
            },
            suffix = "만원"
        )
    }
}

@Composable
fun RangeField(
    startValue: Int?,
    endValue: Int?,
    onStartValueChange: (Int?) -> Unit,
    onEndValueChange: (Int?) -> Unit,
    suffix: String? = null
) {
    val startHasValue = startValue != null
    val endHasValue = endValue != null

    val startBorderColor = if (startHasValue) UMCHackathonTheme.colorScheme.mainGreen300 else UMCHackathonTheme.colorScheme.gray300
    val startContentColor = if (startHasValue) UMCHackathonTheme.colorScheme.mainGreen300 else UMCHackathonTheme.colorScheme.gray300
    val startTextStyle = if (startHasValue) UMCHackathonTheme.typography.Bold else UMCHackathonTheme.typography.Regular

    val endBorderColor = if (endHasValue) UMCHackathonTheme.colorScheme.mainGreen300 else UMCHackathonTheme.colorScheme.gray300
    val endContentColor = if (endHasValue) UMCHackathonTheme.colorScheme.mainGreen300 else UMCHackathonTheme.colorScheme.gray300
    val endTextStyle = if (endHasValue) UMCHackathonTheme.typography.Bold else UMCHackathonTheme.typography.Regular

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BasicTextField(
            value = startValue?.toString() ?: "",
            onValueChange = { text ->
                val filteredText = text.filter { it.isDigit() }
                val newStartValue = filteredText.toIntOrNull()

                if (newStartValue != null && endValue != null && newStartValue > endValue) {
                    onStartValueChange(endValue)
                } else {
                    onStartValueChange(newStartValue)
                }
            },
            modifier = Modifier
                .weight(1f)
                .border(1.dp, startBorderColor, RoundedCornerShape(10.dp)),
            textStyle = startTextStyle.copy(
                fontSize = 15.sp,
                color = startContentColor
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (startValue != null) {
                        innerTextField()
                        suffix?.let {
                            Text(
                                text = it,
                                style = startTextStyle.copy(fontSize = 15.sp, color = startContentColor),
                                modifier = Modifier.padding(start = 2.dp)
                            )
                        }
                    } else {
                        Text(
                            text = "0${suffix ?: ""}",
                            style = UMCHackathonTheme.typography.Regular.copy(
                                fontSize = 15.sp,
                                color = UMCHackathonTheme.colorScheme.gray300
                            )
                        )
                    }
                }
            }
        )

        Text(
            text = "~",
            color = UMCHackathonTheme.colorScheme.mainGreen300,
            fontWeight = FontWeight.Bold
        )

        BasicTextField(
            value = endValue?.toString() ?: "",
            onValueChange = { text ->
                val filteredText = text.filter { it.isDigit() }
                val newEndValue = filteredText.toIntOrNull()

                if (newEndValue != null && startValue != null && newEndValue < startValue) {
                    onEndValueChange(startValue)
                } else {
                    onEndValueChange(newEndValue)
                }
            },
            modifier = Modifier
                .weight(1f)
                .border(1.dp, endBorderColor, RoundedCornerShape(10.dp)),
            textStyle = endTextStyle.copy(
                fontSize = 15.sp,
                color = endContentColor
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (endValue != null) {
                        innerTextField()
                        suffix?.let {
                            Text(
                                text = it,
                                style = endTextStyle.copy(fontSize = 15.sp, color = endContentColor),
                                modifier = Modifier.padding(start = 2.dp)
                            )
                        }
                    } else {
                        Text(
                            text = "0${suffix ?: ""}",
                            style = UMCHackathonTheme.typography.Regular.copy(
                                fontSize = 15.sp,
                                color = UMCHackathonTheme.colorScheme.gray300
                            )
                        )
                    }
                }
            }
        )
    }
}