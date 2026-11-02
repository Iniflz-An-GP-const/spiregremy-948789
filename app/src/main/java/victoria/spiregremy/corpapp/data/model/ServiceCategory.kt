package victoria.spiregremy.corpapp.data.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

enum class ServiceCategory(
    @field:StringRes val titleRes: Int,
    val icon: ImageVector,
) {

}
