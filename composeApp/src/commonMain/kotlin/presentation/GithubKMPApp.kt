 package presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi

 @OptIn(ExperimentalResourceApi::class, ExperimentalMaterialApi::class)
@Composable
fun App() {
    val viewModel = getViewModel(Unit, viewModelFactory { GithubKMPViewModel() })
    val uiState by viewModel.repositoryUiState.collectAsState()
    val repos = uiState.repository
    val profile = uiState.profile

    MaterialTheme {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (repos.isEmpty() && profile == null) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.padding(5.dp)
                ) {
                    LoginScreen(onClick = {
                        viewModel.getUserProfile(userName = it)
                    })

                }
            }
            
            
            AnimatedVisibility(visible = profile != null) {
                profileScreen(profile = profile, logout = {viewModel.logoutUser()})
            }

            AnimatedVisibility(visible = repos.isNotEmpty()) {
                RepositoryScreen(repositoryModelItem = repos)
            }



        }
    }

}
