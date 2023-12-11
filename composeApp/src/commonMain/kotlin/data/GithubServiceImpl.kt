package data

import domain.model.repository.GithubRepositoryModelItem
import domain.profile.ProfileModel
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.get

class GithubServiceImpl(private val client: HttpClient) : GithubService {
    override suspend fun getGithubRepos(userName: String): DataResource<List<GithubRepositoryModelItem>> {
        return try {
            val url = "${APIRoutes.BASE_URL}/user/$userName/repos"
            val response = client.get(url).body<List<GithubRepositoryModelItem>>()
            DataResource.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            DataResource.error(e.cause)
        }
    }

    override suspend fun getGithubProfile(userName: String): ProfileModel {
        return client.get(urlString = "https://api.github.com/user/ashutoshwahane").body<ProfileModel>()

        /*return try {
            val response = client.get(urlString = "https://api.github.com/user/ashutoshwahane").body<ProfileModel>()
            DataResource.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            DataResource.error(e.cause)
        }*/
    }
}