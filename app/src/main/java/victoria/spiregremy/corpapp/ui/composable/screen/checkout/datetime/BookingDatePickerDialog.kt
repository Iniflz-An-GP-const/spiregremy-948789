package victoria.spiregremy.corpapp.ui.composable.screen.checkout.datetime

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import victoria.spiregremy.corpapp.R
import victoria.spiregremy.corpapp.data.model.BookingWindow
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

/**
 * Days before the first bookable date (today, or tomorrow once today's last
 * slot has passed) are disabled in the calendar, so a past date can't be picked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingDatePickerDialog(
    initialDate: LocalDate,
    onConfirm: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
) {
    val firstDate = remember { BookingWindow.firstBookableDate() }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = maxOf(initialDate, firstDate).toUtcMillis(),
        yearRange = firstDate.year..(firstDate.year + 1),
        selectableDates = remember {
            object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return !utcTimeMillis.toLocalDate().isBefore(firstDate)
                }

                override fun isSelectableYear(year: Int): Boolean {
                    return year >= firstDate.year
                }
            }
        },
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = { datePickerState.selectedDateMillis?.let { onConfirm(it.toLocalDate()) } },
                enabled = datePickerState.selectedDateMillis != null,
            ) {
                Text(text = stringResource(R.string.checkout_date_time_dialog_confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.checkout_date_time_dialog_cancel))
            }
        },
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = false,
        )
    }
}

private fun LocalDate.toUtcMillis(): Long =
    atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()

private fun Long.toLocalDate(): LocalDate =
    Instant.ofEpochMilli(this).atZone(ZoneOffset.UTC).toLocalDate()
