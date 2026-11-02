package victoria.spiregremy.corpapp.ui.composable.screen.checkout.datetime

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import victoria.spiregremy.corpapp.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * The whole field is one button: a transparent overlay on top of the
 * read-only text field takes every tap, so the field itself never gains
 * focus and the soft keyboard never opens.
 */
@Composable
fun BookingDateField(
    selectedDate: LocalDate,
    modifier: Modifier = Modifier,
    onDateSelected: (LocalDate) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    val displayFormatter = remember { DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM) }

    Box(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedDate.format(displayFormatter),
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .focusProperties { canFocus = false },
            readOnly = true,
            label = {
                Text(
                    text = stringResource(R.string.checkout_text_field_date),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleSmall,
                )
            },
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.CalendarMonth, contentDescription = null)
            },
            singleLine = true,
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable(role = Role.Button) { showDialog = true },
        )
    }

    if (showDialog) {
        BookingDatePickerDialog(
            initialDate = selectedDate,
            onConfirm = { date ->
                showDialog = false
                onDateSelected(date)
            },
            onDismiss = { showDialog = false },
        )
    }
}
