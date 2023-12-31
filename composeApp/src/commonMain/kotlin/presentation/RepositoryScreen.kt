package presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import domain.model.repository.GithubRepositoryModelItem
import presentation.uicomponent.CustomTextH2
import presentation.uicomponent.CustomTextH3
import presentation.uicomponent.CustomTextH4
import presentation.uicomponent.CustomTextH5

@Composable
fun RepositoryScreen(repositoryModelItem: List<GithubRepositoryModelItem>){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.5.dp, color = Color.White, shape = RectangleShape)
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Top
    ){
        Box(modifier = Modifier.fillMaxWidth()) {
            LazyColumn {
                items(repositoryModelItem) { repos ->
                    RepositoryItem(repos)
                    Divider(color = Color.White, thickness = 1.dp)
                }

            }
        }
    }
}

@Composable
fun RepositoryItem(repositoryModelItem: GithubRepositoryModelItem){
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(5.dp)
    ) {
        Row {
            CustomTextH4(text = repositoryModelItem.name, modifier = Modifier.padding(2.dp))
        }
        CustomTextH3(text = repositoryModelItem.description, modifier = Modifier.fillMaxWidth())
    }
}
