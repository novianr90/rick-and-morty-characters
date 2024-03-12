package id.novian.rickandmortycharacterlocations.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import id.novian.rickandmortycharacterlocations.data.model.Character
import id.novian.rickandmortycharacterlocations.data.model.characterSample
import id.novian.rickandmortycharacterlocations.viewmodel.CharacterViewModel

@Composable
fun CharacterListScreen(
    viewModel: CharacterViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val characters by viewModel.characters.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(characters) {character ->
            CharacterItem(character = character, onCharacterClick = {
                navController.navigate("${Screen.DetailCharacter.route}/${character.id}")
            })
        }
    })
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterItem(
    character: Character,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onCharacterClick(character.id) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(character.image),
            contentDescription = null,
            modifier = modifier.size(50.dp))
        Spacer(modifier = modifier.width(8.dp))
        Text(text = character.name)
    }
}

@Preview
@Composable
fun CharacterItemPreview(
    character: Character = characterSample,
    onCharacterClick: (Int) -> Unit = {}
) {
    CharacterItem(character = character, onCharacterClick = onCharacterClick)
}