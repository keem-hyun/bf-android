package umc.hackathon.presentation.ui.main.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MypageScreen(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    val selectedIndex = remember { mutableStateOf(0) }
    val contents = listOf("1", "2").toPersistentList()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
//        Text("Mypage 화면입니다")
//        Button(onClick = { navController.navigate(MainTabRoute.Search.route) }) {
//            Text("검색으로 이동")
//        }
        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Text(
                text = "000 님"

            )
            Spacer(modifier = Modifier.weight(1F))


        }

        Spacer(modifier = Modifier.height(24.dp))

        TabRow(
            selectedTabIndex = 0,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.LightGray,
            contentColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(
                        tabPositions[selectedIndex.value]
                    ),
                    color = Color.DarkGray
                )

            }
        ) {
            contents.forEachIndexed { index, content ->
                Tab(
                    selected = selectedIndex.value == index,
                    onClick = { selectedIndex.value = index },
                    modifier = Modifier.padding(8.dp)

                ) {
                    Text(text = "content ${content}")
                }


            }

        }
        LazyColumn(
            modifier = Modifier
                .background(color =Color.Gray)
                .fillMaxWidth()
                .fillMaxHeight()
        ){

        }
    }
}

@Preview(showBackground = true)
@Composable
fun showMypage(){
    MypageScreen(
        paddingValues = PaddingValues(),
        navController = NavController(LocalContext.current)
    )
}