package umc.hackathon.presentation.ui.main.mypage.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.HackathonColorScheme
import umc.hackathon.core.designsystem.theme.LocalHackathonColorScheme
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme


@Composable
fun AddButton(
    isExpanded: Boolean,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = if (isExpanded)
            LocalHackathonColorScheme.current.mainGreen300
        else LocalHackathonColorScheme.current.white
    ) {
        Icon(
            painter = painterResource(
                id = if (isExpanded) R.drawable.ic_floating_invalid
                else R.drawable.ic_floating_vailid
            ),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddButtonPreview() {
    UMCHackathonTheme {
        AddButton(isExpanded = true, onClick = {})
    }

}
