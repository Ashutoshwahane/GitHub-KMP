import kotlinx.serialization.Serializable

@Serializable
class GithubRepositoryModel(
    val repositoryModel: List<GithubRepositoryModelItem>? = null
)