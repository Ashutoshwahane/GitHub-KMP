package data

import domain.model.profile.GithubProfile
import domain.model.repository.GithubRepositoryModelItem
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

interface GithubService {
    suspend fun getGithubRepos(userName: String) : List<GithubRepositoryModelItem>
    suspend fun getGithubProfile(userName: String) : GithubProfile


    companion object {
        fun create(): GithubService {
            return GithubServiceImpl (
                client = HttpClient() {
                    install(ContentNegotiation) {
                        json()
                    }
                }
            )
        }
    }
}