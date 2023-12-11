package di

import data.GithubServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val httpClient = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}


val repository = module {
    single<GithubServiceImpl> { GithubServiceImpl(get()) }
}