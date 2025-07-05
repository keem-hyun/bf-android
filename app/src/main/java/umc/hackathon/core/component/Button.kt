package umc.hackathon.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme

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
                color = UMCHackathonTheme.colorScheme.gray100,
                shape = CircleShape
            )
            .clickable { onClick.invoke() }
            .padding(10.dp)
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painter,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun DropDownStyleButtonPreview() {
    UMCHackathonTheme {
        DropDownStyleButton("메뉴") {

        }
    }
}

@Composable
fun DropDownStyleButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier
            .background(
                color = UMCHackathonTheme.colorScheme.gray100,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onClick.invoke()
            }
            .padding(15.dp, 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = UMCHackathonTheme.typography.SemiBold.copy(
                color = UMCHackathonTheme.colorScheme.gray500,
                fontSize = 15.sp
            )
        )

        Spacer(Modifier.width(6.dp))

        Image(painter = painterResource(R.drawable.ic_down_arrow), contentDescription = null)
    }
}