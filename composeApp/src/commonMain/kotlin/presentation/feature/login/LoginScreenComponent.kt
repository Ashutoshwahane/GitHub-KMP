package presentation.feature.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.DataResource
import domain.profile.ProfileModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.component.CustomTextH1

@OptIn(ExperimentalResourceApi::class)
@Composable
fun loginScreen(profile: DataResource<ProfileModel>, onClick: (String) -> Unit) {

    var userName by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Spacer(modifier = Modifier.padding(top = 50.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            painterResource("compose-multiplatform.xml"),
            null
        )

        CustomTextH1(
            text = "GITHUB KMP",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            fontFamily = FontFamily.Monospace,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.width(4.dp))


        Spacer(modifier = Modifier.width(10.dp))

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
                println("response : Button")

            },
            enabled = userName.isNotEmpty()
        ) {
            Text(
                text = "Continue", modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }


        AnimatedVisibility(profile.isError()){
            CustomTextH1(
                text = "Something went wrong!",
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                fontFamily = FontFamily.Monospace,
                fontSize = 32.sp
            )
        }

        AnimatedVisibility(profile.isLoading()){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}
