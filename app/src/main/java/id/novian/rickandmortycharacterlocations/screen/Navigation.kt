package id.novian.rickandmortycharacterlocations.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    data object CharacterList: Screen("character_list")
    data object DetailCharacter : Screen("character_details")
    data object AssignLocations: Screen("character_assign_location")
    data object AddLocation: Screen("add_location")
    data object ListLocation: Screen("list_locations")
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
                DetailsCharacterScreen(characterId = characterId, navController = navController)
            }
        }
        composable(
            Screen.AssignLocations.route + "/{characterId}",
            arguments = listOf(
                navArgument("characterId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId")
            if (characterId != null) {
                AssignCharacterLocationScreen(characterId = characterId, navController = navController)
            }
        }
        composable(Screen.AddLocation.route) {
            AddLocationScreen(navController = navController)
        }
        composable(
            Screen.ListLocation.route + "/{locationId}",
            arguments = listOf(
                navArgument("locationId") {
                    type = NavType.IntType
                }
            )
        ) {
            val locationId = it.arguments?.getInt("locationId")
            if (locationId != null) {
                ListLocations(locationId = locationId)
            }
        }
    }
}