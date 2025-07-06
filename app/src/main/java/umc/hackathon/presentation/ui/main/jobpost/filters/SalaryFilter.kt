package umc.hackathon.presentation.ui.main.jobpost.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.core.component.SimpleButton
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalaryFilter(
    onSelect: (IntRange) -> Unit
) {
    var sliderPosition by remember { mutableStateOf(3000f..5000f) }

    val valueRange = 2000f..10000f // 2,000만원 ~ 1억원
    val stepSize = 50f // 50만원 단위
    val steps = ((valueRange.endInclusive - valueRange.start) / stepSize).toInt() - 1

    // 숫자를 통화 형식의 문자열로 변환하는 함수
    fun formatSalary(value: Float): String {
        val amount = value.roundToInt()
        if (amount >= 10000) {
            val eok = amount / 10000
            val man = amount % 10000
            return if (man == 0) {
                "${eok}억원"
            } else {
                "${eok}억 ${NumberFormat.getNumberInstance(Locale.US).format(man)}만원"
            }
        }
        return "${NumberFormat.getNumberInstance(Locale.US).format(amount)}만원"
    }

    Column(Modifier.fillMaxWidth()) {
        // 필터 제목
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp, 12.dp)
        ) {
            Text(
                text = "희망하는 연봉을 선택해주세요",
                style = UMCHackathonTheme.typography.Bold.copy(fontSize = 20.sp)
            )
        }

        // 구분선
        Spacer(
            Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = UMCHackathonTheme.colorScheme.mainGreen200)
        )

        Spacer(Modifier.height(24.dp))

        Column(Modifier.padding(horizontal = 24.dp)) {
            // 선택된 연봉 범위 표시
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formatSalary(sliderPosition.start),
                    style = UMCHackathonTheme.typography.Bold.copy(
                        fontSize = 18.sp,
                        color = UMCHackathonTheme.colorScheme.mainGreen100
                    )
                )
                Text(
                    text = "  ~  ",
                    style = UMCHackathonTheme.typography.SemiBold.copy(
                        fontSize = 18.sp,
                        color = UMCHackathonTheme.colorScheme.mainGreen100
                    )
                )
                Text(
                    text = formatSalary(sliderPosition.endInclusive),
                    style = UMCHackathonTheme.typography.Bold.copy(
                        fontSize = 18.sp,
                        color = UMCHackathonTheme.colorScheme.mainGreen100
                    )
                )
            }

            Spacer(Modifier.height(12.dp))

            // 연봉 선택 슬라이더
            RangeSlider(
                value = sliderPosition,
                onValueChange = { newRange -> sliderPosition = newRange },
                valueRange = valueRange,
                steps = steps,
                onValueChangeFinished = {
                    // 사용자가 슬라이더 조작을 마치면 값을 50만원 단위에 맞게 보정
                    val snappedStart = (sliderPosition.start / stepSize).roundToInt() * stepSize
                    val snappedEnd =
                        (sliderPosition.endInclusive / stepSize).roundToInt() * stepSize
                    sliderPosition = snappedStart..snappedEnd
                },
                colors = SliderDefaults.colors(
                    thumbColor = UMCHackathonTheme.colorScheme.mainGreen100,
                    activeTrackColor = UMCHackathonTheme.colorScheme.mainGreen100,
                    inactiveTrackColor = UMCHackathonTheme.colorScheme.gray200,
                )
            )

            // 슬라이더 하단 레이블
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = formatSalary(valueRange.start),
                    modifier = Modifier.weight(1f),
                    style = UMCHackathonTheme.typography.Regular.copy(
                        fontSize = 13.sp,
                        color = UMCHackathonTheme.colorScheme.gray200
                    ),
                )
                Text(
                    text = formatSalary(valueRange.endInclusive),
                    modifier = Modifier.weight(1f),
                    style = UMCHackathonTheme.typography.Regular.copy(
                        fontSize = 13.sp,
                        color = UMCHackathonTheme.colorScheme.gray200
                    ),
                    textAlign = TextAlign.End
                )
            }


            Spacer(Modifier.height(30.dp))

            // 확인 버튼
            SimpleButton(
                text = "확인",
                enabled = sliderPosition.start < sliderPosition.endInclusive,
            ) {
                val selectedStart = sliderPosition.start.roundToInt()
                val selectedEnd = sliderPosition.endInclusive.roundToInt()
                onSelect(selectedStart..selectedEnd)
            }
        }
    }
}