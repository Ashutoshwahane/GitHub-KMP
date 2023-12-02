package data

import domain.model.profile.GithubProfile
import domain.model.repository.GithubRepositoryModelItem
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class GithubServiceImpl(private val client: HttpClient): GithubService {
    override suspend fun getGithubRepos(userName: String): List<GithubRepositoryModelItem> {

        return emptyList()
    }

    override suspend fun getGithubProfile(userName: String): GithubProfile {
        TODO("Not yet implemented")
    }
}