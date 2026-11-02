package victoria.spiregremy.corpapp.data.repository

import victoria.spiregremy.corpapp.data.dao.BookingDao
import victoria.spiregremy.corpapp.data.entity.BookingEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class BookingRepository(
    private val bookingDao: BookingDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    fun observeAll(): Flow<List<BookingEntity>> {
        return bookingDao.observeAll()
    }

    suspend fun save(bookingEntity: BookingEntity) {
        return withContext(coroutineDispatcher) {
            bookingDao.save(bookingEntity)
        }
    }

    suspend fun deleteByBookingNumber(bookingNumber: String) {
        withContext(coroutineDispatcher) {
            bookingDao.deleteByBookingNumber(bookingNumber)
        }
    }
}