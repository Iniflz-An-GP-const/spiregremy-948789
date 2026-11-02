package victoria.spiregremy.corpapp.ui.composable.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import victoria.spiregremy.corpapp.R
import victoria.spiregremy.corpapp.ui.theme.LocalDimens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TEXT_ANIMATION_DURATION_MS = 600
private const val TAGLINE_ANIMATION_DURATION_MS = 900
private const val TITLE_DELAY_MS = 300L
private const val TAGLINE_DELAY_MS = 200L

private val HaloSize = 208.dp
private val LogoSize = 120.dp

private const val TAGLINE_START_LETTER_SPACING_SP = 8f
private const val TAGLINE_END_LETTER_SPACING_SP = 0.5f

@Composable
fun SplashContent(
    modifier: Modifier = Modifier,
    onAnimationFinished: () -> Unit,
) {
    val dimens = LocalDimens.current

    val halo = remember { Animatable(0.4f) }
    val logo = remember { Animatable(0.7f) }
    val title = remember { Animatable(0f) }
    val tagline = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        launch {
            halo.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessLow,
                ),
            )
        }

        logo.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessMediumLow,
            ),
        )

        delay(TITLE_DELAY_MS)
        title.animateTo(1f, animationSpec = tween(TEXT_ANIMATION_DURATION_MS))

        delay(TAGLINE_DELAY_MS)
        tagline.animateTo(1f, animationSpec = tween(TAGLINE_ANIMATION_DURATION_MS))

        onAnimationFinished()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(HaloSize)
                        .graphicsLayer {
                            scaleX = halo.value
                            scaleY = halo.value
                            alpha = halo.value.coerceAtMost(1f)
                        }
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                )

                Image(
                    painter = painterResource(R.drawable.icon),
                    contentDescription = stringResource(R.string.viecp_app_logo_description),
                    modifier = Modifier
                        .size(LogoSize)
                        .graphicsLayer {
                            scaleX = logo.value
                            scaleY = logo.value
                        }
                        .clip(CircleShape),
                )
            }

            Spacer(modifier = Modifier.height(dimens.sectionGap))

            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.graphicsLayer { alpha = title.value },
            )

            Spacer(modifier = Modifier.height(dimens.itemSpacing))

            Text(
                text = stringResource(R.string.splash_screen_tagline),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                letterSpacing = lerpSp(
                    start = TAGLINE_START_LETTER_SPACING_SP,
                    end = TAGLINE_END_LETTER_SPACING_SP,
                    fraction = tagline.value,
                ),
                modifier = Modifier
                    .padding(horizontal = dimens.screenPadding)
                    .graphicsLayer { alpha = tagline.value },
            )
        }
    }
}

private fun lerpSp(start: Float, end: Float, fraction: Float) =
    (start + (end - start) * fraction).sp
