package id.novian.rickandmortycharacterlocations.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import id.novian.rickandmortycharacterlocations.viewmodel.CharacterViewModel

@Composable
fun ListLocations(
    modifier: Modifier = Modifier,
    locationId: Int,
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val location by viewModel.location.collectAsState()
    val locationList by viewModel.locationList.collectAsState()

    LaunchedEffect(key1 = locationId) {
        viewModel.getLocationById(locationId)
        viewModel.getCharacterByLocationId(locationId)
    }

    Column {
        Text(
            text = location.name,
        )
        LazyColumn {
            items(locationList) {character ->
                Text(text = "${character.character}")
            }
        }
    }
}