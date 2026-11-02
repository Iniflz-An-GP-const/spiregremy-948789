package victoria.spiregremy.corpapp.ui.composable.screen.onboarding

import androidx.annotation.StringRes
import victoria.spiregremy.corpapp.R

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    val imageUrl: String,
)

val onboardingPagesContent = listOf(
    OnboardingContent(
        titleRes = R.string.viecp_page_expertise_title,
        descriptionRes = R.string.viecp_page_expertise_description,
        imageUrl = "https://images.unsplash.com/photo-1542744173-8e7e53415bb0?w=800&q=80",
    ),
    OnboardingContent(
        titleRes = R.string.viecp_page_reliability_title,
        descriptionRes = R.string.viecp_page_reliability_description,
        imageUrl = "https://images.unsplash.com/photo-1517048676732-d65bc937f952?w=800&q=80",
    ),
    OnboardingContent(
        titleRes = R.string.viecp_page_guarantee_title,
        descriptionRes = R.string.viecp_page_guarantee_description,
        imageUrl = "https://images.unsplash.com/photo-1521791136064-7986c2920216?w=800&q=80",
    ),
)
