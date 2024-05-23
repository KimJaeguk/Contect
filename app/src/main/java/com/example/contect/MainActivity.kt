package com.example.contect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.contect.ui.theme.ContectTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContectTheme {
                MainScreen()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreen() {
        val context = LocalContext.current
        val db = remember {
            AppDatabase.getDatabase(context)
        }
        Column {
            var name by remember { mutableStateOf("") }
            var phoneNumber by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            OutlinedTextField(value = name, onValueChange = { name = it })
            OutlinedTextField(value = phoneNumber, onValueChange = { phoneNumber = it })
            OutlinedTextField(value = email, onValueChange = { email = it })

            val rememberCoroutineScope = rememberCoroutineScope()
            Button(onClick = {
                rememberCoroutineScope.launch(Dispatchers.IO) {
                    db.userDao().insertAll(
                        User(
                            username = name,
                            phoneNumber = phoneNumber,
                            email = email
                        )
                    )
                }

            }) {
                Text(text = "등록")
            }
            Column {
                val userlist by db.userDao().getAll().collectAsState(initial = emptyList())
                userlist.forEach {
                    Text(text = "이름: ${it.username}")
                    Text(text = "연락처: ${it.phoneNumber}")
                    Text(text = "이메일: ${it.email}")
                }
                for (user in userlist) {
                Text(text = "이름: ${user.username}")
                Text(text = "연락처: ${user.phoneNumber}")
                Text(text = "이메일: ${user.email}")
            }
            }
        }
    }
}
