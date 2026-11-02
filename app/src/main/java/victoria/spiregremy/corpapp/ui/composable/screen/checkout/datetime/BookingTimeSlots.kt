package victoria.spiregremy.corpapp.ui.composable.screen.checkout.datetime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import victoria.spiregremy.corpapp.R
import victoria.spiregremy.corpapp.data.model.BookingWindow
import victoria.spiregremy.corpapp.ui.theme.LocalDimens
import java.time.LocalDate

/**
 * Hour slots (8:00–16:00) for [selectedDate]. Slots already in the past are
 * shown but disabled, not hidden, so the row doesn't jump around during the day.
 */
@Composable
fun BookingTimeSlots(
    selectedDate: LocalDate,
    selectedHour: Int?,
    modifier: Modifier = Modifier,
    onHourSelected: (Int) -> Unit,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.checkout_date_time_dialog_time_label),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
        ) {
            BookingWindow.hours.forEach { hour ->
                val isSelected = selectedHour == hour

                FilterChip(
                    selected = isSelected,
                    onClick = { onHourSelected(hour) },
                    enabled = BookingWindow.isValid(selectedDate, hour),
                    label = {
                        Text(
                            text = "%02d:00".format(hour),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                        labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = isSelected,
                        borderColor = MaterialTheme.colorScheme.outlineVariant,
                        selectedBorderColor = MaterialTheme.colorScheme.primary,
                        borderWidth = dimens.controlBorderWidth,
                        selectedBorderWidth = dimens.controlBorderWidth,
                    ),
                )
            }
        }
    }
}
