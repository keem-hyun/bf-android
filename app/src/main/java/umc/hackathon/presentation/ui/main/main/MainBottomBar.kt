package umc.hackathon.presentation.ui.main.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import umc.hackathon.core.navigation.MainTabRoute

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    tabs: List<MainTabRoute>,
    currentTab: MainTabRoute,
    onTabSelected: (MainTabRoute) -> Unit
) {
    Surface(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .navigationBarsPadding()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                BottomBarItem(
                    tab = tab,
                    isSelected = tab == currentTab,
                    onClick = { onTabSelected(tab) }
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    tab: MainTabRoute,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val iconRes = if (isSelected) tab.selectedIcon else tab.unselectedIcon

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = stringResource(id = tab.label),
            style = MaterialTheme.typography.labelSmall
        )
    }
}
