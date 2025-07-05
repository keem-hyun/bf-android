package umc.hackathon.core.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Preview(showBackground = true)
@Composable
fun GridSelectViewPreview() {
    val sampleItems = remember {
        listOf(
            SelectItem("1", "운동"),
            SelectItem("2", "독서"),
            SelectItem("3", "영화 감상"),
            SelectItem("4", "음악 듣기"),
            SelectItem("5", "코딩")
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
}

@Composable
internal fun GridSelectView(
    selectItems: List<SelectItem>,
    selectedItems: List<SelectItem> = emptyList(),
    modifier: Modifier = Modifier,
    onClick: (s: SelectItem) -> Unit,
    washed: Boolean = false
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth()
    ) {
        items(selectItems) { selectItem ->
            SelectItem(
                selectItem = selectItem,
                modifier = Modifier.padding(4.dp),
                onClick = onClick,
                selected = selectedItems.any { it.id == selectItem.id },
                washed = washed
            )
        }
    }
}


@Composable
internal fun SelectItem(
    selectItem: SelectItem,
    modifier: Modifier = Modifier,
    onClick: (s: SelectItem) -> Unit,
    selected: Boolean = false,
    washed: Boolean = false
) {
    var baseColor =
        if (washed) UMCHackathonTheme.colorScheme.gray300 else UMCHackathonTheme.colorScheme.gray600
    if (selected) {
        baseColor = UMCHackathonTheme.colorScheme.mainGreen300
    }
    Box(
        modifier
            .fillMaxWidth()
            .border(1.dp, baseColor, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onClick(selectItem)
            }
            .padding(12.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = selectItem.text,
            style = UMCHackathonTheme.typography.Regular.copy(
                fontSize = 15.sp,
                color = baseColor
            ),
        )
    }
}