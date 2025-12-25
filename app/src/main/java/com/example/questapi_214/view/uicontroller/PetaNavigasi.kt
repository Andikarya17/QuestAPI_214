package com.example.questapi_214.view.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.questapi_214.view.EntrySiswaScreen
import com.example.questapi_214.view.HalamanDetail
import com.example.questapi_214.view.HalamanEdit
import com.example.questapi_214.view.HomeScreen
import com.example.questapi_214.view.route.DestinasiDetail
import com.example.questapi_214.view.route.DestinasiEdit
import com.example.questapi_214.view.route.DestinasiEntry
import com.example.questapi_214.view.route.DestinasiHome
import com.example.questapi_214.viewmodel.HomeViewModel
import com.example.questapi_214.viewmodel.provider.PenyediaViewModel

@Composable
fun DataSiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    val homeViewModel: HomeViewModel =
        viewModel(factory = PenyediaViewModel.Factory)

    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {

        // ======================
        // HOME
        // ======================
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                navigateToItemUpdate = { id ->
                    navController.navigate("${DestinasiDetail.route}/$id")
                }
            )
        }


        // ======================
        // ENTRY
        // ======================
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = {
                    navController.popBackStack()
                    homeViewModel.loadSiswa()
                }
            )
        }

        // ======================
        // DETAIL
        // ======================
        composable(
            route = DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            HalamanDetail(
                navigateBackWithRefresh = {
                    navController.popBackStack()
                    homeViewModel.loadSiswa()
                },
                navigateToEdit = { id ->
                    navController.navigate("${DestinasiEdit.route}/$id")
                }
            )
        }

        // ======================
        // EDIT  ✅ SEJAJAR, BUKAN DI DALAM DETAIL
        // ======================
        composable(
            route = DestinasiEdit.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiEdit.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            HalamanEdit(
                navigateBack = {
                    navController.popBackStack()
                    homeViewModel.loadSiswa()
                }
            )
        }
    }
}

