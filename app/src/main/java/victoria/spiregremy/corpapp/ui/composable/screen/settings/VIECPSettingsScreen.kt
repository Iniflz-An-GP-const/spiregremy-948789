package victoria.spiregremy.corpapp.ui.composable.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import victoria.spiregremy.corpapp.ui.composable.approot.LocalContentBottomPadding
import victoria.spiregremy.corpapp.ui.composable.screen.settings.card.AppInfoCard
import victoria.spiregremy.corpapp.ui.theme.LocalDimens

@Composable
fun VIECPSettingsScreen(modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimens.screenPadding)
            .padding(bottom = LocalContentBottomPadding.current),
    ) {
        AppInfoCard()
    }
}
