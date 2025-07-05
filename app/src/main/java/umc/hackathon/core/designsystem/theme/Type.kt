package umc.hackathon.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
data class HackathonTypography(
    val Light: TextStyle,
    val Regular: TextStyle,
    val SemiBold: TextStyle,
    val Bold: TextStyle,
    val ExtraBold: TextStyle
)

val LocalHackathonTypography = staticCompositionLocalOf {
    HackathonTypography(
        Light = TextStyle.Default,
        Regular = TextStyle.Default,
        SemiBold = TextStyle.Default,
        Bold = TextStyle.Default,
        ExtraBold = TextStyle.Default
    )
}

internal val basicHackathonTypography = HackathonTypography(
    Light = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.W300,
        fontSize = 14.sp,
        lineHeight = 1.em
    ),
    Regular = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 1.em
    ),
    SemiBold = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        lineHeight = 1.em
    ),
    Bold = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        lineHeight = 1.em
    ),
    ExtraBold = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.W800,
        fontSize = 22.sp,
        lineHeight = 1.em
    )
)