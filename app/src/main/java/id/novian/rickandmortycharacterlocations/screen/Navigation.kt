package id.novian.rickandmortycharacterlocations.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.novian.rickandmortycharacterlocations.data.model.Character
import id.novian.rickandmortycharacterlocations.viewmodel.CharacterViewModel

sealed class Screen(val route: String) {
    data object CharacterList: Screen("character_list")
    data object DetailCharacter : Screen("character_details")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String = Screen.CharacterList.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.CharacterList.route) {
            CharacterListScreen(navController = navController)
        }
        composable(
            Screen.DetailCharacter.route + "/{characterId}",
            arguments = listOf(
                navArgument("characterId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId")
            if (characterId != null) {
                DetailsCharacterScreen(characterId = characterId)
            }
        }
    }
}