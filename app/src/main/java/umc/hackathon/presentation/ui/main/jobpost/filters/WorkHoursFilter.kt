package umc.hackathon.presentation.ui.main.jobpost.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.core.component.SimpleButton
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkHoursFilter(
    onSelect: (IntRange) -> Unit
) {
    // RangeSlider의 현재 위치를 저장하는 상태 (기본값: 오전 9시 ~ 오후 6시)
    var sliderPosition by remember { mutableStateOf(9f..18f) }

    // 시간을 사용자 친화적인 텍스트로 변환하는 함수
    fun formatHour(hourFloat: Float): String {
        val hour = hourFloat.roundToInt()
        return when (hour) {
            0 -> "오전 0시"
            24 -> "오후 12시"
            in 1..11 -> "오전 ${hour}시"
            12 -> "오후 12시"
            else -> "오후 ${hour - 12}시"
        }
    }

    Column(Modifier.fillMaxWidth()) {
        // 필터 제목
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp, 12.dp)
        ) {
            Text(
                text = "희망하는 근무시간을 선택해주세요",
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
            // 선택된 시간 범위 표시
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formatHour(sliderPosition.start),
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
                    text = formatHour(sliderPosition.endInclusive),
                    style = UMCHackathonTheme.typography.Bold.copy(
                        fontSize = 18.sp,
                        color = UMCHackathonTheme.colorScheme.mainGreen100
                    )
                )
            }

            Spacer(Modifier.height(12.dp))

            // 근무시간 선택 슬라이더
            RangeSlider(
                value = sliderPosition,
                onValueChange = { newRange ->
                    // 시작과 끝 값을 2시간 단위로 조정
                    val newStart = (newRange.start / 2).roundToInt() * 2f
                    val newEnd = (newRange.endInclusive / 2).roundToInt() * 2f

                    // 시작 값이 끝 값보다 크거나 같지 않도록 보장
                    if (newStart < newEnd) {
                        sliderPosition = newStart..newEnd
                    } else {
                        // 만약 값이 겹치면 한 스텝 차이를 유지
                        sliderPosition = newStart..(newStart + 2f).coerceAtMost(24f)
                    }
                },
                valueRange = 0f..24f, // 0시부터 24시까지
                steps = 11, // (24시 / 2시간) - 1 = 11개의 중간 단계
                colors = SliderDefaults.colors(
                    thumbColor = UMCHackathonTheme.colorScheme.mainGreen100,
                    activeTrackColor = UMCHackathonTheme.colorScheme.mainGreen100,
                    inactiveTrackColor = UMCHackathonTheme.colorScheme.gray200,
                )
            )

            // 슬라이더 하단 시간 레이블
            Row(modifier = Modifier.fillMaxWidth()) {
                val labels = listOf("0시", "6시", "12시", "18시", "24시")
                labels.forEach { label ->
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text(
                            text = label,
                            style = UMCHackathonTheme.typography.Regular.copy(
                                fontSize = 13.sp,
                                color = UMCHackathonTheme.colorScheme.mainGreen100
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
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