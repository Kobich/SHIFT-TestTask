package com.example.shift_testtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import com.example.shift_testtask.data.model.Result
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage
import com.example.shift_testtask.data.api.RandomUserApi
import com.example.shift_testtask.data.repository.UserRepository
import com.example.shift_testtask.ui.theme.SHIFTTestTaskTheme
import com.example.shift_testtask.utils.FormatPhoneNumber
import com.example.shift_testtask.viewmodel.UserUiState
import com.example.shift_testtask.viewmodel.UserViewModel
import com.example.shift_testtask.viewmodel.UserViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RandomUserApi::class.java)
        val repository = UserRepository(api)


        val viewModel: UserViewModel by viewModels { UserViewModelFactory(repository) }

        enableEdgeToEdge()
        setContent {
            SHIFTTestTaskTheme {


                SimpleUserList(viewModel)
            }
        }

    }
}

@Composable
fun SimpleUserList(viewModel: UserViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (state) {
        is UserUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                CircularProgressIndicator()
            }
        }

        is UserUiState.Success -> {
            val users = (state as UserUiState.Success).users

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
                items(users.size) { index ->
                    val user = users[index]
                    UserCard(user)
                }
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }

        is UserUiState.Error -> {
            Text(
                text = (state as UserUiState.Error).message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@Composable
fun UserCard(user: Result) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = user.picture.medium,
            modifier = Modifier.height(96.dp),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            Text(text = "${user.name.first} ${user.name.last}", style = MaterialTheme.typography.headlineSmall)
            Text(text = "${user.location.country}, ${user.location.state}, ${user.location.city}, ${user.location.street.name } ${user.location.street.number }", style = MaterialTheme.typography.labelLarge)
            Text(text = FormatPhoneNumber.format(user.phone, user.nat), style = MaterialTheme.typography.labelLarge)
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SHIFTTestTaskTheme {
        Greeting("Android")
    }
}