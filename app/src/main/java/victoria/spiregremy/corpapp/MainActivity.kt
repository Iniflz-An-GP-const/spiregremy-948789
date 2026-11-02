package victoria.spiregremy.corpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import victoria.spiregremy.corpapp.ui.composable.approot.AppRoot
import victoria.spiregremy.corpapp.ui.theme.Primary
import victoria.spiregremy.corpapp.ui.theme.VIECPAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Primary.toArgb(), android.graphics.Color.BLACK)
        )
        setContent {
            VIECPAppTheme {
                AppRoot()
            }
        }
    }
}