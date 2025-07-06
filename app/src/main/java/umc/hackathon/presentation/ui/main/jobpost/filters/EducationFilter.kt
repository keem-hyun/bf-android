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
fun EducationFilter(onSelect: (education: SelectItem, status: SelectItem) -> Unit) {
    val parentItems = listOf(
        SelectItem("p_grad", "대학원"),
        SelectItem("p_uni4", "대학(4년)"),
        SelectItem("p_uni23", "대학(2,3년)"),
        SelectItem("p_high", "고등학교"),
        SelectItem("p_mid", "중학교"),
        SelectItem("p_elem", "초등학교")
    )
    val childItems = mapOf(
        "p_grad" to listOf(
            SelectItem("c_grad", "졸업"),
            SelectItem("c_expect", "졸업예정"),
            SelectItem("c_attend", "재학중"),
            SelectItem("c_leave", "휴학"),
            SelectItem("c_complete", "수료"),
            SelectItem("c_drop", "중퇴")
        ),
        "p_uni4" to listOf(
            SelectItem("c_grad", "졸업"),
            SelectItem("c_expect", "졸업예정"),
            SelectItem("c_attend", "재학중"),
            SelectItem("c_leave", "휴학"),
            SelectItem("c_complete", "수료"),
            SelectItem("c_drop", "중퇴")
        ),
        "p_uni23" to listOf(
            SelectItem("c_grad", "졸업"),
            SelectItem("c_expect", "졸업예정"),
            SelectItem("c_attend", "재학중"),
            SelectItem("c_leave", "휴학"),
            SelectItem("c_complete", "수료"),
            SelectItem("c_drop", "중퇴")
        ),
        "p_high" to listOf(
            SelectItem("c_grad", "졸업"),
            SelectItem("c_expect", "졸업예정"),
            SelectItem("c_attend", "재학중"),
            SelectItem("c_drop", "중퇴")
        ),
        "p_mid" to listOf(
            SelectItem("c_grad", "졸업"),
            SelectItem("c_attend", "재학중")
        ),
        "p_elem" to listOf(
            SelectItem("c_grad", "졸업"),
            SelectItem("c_attend", "재학중")
        )
    )
    var selectedParent: SelectItem? by remember { mutableStateOf<SelectItem?>(null) }
    var selectedChild by remember { mutableStateOf<SelectItem?>(null) }

    Column(Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp, 12.dp)
        ) {
            Text(
                text = "최종 학력을 입력해주세요",
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 20.sp
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
            HierarchicalSelectView(
                parentItems = parentItems,
                selectParentItem = selectedParent,
                childItems = childItems,
                selectedChildItems = selectedChild?.let { listOf(it) } ?: emptyList(),
                onParentItemChange = { newParent ->
                    selectedParent = newParent
                    selectedChild = null
                },
                onChildItemChange = { newChildren ->
                    selectedChild = newChildren.firstOrNull()
                }
            )

            Spacer(Modifier.height(20.dp))

            SimpleButton(
                text = "확인",
                enabled = selectedParent != null && selectedChild != null
            ) {
                onSelect(selectedParent!!, selectedChild!!)
            }
        }
    }
}