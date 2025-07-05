package umc.hackathon.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Preview
@Composable
fun SearchBarPreview() {
    UMCHackathonTheme {
        SearchBar(
            searchText = "",
            onSearchTextChange = {},
            onSearchClick = {},
            placeholderText = "무슨 일 하고 싶으세요? 검색해볼까요?",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

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
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onSearchClick() }
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                modifier = Modifier
                    .fillMaxWidth()
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
                    IconButton(
                        onClick = onSearchClick
                    ) {
                        Icon(
                            painterResource(R.drawable.ic_search),
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
                ),
                readOnly = true,
                enabled = false
            )
        }

    }


} 