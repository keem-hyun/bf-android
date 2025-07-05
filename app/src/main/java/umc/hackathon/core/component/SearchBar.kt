package umc.hackathon.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchClick: () -> Unit = {},
    placeholderText: String = "무슨 일 하고 싶으세요? 검색해볼까요?",
    modifier: Modifier = Modifier
) {
    val colors = UMCHackathonTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.white)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 17.dp, vertical = 17.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFFFFCDA),
                            Color(0xFFB3F2DA)
                        )
                    ),
                    shape = RoundedCornerShape(18.dp)
                ),
            placeholder = {
                Text(
                    placeholderText,
                    style = UMCHackathonTheme.typography.Regular.copy(
                        fontSize = 15.sp,
                        color = colors.gray500
                    )
                )
            },
            trailingIcon = {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "검색",
                        tint = colors.mainGreen300
                    )
                }
            },
            shape = RoundedCornerShape(18.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colors.mainGreen200,
                unfocusedBorderColor = colors.mainGreen200,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

    }


} 