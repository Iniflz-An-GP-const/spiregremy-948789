package victoria.spiregremy.corpapp.ui.composable.screen.onboarding.controls

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import victoria.spiregremy.corpapp.R
import victoria.spiregremy.corpapp.ui.theme.LocalDimens

@Composable
fun OnboardingControls(
    pageCount: Int,
    currentPage: Int,
    isLastPage: Boolean,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd,
        ) {
            if (!isLastPage) {
                TextButton(
                    onClick = onSkipClick,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                    ),
                ) {
                    Text(text = stringResource(R.string.skip_button_title))
                }
            }
        }

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        Button(
            onClick = onNextClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimens.controlCornerRadius),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
            Text(
                text = stringResource(
                    R.string.onboarding_button_progress,
                    stringResource(
                        if (isLastPage) R.string.start_button_title else R.string.next_button_title
                    ),
                    currentPage + 1,
                    pageCount,
                )
            )
        }
    }
}
