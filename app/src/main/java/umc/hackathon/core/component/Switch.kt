package umc.hackathon.core.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

@Preview
@Composable
fun SwitchPreview() {
    UMCHackathonTheme {
        var checked by remember { androidx.compose.runtime.mutableStateOf(false) }
        Column {
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                modifier = Modifier
            )
        }
    }
}

@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (checked) UMCHackathonTheme.colorScheme.mainGreen100 else UMCHackathonTheme.colorScheme.gray300,
        animationSpec = tween(durationMillis = 300),
        label = "backgroundColorAnimation"
    )

    val horizontalBias by animateFloatAsState(
        targetValue = if (checked) 1f else -1f,
        animationSpec = tween(durationMillis = 300),
        label = "horizontalBiasAnimation"
    )

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 42.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable { onCheckedChange.invoke(!checked) }
            .padding(2.dp)
    ) {
        Box(
            Modifier
                .size(20.dp)
                .align(BiasAlignment(horizontalBias = horizontalBias, verticalBias = 0f))
                .background(color = UMCHackathonTheme.colorScheme.white, shape = CircleShape)
                .clip(CircleShape)
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x0D000000),
                    ambientColor = Color(0x0D000000)
                )
        )
    }
}