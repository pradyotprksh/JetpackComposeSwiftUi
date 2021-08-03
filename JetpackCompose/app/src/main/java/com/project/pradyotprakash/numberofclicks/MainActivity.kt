package com.project.pradyotprakash.numberofclicks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.project.pradyotprakash.numberofclicks.ui.theme.NumberOfClicksTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue

/// A view model which will be used to update the UI
class TapViewModel : ViewModel() {
    private val _taps = MutableLiveData(0)
    val taps: LiveData<Int> = _taps

    // A method which will be called to update the tap value
    fun onTap() {
        _taps.value = _taps.value?.plus(1)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberOfClicksTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(tapViewModel: TapViewModel = viewModel()) {
    val taps: Int by tapViewModel.taps.observeAsState(0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jetpack Compose Demo Home Page") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = Color.White
            )
        },
        floatingActionButton = {
            AddButton(
                onTap = {
                    tapViewModel.onTap()
                }
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    "You have tapped the button this many times:",
                    textAlign = TextAlign.Center
                )
                Taps(taps = taps)
            }
        },
    )
}

@Composable
fun AddButton(onTap: () -> Unit) {
    FloatingActionButton(
        onClick = onTap,
    ) {
        Icon(Icons.Filled.Add, "")
    }
}

@Composable
fun Taps(taps: Int) {
    Text(
        text = "$taps",
        style = MaterialTheme.typography.h1
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NumberOfClicksTheme {
        Taps(0)
    }
}