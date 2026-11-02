package victoria.spiregremy.corpapp.ui.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import victoria.spiregremy.corpapp.data.entity.BookingEntity
import victoria.spiregremy.corpapp.data.model.BookingWindow
import victoria.spiregremy.corpapp.data.repository.BookingRepository
import victoria.spiregremy.corpapp.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class CheckoutViewModel(
    private val bookingRepository: BookingRepository,
) : ViewModel() {
    private val _bookingState = MutableStateFlow<DataUiState<BookingEntity>>(DataUiState.Initial)
    val orderState: StateFlow<DataUiState<BookingEntity>>
        get() = _bookingState.asStateFlow()

    private val _emailInvalidState = MutableStateFlow(false)
    val emailInvalidState: StateFlow<Boolean>
        get() = _emailInvalidState.asStateFlow()

    var customerFirstName by mutableStateOf("")
        private set

    var customerLastName by mutableStateOf("")
        private set

    var customerEmail by mutableStateOf("")
        private set

    // Defaults to the first bookable day and its first open hour slot, so a
    // time is always selected; the hour only becomes null if the chosen day
    // has no open slot left.
    var customerDate by mutableStateOf(BookingWindow.firstBookableDate())
        private set

    var customerHour by mutableStateOf(BookingWindow.firstAvailableHour(customerDate))
        private set

    val customerDateTime: LocalDateTime?
        get() = customerHour?.let { hour -> LocalDateTime.of(customerDate, LocalTime.of(hour, 0)) }

    fun updateCustomerFirstName(input: String) {
        customerFirstName = input
    }

    fun updateCustomerLastName(input: String) {
        customerLastName = input
    }

    fun updateCustomerEmail(input: String) {
        customerEmail = input
        if (_emailInvalidState.value) {
            _emailInvalidState.value = false
        }
    }

    fun updateCustomerDate(date: LocalDate) {
        customerDate = date
        val hour = customerHour
        if (hour == null || !BookingWindow.isValid(date, hour)) {
            customerHour = BookingWindow.firstAvailableHour(date)
        }
    }

    fun updateCustomerHour(hour: Int) {
        if (BookingWindow.isValid(customerDate, hour)) {
            customerHour = hour
        }
    }

    fun placeBooking(serviceId: Int) {
        viewModelScope.launch {
            val dateTime = customerDateTime
            if (isEmailValid() && dateTime != null && BookingWindow.isValid(dateTime)) {
                _emailInvalidState.update { false }
                val booking = BookingEntity(
                    serviceId = serviceId,
                    bookingNumber = generateBookingsNumber(),
                    customerFirstName = customerFirstName,
                    customerLastName = customerLastName,
                    customerEmail = customerEmail,
                    timestamp = dateTime,
                )

                bookingRepository.save(booking)
                _bookingState.update { DataUiState.Populated(data = booking) }
            } else if (!isEmailValid()) {
                _emailInvalidState.update { true }
            }
        }
    }

    private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(customerEmail).matches()
    }

    private fun generateBookingsNumber(): String {
        val chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        return (1..8)
            .map { chars.random() }
            .joinToString("")
    }
}