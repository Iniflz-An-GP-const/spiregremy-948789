package victoria.spiregremy.corpapp.ui.composable.screen.servicedetails.benefits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import victoria.spiregremy.corpapp.data.model.Benefit
import victoria.spiregremy.corpapp.ui.theme.LocalDimens
import victoria.spiregremy.corpapp.ui.theme.controlBorderStroke

private val ChipIconSize = 18.dp

@Composable
fun ServiceBenefitsCard(benefits: List<Benefit>, modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        items(benefits) { benefit ->
            BenefitChip(benefit)
        }
    }
}

@Composable
private fun BenefitChip(benefit: Benefit) {
    val dimens = LocalDimens.current

    Surface(
        shape = RoundedCornerShape(dimens.controlCornerRadius),
        color = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        border = controlBorderStroke(color = MaterialTheme.colorScheme.onTertiaryContainer),
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = dimens.cardPadding,
                vertical = dimens.contentSpacing,
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
        ) {
            Icon(
                imageVector = benefit.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(ChipIconSize),
            )

            Text(
                text = benefit.text,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
