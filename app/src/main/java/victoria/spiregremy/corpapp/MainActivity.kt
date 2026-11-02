package victoria.spiregremy.corpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import victoria.spiregremy.corpapp.ui.composable.approot.AppRoot
import victoria.spiregremy.corpapp.ui.theme.VIECPAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VIECPAppTheme {
                AppRoot()
            }
        }
    }
}