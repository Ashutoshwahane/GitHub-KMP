package di

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.getKoin
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import presentation.feature.login.LoginViewModel
import presentation.feature.profile.ProfileTab
import presentation.feature.profile.ProfileViewModel
import presentation.feature.repository.RepositoryViewModel

val viewmodels = module {

    factory {
        ProfileViewModel(get())
    }

    factory {
        RepositoryViewModel(get())
    }

    factory {
        LoginViewModel(get())
    }

}

 @Composable
 inline fun <reified T : ScreenModel> Screen.getScreenModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T {
    val koin = getKoin()
    return rememberScreenModel(tag = qualifier?.value) { koin.get(qualifier, parameters) }
}