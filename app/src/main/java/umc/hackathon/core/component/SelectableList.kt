package umc.hackathon.core.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SelectableList(
    modifier: Modifier = Modifier,
    items: List<String>,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        items.forEach { item ->
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) },
                headlineContent = {
                    Text(text = item, style = MaterialTheme.typography.bodyMedium)
                }
            )
            Divider()
        }
    }
}

@Preview
@Composable
fun SelectableListPreview() {
    val items = listOf("Item 1", "Item 2", "Item 3")
    SelectableList(
        items = items,
        onItemClick = { /* Handle item click */ }

    )
}