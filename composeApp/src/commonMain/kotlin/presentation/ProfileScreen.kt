package presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import domain.model.profile.GithubProfile
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import presentation.uicomponent.CustomTextH1
import presentation.uicomponent.CustomTextH2

@Composable
fun profileScreen(profile: GithubProfile?, logout: () -> Unit) {
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            CustomTextH2(text = " 🌟 Repository 👇🏻", modifier = Modifier)
            Button(onClick = { logout.invoke()}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)) {
                Text(
                    text = "Logout",
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.padding(6.dp))
    }
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
