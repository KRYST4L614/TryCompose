package com.example.compose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.R
import com.example.compose.domain.entities.ListItem
import com.example.compose.presentation.MainViewModel

@Composable
fun ItemListScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    ItemListScreen(modifier = modifier, items = viewModel.data.data)
}

@Composable
fun ItemListScreen(
    modifier: Modifier,
    items: List<ListItem>,
) {
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
    Row(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_student_form_form),
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Title(
                        modifier = Modifier,
                        text = stringResource(id = R.string.name_template).format(
                            item.name,
                            item.secondName
                        ),
                        fontSize = 16.sp
                    )

                    Description(
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        text = item.description,
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }

                if (item.hasPortfolio) {
                    Image(
                        modifier = Modifier.wrapContentSize(),
                        alpha = 0.54F,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                        painter = painterResource(id = R.drawable.ic_show_details_request),
                        contentDescription = null,
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12F))
            )
        }
    }
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
fun Banner(modifier: Modifier = Modifier, item: ListItem.BannerItem) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Title(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    text = item.title,
                    fontSize = 24.sp
                )

                Description(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    text = item.description,
                    textStyle = MaterialTheme.typography.bodyLarge
                )

                Row(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    BannerTextButton(
                        modifier = Modifier,
                        text = stringResource(id = R.string.apply).uppercase()
                    )

                    BannerTextButton(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = stringResource(id = R.string.reject).uppercase()
                    )
                }
            }
            BannerIcon(
                modifier = Modifier
                    .padding(end = 8.dp, top = 8.dp)

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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ItemsListScreenPreview() {
    ItemListScreen(
        modifier = Modifier, items = listOf(
            ListItem.StudentItem(
                "Michael",
                "Jackson",
                "Simple text",
                false
            ),
            ListItem.StudentItem(
                "Albert",
                "Wagenbhaum",
                "Sample",
                true
            ),
            ListItem.BannerItem(
                "New request",
                "Your action"
            )
        )
    )
}
