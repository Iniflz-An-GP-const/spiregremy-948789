package victoria.spiregremy.corpapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import victoria.spiregremy.corpapp.data.dao.BookingDao
import victoria.spiregremy.corpapp.data.database.converter.Converters
import victoria.spiregremy.corpapp.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

