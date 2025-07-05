package umc.hackathon.core.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
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
    var selectedChildren by remember { mutableStateOf<List<SelectItem>>(emptyList()) }

    UMCHackathonTheme {
        HierarchicalSelectView(
            parentItems = sampleParentItems,
            selectParentItem = selectedParent,
            childItems = sampleChildItems,
            selectedChildItems = selectedChildren,
            onParentItemChange = { newParent ->
                selectedParent = newParent
                selectedChildren = emptyList()
            },
            onChildItemChange = { newChildren ->
                selectedChildren = newChildren
            }
        )
    }
}

@Composable
fun HierarchicalSelectView(
    parentItems: List<SelectItem> = emptyList(),
    selectParentItem: SelectItem? = null,
    childItems: Map<String, List<SelectItem>> = mapOf(),
    selectedChildItems: List<SelectItem> = emptyList(),
    onParentItemChange: (SelectItem?) -> Unit = {},
    onChildItemChange: (List<SelectItem>) -> Unit = {}
) {
    Row(Modifier.fillMaxWidth().height(400.dp)) {
        LazyColumn(
            Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(parentItems) { item ->
                SelectItem(
                    selectItem = item,
                    onClick = { clickedItem ->
                        val newSelection = if (selectParentItem?.id == clickedItem.id) null else clickedItem
                        onParentItemChange(newSelection)
                    },
                    modifier = Modifier.padding(horizontal = 8.dp),
                    selected = selectParentItem?.id == item.id,
                    washed = selectParentItem != null && selectParentItem.id != item.id
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
                    val isSelected = selectedChildItems.any { it.id == childItem.id }
                    val isWashed = selectedChildItems.isNotEmpty() && !isSelected

                    SelectItem(
                        selectItem = childItem,
                        onClick = { clickedChild ->
                            val currentSelection = selectedChildItems.toMutableList()
                            val isAlreadySelected = currentSelection.any { it.id == clickedChild.id }

                            if (isAlreadySelected) {
                                currentSelection.removeAll { it.id == clickedChild.id }
                            } else {
                                currentSelection.add(clickedChild)
                            }
                            onChildItemChange(currentSelection)
                        },
                        modifier = Modifier.padding(horizontal = 8.dp),
                        selected = isSelected,
                        washed = isWashed
                    )
                }
        }
    }
}