package umc.hackathon.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class HackathonColorScheme(
    val mainGreen300: Color,
    val mainGreen200: Color,
    val mainGreen100: Color,
    val mainYellow300: Color,
    val mainYellow200: Color,
    val mainYellow100: Color,
    val black: Color,
    val gray700: Color,
    val gray600: Color,
    val gray500: Color,
    val gray400: Color,
    val gray300: Color,
    val gray200: Color,
    val gray100: Color,
    val white: Color,
    val negativeColor: Color,
    val positiveColor: Color
)

val LocalHackathonColorScheme = staticCompositionLocalOf {
    HackathonColorScheme(
        mainGreen300 = Color.Unspecified,
        mainGreen200 = Color.Unspecified,
        mainGreen100 = Color.Unspecified,
        mainYellow300 = Color.Unspecified,
        mainYellow200 = Color.Unspecified,
        mainYellow100 = Color.Unspecified,
        black = Color.Unspecified,
        gray700 = Color.Unspecified,
        gray600 = Color.Unspecified,
        gray500 = Color.Unspecified,
        gray400 = Color.Unspecified,
        gray300 = Color.Unspecified,
        gray200 = Color.Unspecified,
        gray100 = Color.Unspecified,
        white = Color.Unspecified,
        negativeColor = Color.Unspecified,
        positiveColor = Color.Unspecified
    )
}

internal val basicHackathonColorScheme = HackathonColorScheme(
    mainGreen300 = Color(0xFF01D281),
    mainGreen200 = Color(0xFF67E4B3),
    mainGreen100 = Color(0xFFB3F2DA),
    mainYellow300 = Color(0xFFFFF383),
    mainYellow200 = Color(0xFFFFF8B5),
    mainYellow100 = Color(0xFFFFFCDA),
    black = Color(0xFF101010),
    gray700 = Color(0xFF2D2D2D),
    gray600 = Color(0xFF4B4B4B),
    gray500 = Color(0xFF5F615E),
    gray400 = Color(0xFFA8A8A8),
    gray300 = Color(0xFFE0E0E0),
    gray200 = Color(0xFFF0F0EF),
    gray100 = Color(0xFFF6F6F6),
    white = Color(0xFFFFFFFF),
    negativeColor = Color(0xFFFF5E5E),
    positiveColor = Color(0xFF35DF79)
)