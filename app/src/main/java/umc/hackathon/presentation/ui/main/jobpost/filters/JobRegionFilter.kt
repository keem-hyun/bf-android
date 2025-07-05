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
        ),
        "2800000000" to listOf(
            SelectItem("2811000000", "중구"), SelectItem("2814000000", "동구"),
            SelectItem("2817700000", "미추홀구"), SelectItem("2818500000", "연수구"),
            SelectItem("2820000000", "남동구"), SelectItem("2823700000", "부평구"),
            SelectItem("2824500000", "계양구"), SelectItem("2826000000", "서구"),
            SelectItem("2871000000", "강화군"), SelectItem("2872000000", "옹진군")
        ),
        "2900000000" to listOf(
            SelectItem("2911000000", "동구"), SelectItem("2914000000", "서구"),
            SelectItem("2915500000", "남구"), SelectItem("2917000000", "북구"),
            SelectItem("2920000000", "광산구")
        ),
        "3000000000" to listOf(
            SelectItem("3011000000", "동구"), SelectItem("3014000000", "중구"),
            SelectItem("3017000000", "서구"), SelectItem("3020000000", "유성구"),
            SelectItem("3023000000", "대덕구")
        ),
        "3100000000" to listOf(
            SelectItem("3111000000", "중구"), SelectItem("3114000000", "남구"),
            SelectItem("3117000000", "동구"), SelectItem("3120000000", "북구"),
            SelectItem("3171000000", "울주군")
        ),
        "4100000000" to listOf(
            SelectItem("4111000000", "수원시"), SelectItem("4111100000", "수원시 장안구"),
            SelectItem("4111300000", "수원시 권선구"), SelectItem("4111500000", "수원시 팔달구"),
            SelectItem("4111700000", "수원시 영통구"), SelectItem("4113000000", "성남시"),
            SelectItem("4113100000", "성남시 수정구"), SelectItem("4113300000", "성남시 중원구"),
            SelectItem("4113500000", "성남시 분당구"), SelectItem("4115000000", "의정부시"),
            SelectItem("4117000000", "안양시"), SelectItem("4117100000", "안양시 만안구"),
            SelectItem("4117300000", "안양시 동안구"), SelectItem("4119000000", "부천시"),
            SelectItem("4121000000", "광명시"), SelectItem("4122000000", "평택시"),
            SelectItem("4125000000", "동두천시"), SelectItem("4127000000", "안산시"),
            SelectItem("4127100000", "안산시 상록구"), SelectItem("4127300000", "안산시 단원구"),
            SelectItem("4128000000", "고양시"), SelectItem("4128100000", "고양시 덕양구"),
            SelectItem("4128500000", "고양시 일산동구"), SelectItem("4128700000", "고양시 일산서구"),
            SelectItem("4129000000", "과천시"), SelectItem("4131000000", "구리시"),
            SelectItem("4136000000", "남양주시"), SelectItem("4137000000", "오산시"),
            SelectItem("4139000000", "시흥시"), SelectItem("4141000000", "군포시"),
            SelectItem("4143000000", "의왕시"), SelectItem("4145000000", "하남시"),
            SelectItem("4146000000", "용인시"), SelectItem("4146100000", "용인시 처인구"),
            SelectItem("4146300000", "용인시 기흥구"), SelectItem("4146500000", "용인시 수지구"),
            SelectItem("4148000000", "파주시"), SelectItem("4150000000", "이천시"),
            SelectItem("4155000000", "안성시"), SelectItem("4157000000", "김포시"),
            SelectItem("4159000000", "화성시"), SelectItem("4161000000", "광주시"),
            SelectItem("4163000000", "양주시"), SelectItem("4165000000", "포천시"),
            SelectItem("4167000000", "여주시"), SelectItem("4180000000", "연천군"),
            SelectItem("4182000000", "가평군"), SelectItem("4183000000", "양평군")
        ),
        "4300000000" to listOf(
            SelectItem("4311000000", "청주시"), SelectItem("4311100000", "청주시 상당구"),
            SelectItem("4311200000", "청주시 서원구"), SelectItem("4311300000", "청주시 흥덕구"),
            SelectItem("4311400000", "청주시 청원구"), SelectItem("4313000000", "충주시"),
            SelectItem("4315000000", "제천시"), SelectItem("4372000000", "보은군"),
            SelectItem("4373000000", "옥천군"), SelectItem("4374000000", "영동군"),
            SelectItem("4374500000", "증평군"), SelectItem("4375000000", "진천군"),
            SelectItem("4376000000", "괴산군"), SelectItem("4377000000", "음성군"),
            SelectItem("4380000000", "단양군")
        ),
        "4400000000" to listOf(
            SelectItem("4413000000", "천안시"), SelectItem("4413100000", "천안시 동남구"),
            SelectItem("4413300000", "천안시 서북구"), SelectItem("4415000000", "공주시"),
            SelectItem("4418000000", "보령시"), SelectItem("4420000000", "아산시"),
            SelectItem("4421000000", "서산시"), SelectItem("4423000000", "논산시"),
            SelectItem("4425000000", "계룡시"), SelectItem("4427000000", "당진시"),
            SelectItem("4471000000", "금산군"), SelectItem("4476000000", "부여군"),
            SelectItem("4477000000", "서천군"), SelectItem("4479000000", "청양군"),
            SelectItem("4480000000", "홍성군"), SelectItem("4481000000", "예산군"),
            SelectItem("4482500000", "태안군")
        ),
        "4500000000" to listOf(
            SelectItem("4511000000", "전주시"), SelectItem("4511100000", "전주시 완산구"),
            SelectItem("4511300000", "전주시 덕진구"), SelectItem("4513000000", "군산시"),
            SelectItem("4514000000", "익산시"), SelectItem("4518000000", "정읍시"),
            SelectItem("4519000000", "남원시"), SelectItem("4521000000", "김제시"),
            SelectItem("4571000000", "완주군"), SelectItem("4572000000", "진안군"),
            SelectItem("4573000000", "무주군"), SelectItem("4574000000", "장수군"),
            SelectItem("4575000000", "임실군"), SelectItem("4577000000", "순창군"),
            SelectItem("4579000000", "고창군"), SelectItem("4580000000", "부안군")
        ),
        "4600000000" to listOf(
            SelectItem("4611000000", "목포시"), SelectItem("4613000000", "여수시"),
            SelectItem("4615000000", "순천시"), SelectItem("4617000000", "나주시"),
            SelectItem("4623000000", "광양시"), SelectItem("4671000000", "담양군"),
            SelectItem("4672000000", "곡성군"), SelectItem("4673000000", "구례군"),
            SelectItem("4677000000", "고흥군"), SelectItem("4678000000", "보성군"),
            SelectItem("4679000000", "화순군"), SelectItem("4680000000", "장흥군"),
            SelectItem("4681000000", "강진군"), SelectItem("4682000000", "해남군"),
            SelectItem("4683000000", "영암군"), SelectItem("4684000000", "무안군"),
            SelectItem("4686000000", "함평군"), SelectItem("4687000000", "영광군"),
            SelectItem("4688000000", "장성군"), SelectItem("4689000000", "완도군"),
            SelectItem("4690000000", "진도군"), SelectItem("4691000000", "신안군")
        ),
        "4700000000" to listOf(
            SelectItem("4711000000", "포항시"), SelectItem("4711100000", "포항시 남구"),
            SelectItem("4711300000", "포항시 북구"), SelectItem("4713000000", "경주시"),
            SelectItem("4715000000", "김천시"), SelectItem("4717000000", "안동시"),
            SelectItem("4719000000", "구미시"), SelectItem("4721000000", "영주시"),
            SelectItem("4723000000", "영천시"), SelectItem("4725000000", "상주시"),
            SelectItem("4728000000", "문경시"), SelectItem("4729000000", "경산시"),
            SelectItem("4772000000", "군위군"), SelectItem("4773000000", "의성군"),
            SelectItem("4775000000", "청송군"), SelectItem("4776000000", "영양군"),
            SelectItem("4777000000", "영덕군"), SelectItem("4782000000", "청도군"),
            SelectItem("4783000000", "고령군"), SelectItem("4784000000", "성주군"),
            SelectItem("4785000000", "칠곡군"), SelectItem("4790000000", "예천군"),
            SelectItem("4792000000", "봉화군"), SelectItem("4793000000", "울진군"),
            SelectItem("4794000000", "울릉군")
        ),
        "4800000000" to listOf(
            SelectItem("4812000000", "창원시"), SelectItem("4812100000", "창원시 의창구"),
            SelectItem("4812300000", "창원시 성산구"), SelectItem("4812500000", "창원시 마산합포구"),
            SelectItem("4812700000", "창원시 마산회원구"), SelectItem("4812900000", "창원시 진해구"),
            SelectItem("4817000000", "진주시"), SelectItem("4822000000", "통영시"),
            SelectItem("4824000000", "사천시"), SelectItem("4825000000", "김해시"),
            SelectItem("4827000000", "밀양시"), SelectItem("4831000000", "거제시"),
            SelectItem("4833000000", "양산시"), SelectItem("4872000000", "의령군"),
            SelectItem("4873000000", "함안군"), SelectItem("4874000000", "창녕군"),
            SelectItem("4882000000", "고성군"), SelectItem("4884000000", "남해군"),
            SelectItem("4885000000", "하동군"), SelectItem("4886000000", "산청군"),
            SelectItem("4887000000", "함양군"), SelectItem("4888000000", "거창군"),
            SelectItem("4889000000", "합천군")
        ),
        "5000000000" to listOf(
            SelectItem("5011000000", "제주시"), SelectItem("5013000000", "서귀포시")
        ),
        "5100000000" to listOf(
            SelectItem("5111000000", "춘천시"), SelectItem("5113000000", "원주시"),
            SelectItem("5115000000", "강릉시"), SelectItem("5117000000", "동해시"),
            SelectItem("5119000000", "태백시"), SelectItem("5121000000", "속초시"),
            SelectItem("5123000000", "삼척시"), SelectItem("5172000000", "홍천군"),
            SelectItem("5173000000", "횡성군"), SelectItem("5175000000", "영월군"),
            SelectItem("5176000000", "평창군"), SelectItem("5177000000", "정선군"),
            SelectItem("5178000000", "철원군"), SelectItem("5179000000", "화천군"),
            SelectItem("5180000000", "양구군"), SelectItem("5181000000", "인제군"),
            SelectItem("5182000000", "고성군"), SelectItem("5183000000", "양양군")
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

        Column(Modifier.padding(24.dp, 0.dp)) {
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
}