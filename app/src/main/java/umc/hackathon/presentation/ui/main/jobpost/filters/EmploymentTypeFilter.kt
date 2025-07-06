package umc.hackathon.presentation.ui.main.jobpost.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.core.component.SelectItem
import umc.hackathon.core.component.SimpleButton
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Composable
fun EmploymentTypeFilter(onSelect: (List<SelectItem>) -> Unit) {
    // 고용형태 목록 정의
    val employmentItems = listOf(
        SelectItem("FULL_TIME", "정규직"),
        SelectItem("CONTRACT", "계약직"),
        SelectItem("INTERN", "인턴"),
        SelectItem("FREELANCER", "프리랜서"),
        SelectItem("PART_TIME", "아르바이트"),
        SelectItem("MILITARY_ALTERNATIVE", "병역특례"),
        SelectItem("DAILY_WORKER", "일용직")
    )

    // 선택된 아이템들을 관리하는 상태
    var selectedItems by remember { mutableStateOf<List<SelectItem>>(emptyList()) }

    Column(Modifier.fillMaxWidth()) {
        // 필터 제목 부분
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp, 12.dp)
        ) {
            Text(
                text = "희망하는 고용형태를 골라주세요",
                style = UMCHackathonTheme.typography.Bold.copy(fontSize = 20.sp)
            )

            Spacer(Modifier.height(2.dp))

            Text(
                text = "* 복수선택 가능",
                style = UMCHackathonTheme.typography.Regular.copy(
                    fontSize = 15.sp,
                    color = UMCHackathonTheme.colorScheme.mainGreen300
                )
            )
        }

        // 구분선
        Spacer(
            Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = UMCHackathonTheme.colorScheme.mainGreen200)
        )

        Spacer(Modifier.height(16.dp))

        // 고용형태 선택 뷰
        Column(Modifier.padding(horizontal = 24.dp)) {
            // 아이템들이 화면 너비를 넘어가면 자동으로 줄바꿈되는 FlowRow 사용
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                employmentItems.forEach { item ->
                    val isSelected = selectedItems.contains(item)
                    SelectableChip(
                        text = item.text,
                        isSelected = isSelected,
                        onClick = {
                            // 선택된 상태에 따라 리스트에 추가하거나 제거
                            selectedItems = if (isSelected) {
                                selectedItems - item
                            } else {
                                selectedItems + item
                            }
                        }
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // 확인 버튼
            SimpleButton(
                text = "확인",
                enabled = selectedItems.isNotEmpty() // 하나 이상 선택해야 활성화
            ) {
                onSelect(selectedItems)
            }
        }
    }
}

/**
 * 선택 가능한 칩(Chip) 형태의 UI 컴포저블
 */
@Composable
private fun SelectableChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isSelected) UMCHackathonTheme.colorScheme.mainGreen100 else UMCHackathonTheme.colorScheme.white
    val textColor =
        if (isSelected) UMCHackathonTheme.colorScheme.white else UMCHackathonTheme.colorScheme.mainGreen200
    val borderModifier = if (isSelected) Modifier else Modifier.border(
        width = 1.dp,
        color = UMCHackathonTheme.colorScheme.mainGreen200,
        shape = RoundedCornerShape(12.dp)
    )

    Text(
        text = text,
        color = textColor,
        style = UMCHackathonTheme.typography.Regular.copy(fontSize = 15.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .then(borderModifier)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    )
}