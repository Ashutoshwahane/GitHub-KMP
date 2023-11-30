import androidx.compose.runtime.mutableStateOf
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class GithubViewModel: ViewModel() {
    
    private val BASE_URL = "https://api.github.com"
    
    private val _repositoryUiState = MutableStateFlow(GithubRepositoryUiState(repository = emptyList()))
    val repositoryUiState = _repositoryUiState.asStateFlow()
    
    
    private val client = HttpClient() {
        install(ContentNegotiation){
            json()
        }
    }
    
    fun getUserRepository(userName: String){
        viewModelScope.launch {
            val repository = getRepository(userName = userName)
            _repositoryUiState.update {
                if (it.fakeUiUpdate){
                    it.copy(repository = emptyList(),fakeUiUpdate = false)
                }else{
                    it.copy(repository = repository,fakeUiUpdate = true)
                }
            }            
        }   
    }
    
    private suspend fun getRepository(userName: String):  List<GithubRepositoryModelItem> {
        val response = client
            .get("https://api.github.com/users/ashutoshwahane/repos")
            .body<List<GithubRepositoryModelItem>>()
        println("response : ${response}")
        return response
        
    }

    override fun onCleared() {
        client.close()
    }
    
}