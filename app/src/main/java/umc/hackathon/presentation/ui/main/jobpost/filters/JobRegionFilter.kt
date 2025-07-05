package umc.hackathon.presentation.ui.main.jobpost.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.core.component.GridSelectView
import umc.hackathon.core.component.HierarchicalSelectView
import umc.hackathon.core.component.SelectItem
import umc.hackathon.core.component.SimpleButton
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Composable
fun JobRegionFilter(onSelect: (List<SelectItem>) -> Unit) {
    val parentItems = listOf(
        SelectItem("1100000000", "서울특별시"),
        SelectItem("2600000000", "부산광역시"),
        SelectItem("2700000000", "대구광역시"),
        SelectItem("2800000000", "인천광역시"),
        SelectItem("2900000000", "광주광역시"),
        SelectItem("3000000000", "대전광역시"),
        SelectItem("3100000000", "울산광역시"),
        SelectItem("4100000000", "경기도"),
        SelectItem("4300000000", "충청북도"),
        SelectItem("4400000000", "충청남도"),
        SelectItem("4500000000", "전라북도"),
        SelectItem("4600000000", "전라남도"),
        SelectItem("4700000000", "경상북도"),
        SelectItem("4800000000", "경상남도"),
        SelectItem("5000000000", "제주특별자치도"),
        SelectItem("5100000000", "강원특별자치도")
    )

    val childItems = mapOf(
        "1100000000" to listOf(
            SelectItem("1111000000", "종로구"), SelectItem("1114000000", "중구"),
            SelectItem("1117000000", "용산구"), SelectItem("1120000000", "성동구"),
            SelectItem("1121500000", "광진구"), SelectItem("1123000000", "동대문구"),
            SelectItem("1126000000", "중랑구"), SelectItem("1129000000", "성북구"),
            SelectItem("1130500000", "강북구"), SelectItem("1132000000", "도봉구"),
            SelectItem("1135000000", "노원구"), SelectItem("1138000000", "은평구"),
            SelectItem("1141000000", "서대문구"), SelectItem("1144000000", "마포구"),
            SelectItem("1147000000", "양천구"), SelectItem("1150000000", "강서구"),
            SelectItem("1153000000", "구로구"), SelectItem("1154500000", "금천구"),
            SelectItem("1156000000", "영등포구"), SelectItem("1159000000", "동작구"),
            SelectItem("1162000000", "관악구"), SelectItem("1165000000", "서초구"),
            SelectItem("1168000000", "강남구"), SelectItem("1171000000", "송파구"),
            SelectItem("1174000000", "강동구")
        ),
        "2600000000" to listOf(
            SelectItem("2611000000", "중구"), SelectItem("2614000000", "서구"),
            SelectItem("2617000000", "동구"), SelectItem("2620000000", "영도구"),
            SelectItem("2623000000", "부산진구"), SelectItem("2626000000", "동래구"),
            SelectItem("2629000000", "남구"), SelectItem("2632000000", "북구"),
            SelectItem("2635000000", "해운대구"), SelectItem("2638000000", "사하구"),
            SelectItem("2641000000", "금정구"), SelectItem("2644000000", "강서구"),
            SelectItem("2647000000", "연제구"), SelectItem("2650000000", "수영구"),
            SelectItem("2653000000", "사상구"), SelectItem("2671000000", "기장군")
        ),
        "2700000000" to listOf(
            SelectItem("2711000000", "중구"), SelectItem("2714000000", "동구"),
            SelectItem("2717000000", "서구"), SelectItem("2720000000", "남구"),
            SelectItem("2723000000", "북구"), SelectItem("2726000000", "수성구"),
            SelectItem("2729000000", "달서구"), SelectItem("2771000000", "달성군"),
            SelectItem("2772000000", "군위군")
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
                text = "희망하는 근무 지역을 골라주세요", // 텍스트 수정
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 20.sp
                )
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

        Spacer(
            Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = UMCHackathonTheme.colorScheme.mainGreen200)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "근무 유형",
            style = UMCHackathonTheme.typography.SemiBold.copy(
                fontSize = 15.sp,
                color = UMCHackathonTheme.colorScheme.gray600
            )
        )

        Spacer(Modifier.height(12.dp))

        val sampleItems = remember {
            listOf(
                SelectItem("home", "재택(비대면)"),
                SelectItem("commute", "대면")
            )
        }

        var selectedItems by remember {
            mutableStateOf<List<SelectItem>>(emptyList())
        }

        UMCHackathonTheme {
            GridSelectView(
                selectItems = sampleItems,
                selectedItems = selectedItems,
                onClick = { clickedItem ->
                    selectedItems = if (selectedItems.any { it.id == clickedItem.id }) {
                        selectedItems.filter { it.id != clickedItem.id }
                    } else {
                        selectedItems + clickedItem
                    }
                }
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "근무 지역",
            style = UMCHackathonTheme.typography.SemiBold.copy(
                fontSize = 15.sp,
                color = UMCHackathonTheme.colorScheme.gray600
            )
        )

        Spacer(Modifier.height(12.dp))

        HierarchicalSelectView(
            parentItems = parentItems,
            selectParentItem = selectedParent,
            childItems = childItems,
            selectedChildItems = selectedChildren,
            onParentItemChange = { newParent ->
                selectedParent = newParent
                selectedChildren = emptyList()
            },
            onChildItemChange = { newChildren ->
                selectedChildren = newChildren
            }
        )

        Spacer(Modifier.height(20.dp))

        SimpleButton(
            text = "확인",
            enabled = selectedParent != null && (selectedChildren.isNotEmpty() || childItems[selectedParent?.id].isNullOrEmpty()),
        ) {
            val selection = if (childItems[selectedParent?.id].isNullOrEmpty()) {
                listOfNotNull(selectedParent)
            } else {
                selectedChildren
            }
            onSelect(selection)
        }
    }
}