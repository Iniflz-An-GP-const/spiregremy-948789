package victoria.spiregremy.corpapp.ui.composable.screen.servicedetails.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import victoria.spiregremy.corpapp.R
import victoria.spiregremy.corpapp.data.model.ServiceModel
import victoria.spiregremy.corpapp.data.model.defaultServiceBenefits
import victoria.spiregremy.corpapp.ui.composable.approot.LocalContentBottomPadding
import victoria.spiregremy.corpapp.ui.composable.screen.servicedetails.benefits.ServiceBenefitsCard
import victoria.spiregremy.corpapp.ui.composable.screen.servicedetails.button.BookConsultationButton
import victoria.spiregremy.corpapp.ui.composable.screen.servicedetails.image.ServiceDetailsImage
import victoria.spiregremy.corpapp.ui.composable.screen.servicedetails.includedlist.ServiceIncludedItemsList
import victoria.spiregremy.corpapp.ui.composable.screen.servicedetails.summary.ServiceSummary
import victoria.spiregremy.corpapp.ui.composable.screen.shared.sectiontitle.SectionTitle
import victoria.spiregremy.corpapp.ui.theme.LocalDimens

@Composable
fun ServiceDetailsLayout(
    service: ServiceModel,
    modifier: Modifier = Modifier,
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = dimens.screenPadding + LocalContentBottomPadding.current),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = dimens.screenPadding, vertical = dimens.screenPadding),
        ) {
            ServiceDetailsImage(
                imageUrl = service.imageUrl,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(dimens.contentSpacing))

            ServiceSummary(
                service = service,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(dimens.contentSpacing))

            SectionTitle(
                text = stringResource(R.string.service_details_description_title),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(dimens.itemSpacing))

            Text(
                text = service.description,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
            )

            if (defaultServiceBenefits.isNotEmpty()) {
                Spacer(modifier = Modifier.height(dimens.contentSpacing))

                ServiceBenefitsCard(
                    benefits = defaultServiceBenefits,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            if (service.includedItems.isNotEmpty()) {
                Spacer(modifier = Modifier.height(dimens.contentSpacing))

                SectionTitle(
                    text = stringResource(R.string.service_details_included_title),
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(dimens.itemSpacing))

                ServiceIncludedItemsList(
                    items = service.includedItems,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        BookConsultationButton(
            onClick = { onNavigateToCheckout(service.id) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
