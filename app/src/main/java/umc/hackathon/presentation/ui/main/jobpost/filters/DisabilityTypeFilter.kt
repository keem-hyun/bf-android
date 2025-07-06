package umc.hackathon.presentation.ui.main.jobpost.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.core.component.HierarchicalSelectView
import umc.hackathon.core.component.SelectItem
import umc.hackathon.core.component.SimpleButton
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Composable
fun DisabilityTypeFilter(onSelect: (type: SelectItem, severity: SelectItem) -> Unit) {
    val parentItems = listOf(
        SelectItem("dis_p1", "지체장애"),
        SelectItem("dis_p2", "뇌병변장애"),
        SelectItem("dis_p3", "시각장애"),
        SelectItem("dis_p4", "청각장애"),
        SelectItem("dis_p5", "언어장애"),
        SelectItem("dis_p6", "안면장애"),
        SelectItem("dis_p7", "신장장애"),
        SelectItem("dis_p8", "심장장애"),
        SelectItem("dis_p9", "간장애"),
        SelectItem("dis_p10", "호흡기장애"),
        SelectItem("dis_p11", "장루·요루장애"),
        SelectItem("dis_p12", "뇌전증장애"),
        SelectItem("dis_p13", "정신장애"),
        SelectItem("dis_p14", "발달장애(자폐성)"),
        SelectItem("dis_p15", "발달장애(지적)")
    )

    // Child 아이템: 중증/경증 (모든 장애유형에 공통으로 사용)
    val severityItems = listOf(
        SelectItem("c_severe", "중증"),
        SelectItem("c_mild", "경증")
    )

    // Child 아이템 맵: 모든 Parent 아이템 ID에 대해 동일한 severityItems를 매핑합니다.
    val childItems = parentItems.associate { it.id to severityItems }

    // 선택된 Parent(장애유형)와 Child(등급)를 저장하는 state
    var selectedParent by remember { mutableStateOf<SelectItem?>(null) }
    var selectedChild by remember { mutableStateOf<SelectItem?>(null) }

    Column(Modifier.fillMaxWidth()) {
        // 필터 제목
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp, 12.dp)
        ) {
            Text(
                text = "보유중인 장애유형을 선택해주세요",
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 20.sp
                )
            )

            Spacer(Modifier.height(2.dp))

            // 복수선택 가능 안내
            Text(
                text = "* 복수선택 가능",
                style = UMCHackathonTheme.typography.Regular.copy(
                    fontSize = 15.sp,
                    color = UMCHackathonTheme.colorScheme.mainGreen300
                )
            )
        }

        Spacer(
            Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = UMCHackathonTheme.colorScheme.mainGreen200)
        )

        Spacer(Modifier.height(8.dp))

        Column(Modifier.padding(24.dp, 0.dp)) {
            // 장애유형 및 등급 선택 뷰
            HierarchicalSelectView(
                parentItems = parentItems,
                selectParentItem = selectedParent,
                childItems = childItems,
                // 등급은 '중증', '경증' 중 하나만 선택 가능하므로 단일 선택으로 처리합니다.
                selectedChildItems = selectedChild?.let { listOf(it) } ?: emptyList(),
                onParentItemChange = { newParent ->
                    selectedParent = newParent
                    // 장애유형이 바뀌면 등급 선택은 초기화합니다.
                    selectedChild = null
                },
                onChildItemChange = { newChildren ->
                    // 등급 선택 시, 첫 번째 아이템을 가져와 단일 선택을 구현합니다.
                    selectedChild = newChildren.firstOrNull()
                }
            )

            Spacer(Modifier.height(20.dp))

            // 확인 버튼
            SimpleButton(
                text = "확인",
                // 장애유형과 등급이 모두 선택되었을 때 활성화됩니다.
                enabled = selectedParent != null && selectedChild != null
            ) {
                // onSelect 콜백에 선택된 유형과 등급을 전달합니다.
                // 이 필터를 사용하는 상위 화면에서 이 값들을 리스트에 추가하여 복수선택을 구현할 수 있습니다.
                onSelect(selectedParent!!, selectedChild!!)
            }
        }
    }
}