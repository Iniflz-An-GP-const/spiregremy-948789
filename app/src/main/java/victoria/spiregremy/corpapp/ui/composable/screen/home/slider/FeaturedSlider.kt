package victoria.spiregremy.corpapp.ui.composable.screen.home.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import victoria.spiregremy.corpapp.R
import victoria.spiregremy.corpapp.data.model.ServiceModel
import victoria.spiregremy.corpapp.ui.composable.screen.shared.ServiceImage
import victoria.spiregremy.corpapp.ui.theme.LocalDimens
import victoria.spiregremy.corpapp.ui.theme.cardBorderStroke
import victoria.spiregremy.corpapp.ui.theme.controlBorderStroke

@Composable
fun FeaturedSlider(
    services: List<ServiceModel>,
    modifier: Modifier = Modifier,
    onServiceClick: (serviceId: Int) -> Unit,
) {
    val pagerState = rememberPagerState { services.size }
    val dimens = LocalDimens.current

    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = dimens.screenPadding),
            pageSpacing = dimens.contentSpacing,
        ) { page ->
            val service = services[page]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clickable { onServiceClick(service.id) },
                shape = RoundedCornerShape(dimens.cardCornerRadius),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                border = cardBorderStroke(),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    ServiceImage(
                        imageUrl = service.imageUrl,
                        modifier = Modifier.matchParentSize(),
                    )

                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(dimens.cardPadding),
                        shape = RoundedCornerShape(dimens.controlCornerRadius),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        border = controlBorderStroke(color = MaterialTheme.colorScheme.onSecondaryContainer),
                    ) {
                        Text(
                            text = stringResource(service.serviceCategory.titleRes),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        MaterialTheme.colorScheme.scrim.copy(alpha = 0.75f),
                                    ),
                                )
                            )
                            .padding(dimens.cardPadding),
                    ) {
                        Text(
                            text = service.name,
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Text(
                            text = stringResource(R.string.price_per_hour, service.pricePerHour),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimens.screenPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PagerIndicator(
                pageCount = services.size,
                currentPage = pagerState.currentPage,
            )

            Text(
                text = stringResource(
                    R.string.home_slider_page_counter,
                    pagerState.currentPage + 1,
                    services.size,
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@Composable
private fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(pageCount) { page ->
            val isCurrent = page == currentPage

            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(if (isCurrent) 8.dp else 6.dp)
                    .clip(CircleShape)
                    .background(
                        color = if (isCurrent) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.outlineVariant
                        }
                    )
            )
        }
    }
}
