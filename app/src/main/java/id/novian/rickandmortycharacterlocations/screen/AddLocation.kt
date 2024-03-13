package id.novian.rickandmortycharacterlocations.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.novian.rickandmortycharacterlocations.viewmodel.CharacterViewModel

@Composable
fun AddLocationScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = hiltViewModel(),
    navController: NavHostController
) {
    var locationName by remember { mutableStateOf("") }
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Add New Location",
            fontSize = 24.sp,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
        TextField(
            value = locationName,
            onValueChange = { locationName = it },
            label = { Text(text = "Input new location") },
            modifier = modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = {
                viewModel.createNewLocation(locationName)
                navController.popBackStack()
            },
            modifier = modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            Text(text = "Save")
        }
    }
}
