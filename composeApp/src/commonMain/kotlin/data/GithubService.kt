package data

import domain.model.repository.GithubRepositoryModelItem
import domain.profile.ProfileModel

interface GithubService {
    suspend fun getGithubRepos(userName: String) : DataResource<List<GithubRepositoryModelItem>>
    suspend fun getGithubProfile(userName: String) : ProfileModel
}