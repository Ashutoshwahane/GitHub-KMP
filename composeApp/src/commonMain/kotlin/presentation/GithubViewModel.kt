package presentation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.model.profile.GithubProfile
import domain.model.repository.GithubRepositoryModelItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GithubViewModel : ViewModel() {

    private val _repositoryUiState =
        MutableStateFlow(GithubRepositoryUiState(repository = emptyList(), profile = null))
    val repositoryUiState = _repositoryUiState.asStateFlow()


    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    private fun getUserRepository(userName: String) {
        println("getUserRepository")
        viewModelScope.launch {
            val repository = getRepository(userName = userName)
            _repositoryUiState.update {
                it.copy(repository = repository)
            }
        }
    }


    fun getUserProfile(userName: String) {
        viewModelScope.launch {
            val profile = getProfile(userName = userName)
            println("response : ${profile.login} ${profile.bio?.length}")
            _repositoryUiState.update {
                it.copy(profile = profile)
            }

            getUserRepository(userName)
        }
    }

    fun logoutUser() {
        _repositoryUiState.update {
            it.copy(profile = null, repository = emptyList())
        }
    }

    private suspend fun getRepository(userName: String): List<GithubRepositoryModelItem> {
        val response = client
            .get("https://api.github.com/users/$userName/repos")
            .body<List<GithubRepositoryModelItem>>()
        println("response : $response")
        return response
    }

    private suspend fun getProfile(userName: String): GithubProfile {
        return client
            .get(urlString = "https://api.github.com/users/$userName")
            .body<GithubProfile>()
    }

    override fun onCleared() {
        client.close()
    }

}