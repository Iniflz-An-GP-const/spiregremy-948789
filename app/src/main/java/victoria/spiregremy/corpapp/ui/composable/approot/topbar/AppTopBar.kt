package victoria.spiregremy.corpapp.ui.composable.approot.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import victoria.spiregremy.corpapp.R
import victoria.spiregremy.corpapp.ui.composable.approot.matchesAnyRoute
import victoria.spiregremy.corpapp.ui.composable.approot.topbar.canNavigateBackRoutes
import victoria.spiregremy.corpapp.ui.composable.approot.topbar.topBarTitleRes
import victoria.spiregremy.corpapp.ui.theme.ControlDivider
import victoria.spiregremy.corpapp.ui.theme.Primary
import victoria.spiregremy.corpapp.ui.theme.StatusBarIconsFor

private const val DividerAlpha = 0.4f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentDestination: NavDestination?,
    onNavigateBack: () -> Unit,
) {
    val canNavigateBack = currentDestination.matchesAnyRoute(canNavigateBackRoutes)
    val containerColor = MaterialTheme.colorScheme.primaryContainer

    StatusBarIconsFor(Primary)

    Column {
        TopAppBar(
            title = {
                Text(
                    text = topBarTitleRes(currentDestination)?.let { stringResource(it) }.orEmpty(),
                    style = MaterialTheme.typography.titleLarge,
                )
            },

            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_icon_description),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
            },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = containerColor,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
        )

        ControlDivider(
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = DividerAlpha),
        )
    }
}
