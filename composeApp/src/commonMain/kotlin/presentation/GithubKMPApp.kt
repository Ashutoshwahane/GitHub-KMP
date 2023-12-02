package presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import domain.model.profile.GithubProfile
import domain.model.repository.GithubRepositoryModelItem
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val viewModel = getViewModel(Unit, viewModelFactory { GithubViewModel() })
    val uiState by viewModel.repositoryUiState.collectAsState()
    val repos = uiState.repository
    val profile = uiState.profile

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top
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

@Composable
fun profileScreen( profile: GithubProfile?, logout: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DisplayImage(
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
                .width(100.dp).height(100.dp),
            img = profile?.avatar_url ?: "")

        Spacer(modifier = Modifier.padding(6.dp))
        CustomTextH1(text = profile?.name, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.padding(6.dp))
        CustomTextH2(text = "@${profile?.login}", modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.padding(6.dp))
        CustomTextH2(text = profile?.bio, modifier = Modifier.fillMaxWidth(), background = true)
        Spacer(modifier = Modifier.padding(6.dp))
        Button(onClick = { logout.invoke()}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)) {
            Text(
                text = "Logout",
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.padding(6.dp))
    }
}

@Composable
fun RepositoryScreen(repositoryModelItem: List<GithubRepositoryModelItem>){
    println("RepositoryScreen Recompose")
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray),
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
        CustomTextH5(text = repositoryModelItem.description, modifier = Modifier.padding(2.dp))
    }
}

@Composable
fun LoginScreen(onClick: (String) -> Unit) {
    var userName by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userName,
            label = { Text(text = "Enter GitHub UserName") },
            onValueChange = {
                userName = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )

        Button(
            onClick = {
                onClick.invoke(userName)
                focusManager.clearFocus()
            },
            enabled = userName.isNotEmpty()
        ) {
            Text(
                text = "Continue", modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CustomTextH1(text: String?, modifier: Modifier) {
    Text(
        text = text ?: "",
        modifier = modifier,
        color = Color.White,
        fontSize = 26.sp,
        textAlign = TextAlign.Center,
        style = TextStyle(
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
        )
    )
}

@Composable
fun CustomTextH2(text: String?, modifier: Modifier, background: Boolean = false) {
    Text(
        text = text ?: "",
        modifier = modifier,
        color = if (background) Color.Gray else Color.White,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        maxLines = 4,
        style = TextStyle(
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
        )
    )
}

@Composable
fun CustomTextH3(text: String?, modifier: Modifier = Modifier, background: Boolean = false) {
    Text(
        text = text ?: "",
        modifier = modifier,
        color = if (background) Color.Gray else Color.White,
        fontSize = 16.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 4,
        style = TextStyle(fontFamily = FontFamily.SansSerif)
    )
}

@Composable
fun CustomTextH4(text: String?, modifier: Modifier = Modifier, background: Boolean = false) {
    Text(
        text = text ?: "",
        modifier = modifier,
        color = Color(0xFF68BBE3),
        fontSize = 16.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 4,
        style = TextStyle(fontFamily = FontFamily.Monospace)
    )
}

@Composable
fun CustomTextH5(text: String?, modifier: Modifier = Modifier, background: Boolean = false) {
    Text(
        text = text ?: "",
        modifier = modifier,
        color = Color.White,
        fontSize = 14.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 4,
        style = TextStyle(fontFamily = FontFamily.SansSerif)
    )
}



@Composable
fun DisplayImage(img: String, modifier: Modifier) {
    KamelImage(
        resource = asyncPainterResource(img),
        contentDescription = "Repository",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}
