package presentation.feature.repository

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import di.getScreenModel

object RepositoryTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Repository"
            val icon = rememberVectorPainter(Icons.TwoTone.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    var userName = ""

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<RepositoryViewModel>()
        val uiState by viewModel.repositoryUiState.collectAsState()
        val repos = uiState.repo
        LaunchedEffect(Unit){
            viewModel.getRepositoryData(userName = userName)
        }
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Black)
        ) {
            if (repos.isSuccess()){
                RepositoryScreen(repositoryModelItem = repos)
            }
        }
    }
}