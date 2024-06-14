package com.example.compose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.example.compose.R
import com.example.compose.domain.entities.ListItem
import com.example.compose.presentation.MainViewModel

@Composable
fun ItemListScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val items by remember { mutableStateOf(viewModel.data.data) }
    LazyColumn(modifier.background(MaterialTheme.colorScheme.background)) {
        items(items) { item ->
            if (item is ListItem.StudentItem) {
                Student(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        )
                        .fillMaxWidth(),
                    item = item
                )
            } else {
                Banner(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 8.dp),
                    item = item as ListItem.BannerItem
                )
            }
        }
    }
}

@Composable
fun Student(modifier: Modifier = Modifier, item: ListItem.StudentItem) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (icon, name, description, markPortfolio, separatorLine) = createRefs()

        StudentFormIcon(
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Title(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(name) {
                    start.linkTo(icon.end, margin = 16.dp)
                    end.linkTo(markPortfolio.start, margin = 16.dp)
                    top.linkTo(icon.top)
                    bottom.linkTo(description.top)
                    width = Dimension.fillToConstraints
                }, text = stringResource(id = R.string.name_template).format(
                item.name,
                item.secondName
            ),
            fontSize = 16.sp
        )

        Description(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(description) {
                    start.linkTo(icon.end, margin = 16.dp)
                    end.linkTo(markPortfolio.start, margin = 16.dp)
                    top.linkTo(name.bottom)
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                    width = Dimension.fillToConstraints
                },
            text = item.description,
            textStyle = MaterialTheme.typography.bodyMedium
        )

        MarkIcon(modifier = Modifier
            .constrainAs(markPortfolio) {
                end.linkTo(parent.end)
                bottom.linkTo(description.top)
                top.linkTo(name.top)
                visibility =
                    if (item.hasPortfolio) Visibility.Visible else Visibility.Invisible
            })

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12F))
                .constrainAs(separatorLine) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(icon.end)
                }
        )
    }
}

@Composable
fun StudentFormIcon(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_student_form_form),
        contentDescription = null
    )
}

@Composable
fun Title(modifier: Modifier = Modifier, text: String, fontSize: TextUnit) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = fontSize,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.87F)
    )
}

@Composable
fun Description(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = textStyle,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6F)
    )
}

@Composable
fun MarkIcon(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        alpha = 0.54F,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        painter = painterResource(id = R.drawable.ic_show_details_request),
        contentDescription = null,
    )
}

@Composable
fun Banner(modifier: Modifier = Modifier, item: ListItem.BannerItem) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        ConstraintLayout(
            Modifier.fillMaxWidth()
        ) {
            val (icon, title, description, applyButton, rejectButton) = createRefs()

            Title(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(title) {
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(icon.start, margin = 16.dp)
                        top.linkTo(parent.top, margin = 16.dp)
                        width = Dimension.fillToConstraints
                    },
                text = item.title,
                fontSize = 24.sp
            )

            Description(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(description) {
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(icon.start, margin = 16.dp)
                        top.linkTo(title.bottom, margin = 16.dp)
                        width = Dimension.fillToConstraints
                    },
                text = item.description,
                textStyle = MaterialTheme.typography.bodyLarge
            )

            BannerTextButton(
                modifier = Modifier
                    .constrainAs(applyButton) {
                        start.linkTo(parent.start, margin = 8.dp)
                        top.linkTo(description.bottom, margin = 8.dp)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    },
                text = stringResource(id = R.string.apply).uppercase()
            )

            BannerTextButton(
                modifier = Modifier
                    .constrainAs(rejectButton) {
                        start.linkTo(applyButton.end, margin = 8.dp)
                        top.linkTo(description.bottom, margin = 8.dp)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    }, text = stringResource(id = R.string.reject).uppercase()
            )

            BannerIcon(
                modifier = Modifier
                    .constrainAs(icon) {
                        start.linkTo(description.end)
                        end.linkTo(parent.end, margin = 16.dp)
                        top.linkTo(parent.top, margin = 16.dp)
                    }
            )
        }
    }
}

@Composable
fun BannerTextButton(modifier: Modifier = Modifier, text: String) {
    TextButton(onClick = {}, modifier = modifier) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight(500),
            fontSize = 14.sp
        )
    }
}

@Composable
fun BannerIcon(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_banner_form),
        contentDescription = null
    )
}
