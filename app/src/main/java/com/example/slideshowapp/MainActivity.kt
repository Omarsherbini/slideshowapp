package com.example.slideshowapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme()) {
                SlideshowScreen()
            }
        }
    }
}

@Composable
fun SlideshowScreen() {
    val images = listOf(
        Pair(R.drawable.image1, "cristiano in all times 1"),
        Pair(R.drawable.image2, "Best in the world  2"),
        Pair(R.drawable.image3, "CR7 IN MANU  3"),
        Pair(R.drawable.image4, "CR7 IS THE GOAT 4"),
        Pair(R.drawable.image5, "RONALDO JR 5")
    )

    var currentIndex by remember { mutableStateOf(0) }
    var inputText by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color(0xFF121212)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Slideshow App",
            fontSize = 28.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 20.dp)
        )


        Image(
            painter = painterResource(id = images[currentIndex].first),
            contentDescription = "Displayed Image",
            modifier = Modifier
                .size(350.dp)
                .border(4.dp, Color.White)
        )

        Text(
            text = images[currentIndex].second,
            fontSize = 22.sp,
            color = Color.White,
            modifier = Modifier.padding(12.dp)
        )

        // Number Input Row
        Row(
            modifier = Modifier
                .padding(12.dp)
                .background(Color.White, MaterialTheme.shapes.medium)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = inputText,
                onValueChange = { inputText = it },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    val index = inputText.text.toIntOrNull()?.minus(1)
                    if (index != null && index in images.indices) {
                        currentIndex = index
                    } else {
                        Toast.makeText(context, "Enter a number between 1 and ${images.size}", Toast.LENGTH_SHORT).show()
                    }
                }),
                modifier = Modifier
                    .width(80.dp)
                    .background(Color.LightGray, MaterialTheme.shapes.small)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    val index = inputText.text.toIntOrNull()?.minus(1)
                    if (index != null && index in images.indices) {
                        currentIndex = index
                    } else {
                        Toast.makeText(context, "Enter a number between 1 and ${images.size}", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5)),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Go", fontSize = 18.sp)
            }
        }

        // Navigation Buttons
        Row(modifier = Modifier.padding(12.dp)) {
            Button(
                onClick = { currentIndex = (currentIndex - 1 + images.size) % images.size },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)),
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Back", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                onClick = { currentIndex = (currentIndex + 1) % images.size },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Next", fontSize = 18.sp)
            }
        }
    }
}
