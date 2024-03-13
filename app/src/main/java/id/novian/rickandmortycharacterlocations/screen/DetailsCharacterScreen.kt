package id.novian.rickandmortycharacterlocations.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import id.novian.rickandmortycharacterlocations.domain.CharacterDomain
import id.novian.rickandmortycharacterlocations.domain.characterSample
import id.novian.rickandmortycharacterlocations.viewmodel.CharacterViewModel

@Composable
fun DetailsCharacterScreen(
    modifier: Modifier = Modifier,
    characterId: Int,
    viewModel: CharacterViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val character by viewModel.character.collectAsState()

    LaunchedEffect(key1 = characterId) {
        viewModel.fetchCharacterById(characterId)
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        CharacterHeader(character = character, imageSize = 250, nameSize = 36)
        Spacer(modifier = modifier.height(8.dp))
        CharacterDetails(character = character)
        Spacer(modifier = modifier.height(16.dp))
        CharacterFooter(character = characterId, {
            navController.navigate("${Screen.AssignLocations.route}/$characterId")
        })
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun CharacterHeader(
    character: CharacterDomain,
    imageSize: Int,
    modifier: Modifier = Modifier,
    nameSize: Int
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(character.image),
            contentDescription = null,
            modifier = modifier
                .size(imageSize.dp)
                .clip(RoundedCornerShape(15.dp))
                .align(Alignment.CenterHorizontally)
        )
        character.name?.let {
            Text(
                text = it,
                fontWeight = FontWeight.Bold,
                fontSize = nameSize.sp,
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
            )
        }
    }
}

@Composable
private fun CharacterDetails(
    character: CharacterDomain,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 5.dp, start = 12.dp, bottom = 10.dp, end = 12.dp)
    ) {
        character.status?.let { CharacterProperty(label = "Status", value = it) }
        character.species?.let { CharacterProperty(label = "Species", value = it) }
        character.gender?.let { CharacterProperty(label = "Gender", value = it) }
        character.origin?.let { CharacterProperty(label = "Origin", value = it) }
    }
}

@Composable
private fun CharacterProperty(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column {
        Divider(modifier = modifier.padding(top = 4.dp, bottom = 4.dp))
        Text(
            text = label,
            modifier = modifier.height(20.dp),
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier
                .padding(4.dp)
        )
    }
}

@Composable
private fun CharacterFooter(
    character: Int,
    onAssignLocation: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = { onAssignLocation(character) }
        ) {
            Text(text = "Assign Location")
        }
    }
}