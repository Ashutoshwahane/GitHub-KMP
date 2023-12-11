package presentation.feature.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import di.getScreenModel
import presentation.feature.DetailScreen

class LoginScreen : Screen {

    @Composable
    override fun Content() {

        val viewModel = getScreenModel<LoginViewModel>()
        val profile = viewModel.profileUiState.collectAsState().value.profile
        val currentNav = LocalNavigator.currentOrThrow

        if (profile.isSuccess() && profile.data?.login != null){
            currentNav.push(item = DetailScreen(profile.data.login ?: ""))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            loginScreen(
                profile = profile,
                onClick = {
                    viewModel.getProfileData(it)
                },
            )
        }
    }

}