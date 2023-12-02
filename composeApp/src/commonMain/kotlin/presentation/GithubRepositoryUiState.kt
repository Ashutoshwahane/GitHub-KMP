package presentation

import domain.model.profile.GithubProfile
import domain.model.repository.GithubRepositoryModelItem

data class GithubRepositoryUiState(
    val repository:  List<GithubRepositoryModelItem>,
    val profile: GithubProfile? = null
)