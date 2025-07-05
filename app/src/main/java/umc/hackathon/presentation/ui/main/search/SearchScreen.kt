package umc.hackathon.presentation.ui.main.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme


@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val colors = UMCHackathonTheme.colorScheme
    val searchText = viewModel.searchText
    val searchHistory = viewModel.searchHistory
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.white)
            .padding(paddingValues)
    ) {
        // 상단 검색바와 뒤로가기 버튼
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 뒤로가기 버튼
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.width(10.dp).height(17.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "뒤로가기",
                    tint = colors.gray500
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            
            // 검색바
            OutlinedTextField(
                value = searchText,
                onValueChange = { viewModel.updateSearchText(it) },
                modifier = Modifier
                    .weight(1f)
                    .height(55.dp)
                    .background(
                        color = colors.white,
                        shape = RoundedCornerShape(18.dp)
                    ),
                placeholder = {
                    Text(
                        "무슨 일 하고 싶으세요? 검색해볼까요?",
                        style = UMCHackathonTheme.typography.Regular.copy(
                            fontSize = 15.sp,
                            color = colors.gray400
                        )
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (searchText.trim().isNotEmpty()) {
                                viewModel.addSearchHistory(searchText.trim())
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "검색",
                            tint = colors.mainGreen300,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.mainGreen200,
                    unfocusedBorderColor = colors.mainGreen200,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                singleLine = true
            )
        }
        
        // 최근 검색 섹션
        if (searchHistory.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "최근 검색",
                    style = UMCHackathonTheme.typography.Regular.copy(
                        fontSize = 16.sp,
                        color = colors.gray700
                    )
                )
                
                Text(
                    text = "수정",
                    style = UMCHackathonTheme.typography.Regular.copy(
                        fontSize = 15.sp,
                        color = colors.gray400
                    ),
                    modifier = Modifier.clickable { viewModel.clearSearchHistory() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            // 검색 태그들
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(searchHistory) { searchHistoryItem ->
                    SearchTagChip(
                        text = searchHistoryItem.query,
                        onRemoveClick = { 
                            viewModel.removeSearchHistory(searchHistoryItem.query)
                        },
                        onChipClick = {
                            viewModel.selectSearchHistory(searchHistoryItem.query)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchTagChip(
    text: String,
    onRemoveClick: () -> Unit,
    onChipClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = UMCHackathonTheme.colorScheme
    
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(colors.gray100)
            .clickable { onChipClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = text,
                style = UMCHackathonTheme.typography.Regular.copy(
                    fontSize = 14.sp,
                    color = colors.gray600
                )
            )
            
            IconButton(
                onClick = onRemoveClick,
                modifier = Modifier.size(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "태그 제거",
                    tint = colors.gray500,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}
