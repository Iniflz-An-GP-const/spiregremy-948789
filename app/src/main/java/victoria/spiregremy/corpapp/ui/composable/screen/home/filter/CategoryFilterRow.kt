package victoria.spiregremy.corpapp.ui.composable.screen.home.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import victoria.spiregremy.corpapp.R
import victoria.spiregremy.corpapp.data.model.ServiceCategory
import victoria.spiregremy.corpapp.ui.theme.ControlDivider
import victoria.spiregremy.corpapp.ui.theme.LocalDimens
import victoria.spiregremy.corpapp.ui.theme.controlBorderStroke

private val MARKER_SIZE = 8.dp
private val MARKER_BORDER_WIDTH = 1.dp

@Composable
fun CategoryFilterRow(
    selectedCategory: ServiceCategory?,
    modifier: Modifier = Modifier,
    onCategoryClick: (ServiceCategory?) -> Unit,
) {
    val dimens = LocalDimens.current

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Column {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = dimens.screenPadding,
                    vertical = dimens.contentSpacing,
                ),
                horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
            ) {
                item {
                    CategoryChip(
                        isSelected = selectedCategory == null,
                        title = stringResource(R.string.home_filter_all),
                        onClick = { onCategoryClick(null) },
                    )
                }

                items(items = ServiceCategory.entries) { category ->
                    CategoryChip(
                        isSelected = selectedCategory == category,
                        title = stringResource(category.titleRes),
                        onClick = { onCategoryClick(category) },
                    )
                }
            }

            ControlDivider()
        }
    }
}

@Composable
private fun CategoryChip(
    isSelected: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val dimens = LocalDimens.current
    val shape = RoundedCornerShape(dimens.controlCornerRadius)

    Surface(
        modifier = modifier
            .clip(shape)
            .clickable(onClick = onClick),
        shape = shape,
        color = if (isSelected) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceContainerLow
        },
        contentColor = if (isSelected) {
            MaterialTheme.colorScheme.onSecondaryContainer
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        border = controlBorderStroke(),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(MARKER_SIZE)
                    .clip(CircleShape)
                    .then(
                        if (isSelected) {
                            Modifier.background(MaterialTheme.colorScheme.primary)
                        } else {
                            Modifier.border(
                                width = MARKER_BORDER_WIDTH,
                                color = MaterialTheme.colorScheme.outlineVariant,
                                shape = CircleShape,
                            )
                        }
                    )
            )

            Spacer(modifier = Modifier.width(dimens.itemSpacing))

            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}
