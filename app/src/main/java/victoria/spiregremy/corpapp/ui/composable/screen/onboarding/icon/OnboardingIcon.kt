package victoria.spiregremy.corpapp.ui.composable.screen.onboarding.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import victoria.spiregremy.corpapp.ui.theme.imageBorder

private val CompositionSize = 152.dp
private val BackgroundIconSize = 128.dp
private const val BackgroundIconAlpha = 0.16f
private val BadgeSize = 56.dp
private val BadgeIconSize = 28.dp

@Composable
fun OnboardingIcon(
    icons: List<ImageVector>,
    modifier: Modifier = Modifier,
) {
    val backgroundIcon = icons.first()
    val badgeIcon = if (icons.size > 1) icons[1] else icons.first()

    Box(
        modifier = modifier.size(CompositionSize),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = backgroundIcon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary.copy(alpha = BackgroundIconAlpha),
            modifier = Modifier.size(BackgroundIconSize),
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(BadgeSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .imageBorder(CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = badgeIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(BadgeIconSize),
            )
        }
    }
}
