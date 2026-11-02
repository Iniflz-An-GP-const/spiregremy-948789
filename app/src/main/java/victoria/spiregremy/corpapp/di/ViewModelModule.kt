package victoria.spiregremy.corpapp.di

import victoria.spiregremy.corpapp.ui.viewmodel.BookingViewModel
import victoria.spiregremy.corpapp.ui.viewmodel.CheckoutViewModel
import victoria.spiregremy.corpapp.ui.viewmodel.OnboardingViewModel
import victoria.spiregremy.corpapp.ui.viewmodel.ServiceDetailsViewModel
import victoria.spiregremy.corpapp.ui.viewmodel.ServiceViewModel
import victoria.spiregremy.corpapp.ui.viewmodel.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        SplashViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        OnboardingViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}