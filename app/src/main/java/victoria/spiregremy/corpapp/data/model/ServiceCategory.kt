package victoria.spiregremy.corpapp.data.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountTree
import androidx.compose.material.icons.outlined.AutoGraph
import androidx.compose.material.icons.outlined.ChangeCircle
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.material.icons.outlined.Tune
import victoria.spiregremy.corpapp.R

enum class ServiceCategory(
    @field:StringRes val titleRes: Int,
    val icon: ImageVector,
) {
    STRATEGY(R.string.service_category_strategy_title, Icons.Outlined.AutoGraph),
    ORGANIZATION_DESIGN(R.string.service_category_organization_design_title, Icons.Outlined.AccountTree),
    PROCESS_IMPROVEMENT(R.string.service_category_process_improvement_title, Icons.Outlined.Tune),
    PERFORMANCE_MANAGEMENT(R.string.service_category_performance_management_title, Icons.Outlined.Speed),
    CHANGE_MANAGEMENT(R.string.service_category_change_management_title, Icons.Outlined.ChangeCircle),
}
