package umc.hackathon.presentation.ui.main.jobpost

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import umc.hackathon.R
import umc.hackathon.core.component.ActionBar
import umc.hackathon.core.component.DropDownStyleButton
import umc.hackathon.core.component.IconCircleButton
import umc.hackathon.core.component.JobPostListItem
import umc.hackathon.core.component.SearchBar
import umc.hackathon.core.component.Switch
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import umc.hackathon.presentation.ui.main.search.SearchScreen

@Preview
@Composable
fun JobPostListScreenPreview() {
    UMCHackathonTheme {
        JobPostListScreen()
    }
}

@Composable
fun JobPostListRoute(
    paddingValues: PaddingValues,
    navController: NavController
) {
    JobPostListScreen()
}

@Composable
fun JobPostListScreen() {
    var searchText by remember {
        mutableStateOf("")
    }

    var personalized by remember {
        mutableStateOf(false)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(UMCHackathonTheme.colorScheme.gray100)
    ) {
        Card(
            Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ), shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 24.dp,
                bottomStart = 24.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = UMCHackathonTheme.colorScheme.white
            )
        ) {
            Column {
                ActionBar()

                SearchBar(
                    searchText = "",
                    onSearchTextChange = {},
                    onSearchClick = {},
                    placeholderText = "무슨 일 하고 싶으세요? 검색해볼까요?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp)
                )

                Spacer(Modifier.height(16.dp))


                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            items(5) {
                                DropDownStyleButton(
                                    text = "직종",
                                    highlited = false
                                ) {

                                }
                            }
                        }
                    }

                    Spacer(Modifier.width(8.dp))

                    IconCircleButton(painterResource(R.drawable.ic_filter)) { }
                }

                Spacer(Modifier.height(16.dp))
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Text("프로필 기반 추천")

            Spacer(Modifier.width(10.dp))

            Switch(personalized, {
                personalized = it
            })
        }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) { index ->
                JobPostListItem(
                    "주식회사 레진엔터테인먼트", 19, "[장애인 전형] 사내카페 지원", "외식·음료 > 커피전문점",
                    "서울특별시 강남구", "주 5일", "정규직", "월급 110만원"
                )
            }
        }
    }
}