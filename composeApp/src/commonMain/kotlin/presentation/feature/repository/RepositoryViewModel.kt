package presentation.feature.repository

import cafe.adriel.voyager.core.model.ScreenModel
import data.DataResource
import data.GithubServiceImpl
import domain.model.repository.GithubRepositoryModelItem
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


data class RepositoryUiState(
    val repo: DataResource<List<GithubRepositoryModelItem>> = DataResource.initial()
)

class RepositoryViewModel(
    private val service: GithubServiceImpl
): ScreenModel {

    private val _repositoryUiState = MutableStateFlow(RepositoryUiState())
    val repositoryUiState = _repositoryUiState.asStateFlow()

    fun getRepositoryData(userName: String = "ashutoshwahane") = CoroutineScope(Dispatchers.IO).launch {
        val response = getRepos(userName)
        response.let { repo ->
            println("response : $response")
            _repositoryUiState.update {
                it.copy(repo = DataResource.success(data = repo))
            }
        }
    }


    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    private suspend fun getRepos(userName: String): List<GithubRepositoryModelItem> {
        return client
            .get(urlString = "https://api.github.com/users/$userName/repos")
            .body<List<GithubRepositoryModelItem>>()
    }


}