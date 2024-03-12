package id.novian.rickandmortycharacterlocations.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.novian.rickandmortycharacterlocations.data.model.Character





@Composable
private fun LocationItem(
    location: String,
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = location,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = modifier.height(8.dp))
        LazyRow {
            items(characters) { item ->
                CharacterItem(character = item, onCharacterClick = onCharacterClick)
            }
        }
    }
}