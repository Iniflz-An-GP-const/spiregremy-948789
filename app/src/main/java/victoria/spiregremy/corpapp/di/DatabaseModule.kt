package victoria.spiregremy.corpapp.di

import androidx.room.Room
import victoria.spiregremy.corpapp.data.database.Database
import org.koin.dsl.module

private const val DB_NAME = "viecp_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = Database::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<Database>().bookingDao() }
}