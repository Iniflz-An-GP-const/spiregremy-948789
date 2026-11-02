package victoria.spiregremy.corpapp

import android.app.Application
import victoria.spiregremy.corpapp.di.dataModule
import victoria.spiregremy.corpapp.di.dispatcherModule
import victoria.spiregremy.corpapp.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class VIECPApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@VIECPApplication)
            modules(appModules)
        }
    }
}
