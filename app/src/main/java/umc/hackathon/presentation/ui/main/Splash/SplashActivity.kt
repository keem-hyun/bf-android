package umc.hackathon.presentation.ui.main.Splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import umc.hackathon.presentation.ui.main.main.MainActivity

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UMCHackathonTheme {
                SplashScreen {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }
}

enum class GradientState { Initial, Reversed }

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    var gradientState by remember { mutableStateOf(GradientState.Initial) }

    LaunchedEffect(Unit) {
        delay(300)
        gradientState = GradientState.Reversed
        delay(1800)
        onTimeout()
    }

    val transition = updateTransition(targetState = gradientState, label = "GradientTransition")

    val topColor by transition.animateColor(
        transitionSpec = { tween(1200, easing = FastOutSlowInEasing) },
        label = "TopColor"
    ) { state ->
        when (state) {
            GradientState.Initial -> Color(0xFF01D281) // 초록
            GradientState.Reversed -> Color(0xFFFFF8B5) // 연노랑
        }
    }

    val bottomColor by transition.animateColor(
        transitionSpec = { tween(1200, easing = FastOutSlowInEasing) },
        label = "BottomColor"
    ) { state ->
        when (state) {
            GradientState.Initial -> Color(0xFFFFF8B5)
            GradientState.Reversed -> Color(0xFF01D281)
        }
    }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(topColor, bottomColor)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = umc.hackathon.R.drawable.ic_logo_splash_final),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(240.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "누구에게나 열린 일자리, 배포와 함께",
                color = UMCHackathonTheme.colorScheme.white,
                style = UMCHackathonTheme.typography.Bold.copy(fontSize = 16.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun showSplash() {
    UMCHackathonTheme {
        SplashScreen(onTimeout = {})
    }
}
