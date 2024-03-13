package id.novian.rickandmortycharacterlocations.screen

import android.util.Log
import android.widget.CheckBox
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import id.novian.rickandmortycharacterlocations.data.model.room.Location
import id.novian.rickandmortycharacterlocations.domain.CharacterDomain
import id.novian.rickandmortycharacterlocations.viewmodel.CharacterViewModel

@Composable
fun AssignCharacterLocationScreen(
    characterId: Int,
    viewModel: CharacterViewModel = hiltViewModel(),
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val locations by viewModel.locations.collectAsState()

    val selectedData = remember { mutableStateListOf<Int>() }
    Column(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        AssignLocationHeader(onClick = { navController.navigate(Screen.AddLocation.route) })
        LazyColumn {
            items(locations) {location ->
                Row(
                    modifier = modifier
                        .padding(top = 5.dp)
                        .clickable {
                            navController.navigate("${Screen.ListLocation.route}/${location.id}")
                        }
                ) {
                    Checkbox(
                        checked = selectedData.contains(location.id),
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                location.id?.let { selectedData.add(it) }
                            } else {
                                selectedData.remove(location.id)
                            }
                        },
                        modifier = modifier
                            .padding(start = 10.dp, end = 10.dp)
                    )
                    Text(
                        text = location.name,
                        fontSize = 16.sp,
                        modifier = modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
        Button(
            onClick = {
                viewModel.assignCharacterToExistingLocation(characterId, selectedData.last())
                navController.popBackStack()
            },
            modifier = modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Assign Character $characterId to this selected location")
        }
    }
}

@Composable
private fun AssignLocationHeader(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Button(onClick = onClick) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            Text(text = "Add Location")
        }
    }
}
