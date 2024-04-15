package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository.heroes
import com.example.superheroes.ui.theme.SuperHeroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroApp()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroApp(
    modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            HeroesTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(start=16.dp,end=16.dp)) {
            items(heroes) {
                HeroItem(
                    hero = it
                )
            }
        }
    }

}

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier) {

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            HeroInformation(
                heroNameId = hero.nameResourceId,
                heroDescriptionId = hero.descriptionResourceId
            )
            Spacer(modifier = Modifier.weight(1f))
            HeroProfileImage(
                heroImageId = hero.imageResourceId)
        }
    }
}

@Composable
fun HeroInformation(
    @StringRes heroNameId: Int,
    @StringRes heroDescriptionId: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier
        .padding(end = 16.dp)
        .width(230.dp)) {
        Text(
            text = stringResource(heroNameId),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
        )
        Text(
            text = stringResource(heroDescriptionId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun HeroProfileImage(
    @DrawableRes heroImageId: Int,
    modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .clip(RoundedCornerShape(8.dp)),
        painter = painterResource(heroImageId),
        contentDescription = null
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesTopAppBar(modifier: Modifier = Modifier) {

    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperHeroesTheme {
        HeroApp()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewDarkTheme() {
    SuperHeroesTheme(
        darkTheme = true
    ){
        HeroApp()
    }
}