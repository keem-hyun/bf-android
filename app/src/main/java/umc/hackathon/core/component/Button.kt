package umc.hackathon.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme


@Preview
@Composable
fun SimpleButtonPreview() {
    UMCHackathonTheme {
        Column {
            SimpleButton(text = "버튼", enabled = true) {

            }

            SimpleButton(text = "버튼", enabled = false) {

            }
        }
    }
}

@Composable
fun SimpleButton(
    text: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .background(
                color = if (enabled) UMCHackathonTheme.colorScheme.mainGreen300 else UMCHackathonTheme.colorScheme.gray300,
                RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable(enabled = enabled) {
                if (enabled) onClick.invoke()
            }
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text, style = UMCHackathonTheme.typography.Bold.copy(
                fontSize = 15.sp,
                color = UMCHackathonTheme.colorScheme.white
            )
        )
    }
}


@Composable
@Preview
fun IconCircleButtonPreview() {
    UMCHackathonTheme {
        IconCircleButton(painter = painterResource(R.drawable.ic_filter)) {

        }
    }
}

@Composable
fun IconCircleButton(painter: Painter, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier
            .background(
                color = UMCHackathonTheme.colorScheme.gray100, shape = CircleShape
            )
            .clickable { onClick.invoke() }
            .padding(10.dp)) {
        Image(
            modifier = Modifier.size(18.dp), painter = painter, contentDescription = null
        )
    }
}

@Preview
@Composable
fun DropDownStyleButtonPreview() {
    UMCHackathonTheme {
        Column {
            DropDownStyleButton("메뉴", highlited = true) {

            }

            DropDownStyleButton("메뉴", highlited = false) {

            }
        }
    }
}

@Composable
fun DropDownStyleButton(
    text: String, highlited: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit
) {
    Row(
        modifier
            .background(
                color = if (highlited) Color(0x33B3F2DA) else UMCHackathonTheme.colorScheme.gray100,
                shape = RoundedCornerShape(20.dp)
            )
            .let {
                if (highlited) {
                    it.border(
                        width = 1.dp,
                        color = UMCHackathonTheme.colorScheme.mainGreen200,
                        shape = RoundedCornerShape(20.dp)
                    )
                } else {
                    it
                }
            }
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onClick.invoke()
            }
            .padding(15.dp, 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = text, style = UMCHackathonTheme.typography.SemiBold.copy(
                color = if (highlited) UMCHackathonTheme.colorScheme.mainGreen300 else UMCHackathonTheme.colorScheme.gray500,
                fontSize = 15.sp
            )
        )

        Spacer(Modifier.width(6.dp))

        Image(
            painter = painterResource(R.drawable.ic_down_arrow),
            contentDescription = null,
            colorFilter = if (highlited) ColorFilter.tint(color = UMCHackathonTheme.colorScheme.mainGreen200) else null
        )
    }
}