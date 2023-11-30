import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val viewModel = getViewModel(Unit, viewModelFactory { GithubViewModel() })
    val uiState by viewModel.repositoryUiState.collectAsState()
    val repos = uiState.repository

    MaterialTheme {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            ) {
                Button(onClick = {
                    viewModel.getUserRepository("ashutoshwahane")
                }, modifier = Modifier.aspectRatio(1f).weight(1f)
                    ){
                    Text("Click Me")
                }
            }

            AnimatedVisibility(visible = repos.isNotEmpty()){
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(100.dp),
                    modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ){
                    items(repos) {repo ->
                        RepositoryUI(repo)
                    }

                }
            }

        }
    }

}

@Composable
fun RepositoryUI(repositoryModelItem: GithubRepositoryModelItem){
        KamelImage(
            resource = asyncPainterResource(repositoryModelItem.owner?.avatar_url ?: ""),
            contentDescription = "Repository",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )
    }
