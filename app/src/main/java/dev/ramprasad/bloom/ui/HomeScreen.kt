package dev.ramprasad.bloom.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import dev.ramprasad.bloom.viewmodel.HomeViewModel
import dev.ramprasad.bloom.R
import dev.ramprasad.bloom.ui.theme.BloomTheme

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.size(40.dp))
            SearchTextField()
            ThemesTitle()
            ThemesList(homeViewModel)
            PlantsTitle()
            PlantsList(homeViewModel)
        }
    }
}

@Composable
fun SearchTextField() {
    var searchKeyword by rememberSaveable {
        mutableStateOf("")
    }

    val errorOccured by rememberSaveable {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = searchKeyword,
        onValueChange = {

        },
        isError = errorOccured,
        placeholder = {
            Text(text = stringResource(id = R.string.search), color = MaterialTheme.colors.onPrimary)
        },
        leadingIcon = {
            painterResource(id = R.drawable.ic_baseline_search_24)
        },
        enabled = true,
        modifier = Modifier
            .fillMaxWidth()
            //.height(56.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            textColor = MaterialTheme.colors.onPrimary,
            focusedIndicatorColor = MaterialTheme.colors.onPrimary,
            focusedLabelColor = MaterialTheme.colors.onPrimary,
            cursorColor = MaterialTheme.colors.onPrimary
        )
    )
}

@Composable
fun ThemesTitle() {
    Text(
        text = stringResource(id = R.string.browse_themes),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .paddingFromBaseline(32.dp, 0.dp)
            .padding(16.dp, 16.dp)
    )
}

@Composable
fun ThemesList(homeViewModel : HomeViewModel) {
    //val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    //val homeViewModel = hiltNavGraphViewModel<HomeViewModel>()
    homeViewModel.loadGardenThemesList()
    val gardenThemesList by homeViewModel.gardenThemesListLiveData.observeAsState()
    gardenThemesList?.let {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(136.dp)
                .padding(16.dp, 0.dp, 0.dp, 0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Top,
            contentPadding = PaddingValues(0.dp,0.dp),
        ) {
            items(
                items = it,
                key = { gardenTheme ->
                    gardenTheme.themeId
                }
            ){ gardenTheme ->
                Card(
                    modifier = Modifier
                        .width(136.dp)
                        .fillMaxHeight(),
                    elevation = 2.dp,
                    backgroundColor = MaterialTheme.colors.surface,
                    shape = MaterialTheme.shapes.small
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        val painter = rememberCoilPainter(
                            gardenTheme.themeImageUrl,
                            fadeIn = true
                        )
                        Image(
                            painter = painter,
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(96.dp)
                        )
                        when(painter.loadState){
                            ImageLoadState.Loading -> {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                                }
                            }
                            is ImageLoadState.Error -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_baseline_error_24),
                                    contentDescription = null
                                )
                            }
                            else -> {

                            }
                        }
                        Text(
                            text = gardenTheme.themeName,
                            style = MaterialTheme.typography.h2,
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp, 8.dp, 16.dp, 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlantsTitle() {
    Text(
        text = stringResource(id = R.string.design_your_home_garden),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .paddingFromBaseline(40.dp, 0.dp)
            .padding(16.dp, 16.dp)
    )
}

@Composable
fun PlantsList(homeViewModel: HomeViewModel) {
    //val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    //val homeViewModel = hiltNavGraphViewModel<HomeViewModel>()
    homeViewModel.loadPlantsList()
    val plantsList by homeViewModel.plantsListLiveData.observeAsState()
    plantsList?.let {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Transparent)
                .padding(16.dp, 0.dp, 16.dp, 0.dp),
            contentPadding = PaddingValues(0.dp,0.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = it,
                key = { plant ->
                    plant.plantId
                }
            ){ plant ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top,
                ) {
                    val painter = rememberCoilPainter(
                        plant.plantImageUrl,
                        fadeIn = true,
                        requestBuilder = { transformations(RoundedCornersTransformation(4F,4F,4F,4F))}
                    )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp)
                    )
                    when(painter.loadState){
                        ImageLoadState.Loading -> {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(Modifier.align(Alignment.Center))
                            }
                        }
                        is ImageLoadState.Error -> {
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_error_24),
                                contentDescription = null
                            )
                        }
                        else -> {

                        }
                    }
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.98F)
                        ){
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(0.9F)
                                    .fillMaxHeight()
                                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Top
                            ) {
                                Text(
                                    text = plant.plantName,
                                    style = MaterialTheme.typography.h2,
                                    color = MaterialTheme.colors.onPrimary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .paddingFromBaseline(24.dp, 0.dp)
                                )
                                Text(
                                    text = plant.plantDescription,
                                    style = MaterialTheme.typography.body1,
                                    color = MaterialTheme.colors.onPrimary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .paddingFromBaseline(0.dp, 24.dp)
                                )
                            }
                            Checkbox(
                                checked = false,
                                onCheckedChange = {},
                                modifier = Modifier
                                    .wrapContentSize()
                                    .align(Alignment.CenterVertically)
                            )
                        }

                        Divider(modifier = Modifier
                            .fillMaxWidth(0.95F)
                            .align(Alignment.CenterHorizontally))
                    }
                }
            }
        }
    }
}

/*@Preview(
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    widthDp = 360, heightDp = 640,
    name = "BasePreview"
)*/

/*@Composable
fun PreviewLightHomeScreen() {
    BloomTheme(false){
        HomeScreen(homeViewModel)
    }
}*/

/*
@ExperimentalMaterialApi
@Preview(
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    widthDp = 360, heightDp = 640,
    name = "BasePreview"
)
@Composable
fun PreviewDarkHomeScreen() {
    BloomTheme(true) {
        HomeScreen(arrayListOf(
            GardenTheme(1, stringResource(R.string.desert_chic),"https://www.pexels.com/photo/assorted-color-flowers-2132227/")
        ), arrayListOf(
            Plant(1, stringResource(R.string.monstera),"",stringResource(R.string.plant_description))
        ))
    }
}*/
