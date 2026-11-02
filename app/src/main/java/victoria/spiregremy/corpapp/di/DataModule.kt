package victoria.spiregremy.corpapp.di

import victoria.spiregremy.corpapp.data.repository.BookingRepository
import victoria.spiregremy.corpapp.data.repository.OnboardingRepository
import victoria.spiregremy.corpapp.data.repository.ServiceRepository
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        OnboardingRepository(
            onboardingDataStoreManager = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }

    single { ServiceRepository() }

    single {
        BookingRepository(
            bookingDao = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }
}