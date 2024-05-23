package com.example.contect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.contect.ui.theme.ContectTheme
import java.util.jar.Attributes.Name

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

}

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    var name by remember {
        mutableStateOf("")
    }
    var number by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    Column {
        OutlinedTextField(value = name, onValueChange = { name = it })
        OutlinedTextField(value = number, onValueChange = { number = it })
        OutlinedTextField(value = email, onValueChange = { email= it })
        Button(onClick = { /*TODO*/ }) {
            Text(text = "등록")
        }
        Text(text = "이름: $name")
        Text(text = "연락처: $number")
        Text(text = "이메일: $email")
    }
}


