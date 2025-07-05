package umc.hackathon.presentation.ui.main.jobpost.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.core.component.RangeField
import umc.hackathon.core.component.SelectItem
import umc.hackathon.core.component.SimpleButton
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Composable
fun JobWorkHoursFilter(onSelect: (minHour: Int, maxHour: Int) -> Unit) {
    var startHour by remember { mutableStateOf<Int?>(null) }
    var endHour by remember { mutableStateOf<Int?>(null) }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(24.dp, 12.dp)
    ) {
        Text(
            text = "희망하는 근무시간을 지정해주세요", style = UMCHackathonTheme.typography.Bold.copy(
                fontSize = 20.sp
            )
        )

        Spacer(Modifier.height(2.dp))
    }

    Spacer(
        Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = UMCHackathonTheme.colorScheme.mainGreen200)
    )

    Spacer(Modifier.height(8.dp))

    RangeField(
        startValue = startHour,
        endValue = endHour,
        onStartValueChange = { minHour ->
            startHour = minHour ?: 0
        },
        onEndValueChange = { maxHour ->
            endHour = maxHour ?: 24
        },
        suffix = "시간"
    )

    Spacer(Modifier.height(16.dp))

    SimpleButton(
        text = "확인",
        modifier = Modifier.fillMaxWidth(),
        enabled = startHour != null && endHour != null,
        onClick = {
            onSelect(startHour ?: 0, endHour ?: 24)
        }
    )
}