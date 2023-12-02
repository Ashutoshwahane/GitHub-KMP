package data

object APIRoutes {

    private const val BASE_URL = "https://api.github.com"

    const val GET_REPOSITORY = "$BASE_URL/user/{username}/repos"

    const val GET_PROFILE = "$BASE_URL/user/{username}"

}