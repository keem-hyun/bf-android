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
import umc.hackathon.core.component.HierarchicalSelectView
import umc.hackathon.core.component.SelectItem
import umc.hackathon.core.component.SimpleButton
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Composable
fun JobTypeFilter(onSelect: (List<SelectItem>) -> Unit) {
    val parentItems = listOf(
        SelectItem("p1", "기획·전략"),
        SelectItem("p2", "법무·사무·총무"),
        SelectItem("p3", "인사·HR"),
        SelectItem("p4", "회계·세무"),
        SelectItem("p5", "마케팅·광고·MD"),
        SelectItem("p6", "AI·개발·데이터"),
        SelectItem("p7", "디자인"),
        SelectItem("p8", "물류·무역")
    )

    val childItems = mapOf(
        "p1" to listOf(
            SelectItem("c1_1", "경영·비즈니스·기획"),
            SelectItem("c1_2", "웹기획"),
            SelectItem("c1_3", "마케팅 기획"),
            SelectItem("c1_4", "PL·PM·PO"),
            SelectItem("c1_5", "컨설턴트"),
            SelectItem("c1_6", "CEO·COO·CTO"),
            SelectItem("c1_7", "AI기획자"),
            SelectItem("c1_8", "AI사업전략")
        ),
        "p2" to listOf(
            SelectItem("c2_1", "법무"),
            SelectItem("c2_2", "사무보조"),
            SelectItem("c2_3", "총무"),
            SelectItem("c2_4", "비서")
        ),
        "p3" to listOf(
            SelectItem("c3_1", "인사관리"),
            SelectItem("c3_2", "채용"),
            SelectItem("c3_3", "급여·보상"),
            SelectItem("c3_4", "노무"),
            SelectItem("c3_5", "조직문화")
        ),
        "p4" to listOf(
            SelectItem("c4_1", "재무회계"),
            SelectItem("c4_2", "세무"),
            SelectItem("c4_3", "관리회계"),
            SelectItem("c4_4", "자금"),
            SelectItem("c4_5", "IR")
        ),
        "p5" to listOf(
            SelectItem("c5_1", "브랜드 마케팅"),
            SelectItem("c5_2", "퍼포먼스 마케팅"),
            SelectItem("c5_3", "콘텐츠 마케터"),
            SelectItem("c5_4", "MD"),
            SelectItem("c5_5", "광고 AE")
        ),
        "p6" to listOf(
            SelectItem("c6_1", "서버 개발자"),
            SelectItem("c6_2", "프론트엔드 개발자"),
            SelectItem("c6_3", "데이터 사이언티스트"),
            SelectItem("c6_4", "AI/ML 엔지니어"),
            SelectItem("c6_5", "DBA"),
            SelectItem("c6_6", "QA 엔지니어")
        ),
        "p7" to listOf(
            SelectItem("c7_1", "UX/UI 디자이너"),
            SelectItem("c7_2", "그래픽 디자이너"),
            SelectItem("c7_3", "프로덕트 디자이너"),
            SelectItem("c7_4", "영상 디자이너")
        ),
        "p8" to listOf(
            SelectItem("c8_1", "해외영업"),
            SelectItem("c8_2", "무역사무"),
            SelectItem("c8_3", "SCM 전문가"),
            SelectItem("c8_4", "물류 관리"),
            SelectItem("c8_5", "구매")
        )
    )

    var selectedParent by remember { mutableStateOf<SelectItem?>(parentItems.first()) }
    var selectedChildren by remember { mutableStateOf<List<SelectItem>>(emptyList()) }

    Column(Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp, 12.dp)
        ) {
            Text(
                text = "희망하는 직종을 골라주세요", style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 20.sp
                )
            )

            Spacer(Modifier.height(2.dp))

            Text(
                text = "* 복수선택 가능", style = UMCHackathonTheme.typography.Regular.copy(
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

        HierarchicalSelectView(
            parentItems = parentItems,
            selectParentItem = selectedParent,
            childItems = childItems,
            selectedChildItems = selectedChildren,
            onParentItemChange = { newParent ->
                selectedParent = newParent
            },
            onChildItemChange = { newChildren ->
                selectedChildren = newChildren
            }
        )

        Spacer(Modifier.height(20.dp))

        SimpleButton(
            text = "확인",
            enabled = selectedParent != null && selectedChildren.isNotEmpty()
        ) {
            onSelect(selectedChildren)
        }
    }
}