package umc.hackathon.core.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

data class SelectItem(val id: String, val text: String)

@Preview
@Composable
fun HierarchicalSelectViewPreview() {
    val sampleParentItems = listOf(
        SelectItem("p1", "Fruits"),
        SelectItem("p2", "Vegetables"),
        SelectItem("p3", "Dairy")
    )

    val sampleChildItems = mapOf(
        "p1" to listOf(
            SelectItem("c1_1", "Apple"),
            SelectItem("c1_2", "Banana"),
            SelectItem("c1_3", "Cherry")
        ),
        "p2" to listOf(
            SelectItem("c2_1", "Carrot"),
            SelectItem("c2_2", "Broccoli"),
            SelectItem("c2_3", "Spinach")
        ),
        "p3" to listOf(
            SelectItem("c3_1", "Milk"),
            SelectItem("c3_2", "Cheese"),
            SelectItem("c3_3", "Yogurt")
        )
    )

    var selectedParent by remember { mutableStateOf<SelectItem?>(null) }
    var selectedChild by remember { mutableStateOf<SelectItem?>(null) }

    UMCHackathonTheme {
        HierarchicalSelectView(
            parentItems = sampleParentItems,
            selectParentItem = selectedParent,
            childItems = sampleChildItems,
            selectChildItem = selectedChild,
            onParentItemChange = { parent ->
                selectedParent = parent
                selectedChild = null
            },
            onChildItemChange = { child ->
                selectedChild = child
            }
        )
    }
}

@Composable
fun HierarchicalSelectView(
    parentItems: List<SelectItem> = emptyList(),
    selectParentItem: SelectItem? = null,
    childItems: Map<String, List<SelectItem>> = mapOf(),
    selectChildItem: SelectItem? = null,
    onParentItemChange: (SelectItem?) -> Unit = {},
    onChildItemChange: (SelectItem?) -> Unit = {}
) {
    Row(Modifier.fillMaxWidth()) {
        LazyColumn(
            Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(parentItems) {
                SelectItem(
                    selectItem = it,
                    onClick = {
                        onParentItemChange.invoke(it)
                        onChildItemChange.invoke(null)
                    },
                    modifier = Modifier.padding(horizontal = 8.dp),
                    selected = selectParentItem?.id == it.id,
                    washed = selectParentItem != null && selectParentItem.id != it.id
                )
            }
        }
        Spacer(Modifier.width(8.dp))
        LazyColumn(
            Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            if (selectParentItem != null)
                items(childItems[selectParentItem.id] ?: emptyList()) { childItem ->
                    SelectItem(
                        selectItem = childItem,
                        onClick = {
                            onChildItemChange.invoke(childItem)
                        },
                        modifier = Modifier.padding(horizontal = 8.dp),
                        selected = selectChildItem?.id == childItem.id,
                        washed = selectChildItem != null && selectChildItem.id != childItem.id
                    )
                }
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
                onClick.invoke(selectItem)
            }
            .padding(12.dp)
    ) {
        Text(
            text = selectItem.text,
            style = UMCHackathonTheme.typography.Regular.copy(
                fontSize = 15.sp,
                color = baseColor
            ),
        )
    }
}