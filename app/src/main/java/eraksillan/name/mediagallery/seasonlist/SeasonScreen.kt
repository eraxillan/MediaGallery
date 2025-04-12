package eraksillan.name.mediagallery.seasonlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eraksillan.name.mediagallery.local.utils.MediaSeasonInfo
import eraksillan.name.mediagallery.medialist.ActualSeasonCompose
import eraksillan.name.mediagallery.medialist.MediaListViewModel
import eraksillan.name.mediagallery.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.seasonScreen(navController: NavController) {
    composable<Route.MediaSeason> { backStackEntry ->
        val route: Route.MediaSeason = backStackEntry.toRoute()

        val viewModel =
            hiltViewModel<MediaListViewModel, MediaListViewModel.ViewModelFactory>(
                key = "$${route.year}_${route.season}",
                creationCallback = { it.create(navController, route.season) }
            )

        val seasonInfo = MediaSeasonInfo(route.season, route.year, route.season.titleResId)
        val toolbarText = stringResource(route.season.titleResId) + " " + route.year.toString()
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            text = toolbarText,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
            },
            modifier = Modifier
                .navigationBarsPadding()
                .imePadding(),
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    )
            ) {
                ActualSeasonCompose(viewModel, seasonInfo)
            }
        }
    }
}
