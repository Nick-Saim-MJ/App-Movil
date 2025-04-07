package pe.edu.upeu.navegationjpc.ui.theme.presentation.screens

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import pe.edu.upeu.navegationjpc.R

@Composable
fun RingtoneScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val radioOptions = listOf("None", "Nokia", "Sony", "Galaxy", "Primavera", "Night")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    // MediaPlayer como estado para evitar que se recree en cada recomposición
    val mediaPlayer = remember { mutableStateOf<MediaPlayer?>(null) }

    Column(modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)

                            // Liberar el anterior si existe
                            mediaPlayer.value?.release()

                            // Si no es "None", reproducir el nuevo sonido
                            if (text != "None") {
                                val resId = when (text) {

                                    "Nokia" -> R.raw.nokia
                                    "Sony" -> R.raw.sony
                                    "Galaxy" -> R.raw.galaxy
                                    "Primavera" -> R.raw.primavera
                                    "Nigth" -> R.raw.night


                                    else -> 0
                                }

                                if (resId != 0) {
                                    mediaPlayer.value = MediaPlayer.create(context, resId).apply {
                                        setOnCompletionListener {
                                            release()
                                            mediaPlayer.value = null
                                        }
                                        start()
                                    }
                                }
                            }
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // para accesibilidad
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
