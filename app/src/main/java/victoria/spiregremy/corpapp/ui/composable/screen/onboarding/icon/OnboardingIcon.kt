package victoria.spiregremy.corpapp.ui.composable.screen.onboarding.icon

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import victoria.spiregremy.corpapp.ui.composable.screen.shared.ServiceImage
import victoria.spiregremy.corpapp.ui.theme.LocalDimens
import victoria.spiregremy.corpapp.ui.theme.imageBorder

private const val ImageAspectRatio = 4f / 3f

@Composable
fun OnboardingIcon(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(LocalDimens.current.cardCornerRadius)

    ServiceImage(
        imageUrl = imageUrl,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(ImageAspectRatio)
            .clip(shape)
            .imageBorder(shape),
    )
}
