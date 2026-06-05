package br.com.quiztourapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.quiztourapp.feature.data.model.Player
import br.com.quiztourapp.feature.di.RepositoryProvider
import br.com.quiztourapp.feature.presentation.PlayerViewModel
import br.com.quiztourapp.feature.presentation.PlayerViewModelFactory
import br.com.quiztourapp.ui.InstructionScreen
import br.com.quiztourapp.ui.QuizApp
import br.com.quiztourapp.ui.ResultScreen
import br.com.quiztourapp.ui.StartScreen
import com.google.gson.Gson

@Composable
fun Navigation(navController: NavHostController) {
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "start_screen") {
        composable("start_screen") {
            val playerViewModel: PlayerViewModel = viewModel(
                factory = PlayerViewModelFactory(RepositoryProvider.providePlayerRepository(context))
            )
            StartScreen(navController, playerViewModel) { player ->
                val playerJson = Gson().toJson(player)
                navController.navigate("quiz_screen/$playerJson")
            }
        }
        composable(
            route = "quiz_screen/{player}",
            arguments = listOf(navArgument("player") { type = NavType.StringType })
        ) { backStackEntry ->
            val playerJson = backStackEntry.arguments?.getString("player") ?: ""
            val player = Gson().fromJson(playerJson, Player::class.java)
            val playerViewModel: PlayerViewModel = viewModel(
                factory = PlayerViewModelFactory(RepositoryProvider.providePlayerRepository(context))
            )
            QuizApp(player, playerViewModel, navController)
        }
        composable("instruction_screen") {
            InstructionScreen(navController)
        }

        composable("result_screen") {
            val playerViewModel: PlayerViewModel = viewModel(
                factory = PlayerViewModelFactory(RepositoryProvider.providePlayerRepository(context))
            )
            ResultScreen(navController, playerViewModel, "")
        }
    }
}
