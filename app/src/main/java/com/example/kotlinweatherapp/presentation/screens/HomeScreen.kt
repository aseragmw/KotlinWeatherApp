package com.example.kotlinweatherapp.presentation.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kotlinweatherapp.R
import com.example.kotlinweatherapp.presentation.composeables.CityWeatherItem
import com.example.kotlinweatherapp.presentation.viewmodels.WeatherViewModel

@Composable
fun HomeScreen(viewModel: WeatherViewModel, context: Context) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val weatherData = viewModel.weatherData.observeAsState()
    var cityToAdd by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                label = { Text(text = "Add City") },
                modifier = Modifier.fillMaxWidth(0.8f),
                value = cityToAdd,
                onValueChange = {
                    cityToAdd = it
                }
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color.Black)
            ) {
                IconButton(
                    onClick = {
                        if (cityToAdd.isNotBlank()) {
                            keyboardController?.hide()
                            viewModel.getWeatherItem(city = cityToAdd.capitalize(), onFailure = {
                                Log.d("Failuree", "HomeScreen: faileedddd")
                                Toast.makeText(context, "Failed To Load", Toast.LENGTH_SHORT).show()
                            })
                            cityToAdd = ""
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        tint = Color.White,
                        contentDescription = "Add City"
                    )
                }
            }

        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            weatherData.value?.let {
                itemsIndexed(it.toList()) { index, item ->
                    CityWeatherItem(entity = item, onDelete = {
                        viewModel.deleteItem(item)
                    })
                }
            }
        }
    }
}

