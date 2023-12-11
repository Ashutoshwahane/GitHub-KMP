package presentation.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import presentation.feature.profile.ProfileTab
import presentation.feature.repository.RepositoryTab

class DetailScreen(private val userName: String) : Screen {

    @Composable
    override fun Content() {
        MaterialTheme {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TabNavigator(ProfileTab){
                    Scaffold(
                        bottomBar = {
                            BottomNavigation {
                                TabNavigationItem(RepositoryTab).also { RepositoryTab.userName = userName }
                                TabNavigationItem(ProfileTab).also { ProfileTab.userName = userName }
                            }
                        },
                        content = {
                            CurrentTab()
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {},
                                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                                modifier = Modifier,
                                shape = CircleShape,
                                backgroundColor = Color.DarkGray,
                                content = {
                                    Text("X", fontWeight = FontWeight.Bold)
                                },
                            )
                        }
                    )
                }
            }

        }
    }

}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { tab.options.icon?.let { Icon(painter = it, contentDescription = tab.options.title) } },
        selectedContentColor = Color.Black,
        unselectedContentColor = Color.LightGray,
        modifier = Modifier.background(color = Color.White),

        )
}