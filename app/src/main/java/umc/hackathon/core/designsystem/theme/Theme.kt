package umc.hackathon.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

@Composable
fun UMCHackathonTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalHackathonColorScheme provides basicHackathonColorScheme
    ) {
        MaterialTheme(
            typography = Typography,
            content = content
        )
    }
}

object UMCHackathonTheme {
    val colorScheme: HackathonColorScheme
        @Composable
        get() = LocalHackathonColorScheme.current
}