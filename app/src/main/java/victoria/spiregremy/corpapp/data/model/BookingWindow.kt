package victoria.spiregremy.corpapp.data.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * When a booking is allowed to start: not in the past, and only within the
 * hours the consultancy actually takes sessions. Shared by the date picker and
 * hour slots (to grey out invalid choices) and `CheckoutViewModel` (to re-validate
 * before writing a booking, the same way email format is re-validated there
 * rather than trusted from the UI alone).
 */
object BookingWindow {
    const val START_HOUR = 8
    const val END_HOUR = 16

    val hours: IntRange = START_HOUR..END_HOUR

    fun isValid(dateTime: LocalDateTime): Boolean {
        return dateTime.isAfter(LocalDateTime.now()) && dateTime.hour in hours
    }

    fun isValid(date: LocalDate, hour: Int): Boolean {
        return isValid(LocalDateTime.of(date, LocalTime.of(hour, 0)))
    }

    /** First hour still bookable on [date], or null if the whole day is over. */
    fun firstAvailableHour(date: LocalDate): Int? {
        return hours.firstOrNull { hour -> isValid(date, hour) }
    }

    /** Today, unless today's last slot has already passed. */
    fun firstBookableDate(): LocalDate {
        val today = LocalDate.now()
        return if (firstAvailableHour(today) != null) today else today.plusDays(1)
    }
}
