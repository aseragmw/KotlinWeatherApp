package com.example.kotlinweatherapp.features.weather.presentation.screens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.kotlinweatherapp.core.MainApp
import com.example.kotlinweatherapp.core.RequestState
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity
import com.example.kotlinweatherapp.features.weather.presentation.composeables.CityWeatherItem
import com.example.kotlinweatherapp.features.weather.presentation.WeatherViewModel
import com.example.kotlinweatherapp.features.weather.presentation.composeables.getRandomColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: WeatherViewModel, context: Context) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var forceUpdate by remember {
        mutableStateOf(false)
    }
    val weatherData by viewModel.getAllWeatherItems().observeAsState()

    var cityToAdd by remember {
        mutableStateOf("")
    }
    val state by viewModel.getWeatherItemState.collectAsState()

    LaunchedEffect(state) {
        when(state){
            is RequestState.Loading,
            RequestState.Initial -> null
            is RequestState.Success-> {
                Toast.makeText(context, "Successfully loaded ${(state as RequestState.Success<WeatherEntity?>).data?.location}", Toast.LENGTH_SHORT).show()
            }
            is RequestState.Failure-> {
                Toast.makeText(context, (state as RequestState.Failure<WeatherEntity?>).error, Toast.LENGTH_SHORT).show()
            }
        }
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
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedTrailingIconColor = getRandomColor()

                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Add City"
                    )
                },
                label = { Text(text = "Add City") },
                modifier = Modifier.fillMaxWidth(0.6f),
                value = cityToAdd,
                onValueChange = {
                    cityToAdd = it
                }
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(getRandomColor())
            ) {
                IconButton(
                    onClick = {
                        keyboardController?.hide()
                        if (cityToAdd.isNotBlank()) {
                            viewModel.getWeatherItem(city = cityToAdd.capitalize())
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
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(getRandomColor())
            ) {
                IconButton(
                    onClick = {
                        viewModel.updateAllItems()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        tint = Color.White,
                        contentDescription = "Refresh"
                    )
                }
            }


        }
        if(state is RequestState.Loading){
            CircularProgressIndicator(color = Color.Black)
        }
        LazyColumn(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxSize()
        ) {
            weatherData.let {
                it?.let {
                    itemsIndexed(it.reversed().toList()) { index, item ->
                        CityWeatherItem(entity = item, onDelete = {
                            viewModel.deleteItem(item)
                        })
                    }
                }

            }
        }
    }
}

