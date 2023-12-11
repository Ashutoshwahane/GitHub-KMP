package presentation.feature.profile

import cafe.adriel.voyager.core.model.ScreenModel
import data.DataResource
import data.GithubServiceImpl
import domain.profile.ProfileModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileUiState(
    val profile: DataResource<ProfileModel> = DataResource.initial()
)

class ProfileViewModel(
    private val service: GithubServiceImpl
): ScreenModel {

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState = _profileUiState.asStateFlow()

    fun getProfileData(userName: String = "ashutoshwahane") = CoroutineScope(Dispatchers.IO).launch {
        val response = getProfile(userName)
        response.let { githubProfile ->
            println("response : $response")
            _profileUiState.update {
                it.copy(profile = DataResource.success(data = githubProfile))
            }
        }
    }


    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    private suspend fun getProfile(userName: String): ProfileModel {
        return client
            .get(urlString = "https://api.github.com/users/$userName")
            .body<ProfileModel>()
    }



}