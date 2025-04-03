package pe.edu.upeu.navegationjpc.ui.theme.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.edu.upeu.navegationjpc.modelo.RetrofitClient
import pe.edu.upeu.navegationjpc.modelo.User
import pe.edu.upeu.navegationjpc.utils.conttexto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "Profile Screen",
                style = MaterialTheme.typography.headlineMedium
            )
            MyApp(context = conttexto.CONTEXTO_APPX)
        }
    }
}

@Composable
fun UserList(users: List<User>, context: Context) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(users) { item ->
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.medium,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "N: ${item.name}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "U: ${item.username}",
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(onClick = {
                        Toast.makeText(
                            context,
                            "${item.name} DMP..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Text(text = "A")
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(context: Context) {
    var users by remember { mutableStateOf<List<User>?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        RetrofitClient.apiService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    users = response.body()
                } else {
                    errorMessage = "Error en la respuesta del servidor"
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                errorMessage = "Error en la solicitud: ${t.message}"
            }
        })
    }

    when {
        errorMessage != null -> BasicText(text = errorMessage!!)
        users != null -> UserList(users = users!!, context = context)
        else -> CircularProgressIndicator()
    }
}
