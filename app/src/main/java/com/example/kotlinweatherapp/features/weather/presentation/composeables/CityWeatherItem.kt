package com.example.kotlinweatherapp.features.weather.presentation.composeables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kotlinweatherapp.R
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity
@Composable
fun CityWeatherItem(entity:WeatherEntity,onDelete : ()->Unit) {
    Row (
        modifier = Modifier
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(25.dp))
            .fillMaxWidth(1f)
            .background(Color.Black)
            .padding(30.dp)
            .fillMaxHeight(0.3f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = entity.location,
                color = Color.White,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            )
            Text(
                text = entity.temp,
                color = Color.White,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ){
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = entity.iconPath
                    .replace("64x64","128x128"),
                contentDescription =null,
                )
            Text(
                text = entity.condition,
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
        IconButton(
            onClick = {
                      onDelete()
            },
            modifier = Modifier.weight(0.5f)
            ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24,),
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier.size(30.dp))
        }
    }
}