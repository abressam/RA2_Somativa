package com.example.ra2somativa.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ra2somativa.feature.data.model.Player
import com.example.ra2somativa.feature.di.RepositoryProvider
import com.example.ra2somativa.feature.presentation.PlayerViewModel
import com.example.ra2somativa.feature.presentation.PlayerViewModelFactory
import com.example.ra2somativa.ui.InstructionScreen
import com.example.ra2somativa.ui.QuizApp
import com.example.ra2somativa.ui.ResultScreen
import com.example.ra2somativa.ui.StartScreen
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
