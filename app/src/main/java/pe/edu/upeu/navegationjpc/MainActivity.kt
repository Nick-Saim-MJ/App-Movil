package pe.edu.upeu.navegationjpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pe.edu.upeu.navegationjpc.ui.theme.LightColorScheme
import pe.edu.upeu.navegationjpc.ui.theme.NavegationJPCTheme
import pe.edu.upeu.navegationjpc.ui.theme.ThemeType
import pe.edu.upeu.navegationjpc.ui.theme.presentation.component.MyAppDrawer
import pe.edu.upeu.navegationjpc.ui.theme.sGreendarkScheme
import pe.edu.upeu.navegationjpc.ui.theme.sGreenlightScheme
import pe.edu.upeu.navegationjpc.ui.theme.sReddarkScheme
import pe.edu.upeu.navegationjpc.ui.theme.sRedlightScheme
import pe.edu.upeu.navegationjpc.utils.conttexto
import pe.edu.upeu.navegationjpc.utils.isNight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeType = remember {
                mutableStateOf(ThemeType.RED)
            }
            val darkThemex = isNight()
            val darkTheme = remember {
                mutableStateOf(darkThemex)
            }
            conttexto.CONTEXTO_APPX = this
            val colorScheme = when (themeType.value) {
                ThemeType.GREEN->{
                    if (darkTheme.value)
                    sReddarkScheme
                    else
                    sRedlightScheme}

                ThemeType.RED -> {
                    if (darkTheme.value)
                        sGreendarkScheme
                    else
                        sGreenlightScheme
                }
                else -> {
                    LightColorScheme
                }
            }
            NavegationJPCTheme(colorScheme = colorScheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MyAppDrawer(darkMode = darkTheme, themeType = themeType)
                    //Greeting("Android")
                }
            }

        }
    }
}
/*    @Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavegationJPCTheme {
        Greeting("Android")
    }
}*/