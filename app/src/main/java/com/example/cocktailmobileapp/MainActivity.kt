package com.example.cocktailmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalConfiguration
import kotlinx.coroutines.delay
import com.example.cocktailmobileapp.ui.theme.CocktailMobileAppTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.foundation.clickable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CocktailMobileAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CocktailMenu(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CocktailMenu(modifier: Modifier = Modifier) {
    val cocktails = listOf(
        "Mojito",
        "Aperol Spritz",
        "Campari Orange",
        "Martini",
        "Espresso Martini",
        "Jagerbomb",
        "Malibu",
        "Moscow Mule",
        "Mimosa",
        "Sour Apple Vodka",
        "Pina Colada",
        "Old Fashioned",
        "Gin and Tonic",
        "Sex on the Beach",
        "Cosmopolitan",
        "Blue Kamikaze",
        "Whiskey Sour",
        "Daiquiri",
        "Margarita"
    )

    var selectedCocktail by remember { mutableStateOf<String?>(null) }

    if (selectedCocktail == null) {
        LazyColumn(modifier = modifier) {
            items(cocktails) { cocktail -> // <-- Tutaj mamy dostęp do 'cocktails'
                CocktailItem(cocktailName = cocktail, onClick = { selectedCocktail = cocktail })
            }
        }
    } else {
        CocktailDetailScreen(selectedCocktail!!, onBack = { selectedCocktail = null })
    }
}

@Composable
fun CocktailItem(cocktailName: String, onClick: () -> Unit) {
    Text(
        text = cocktailName,
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick() }
    )
}

@Composable
fun CocktailDetailScreen(cocktailName: String, onBack: () -> Unit, modifier: Modifier = Modifier) {
    // Przykładowe dane dotyczące koktajli (składniki i sposób przygotowania)
    val cocktailDetails = mapOf(
        "Mojito" to CocktailDetails(
            ingredients = "Rum, mięta, cukier, limonka, woda gazowana",
            preparation = "1. Zgnieść miętę w szklance. 2. Dodać cukier, rum i limonkę. 3. Wlać wodę gazowaną."
        ),
        "Margarita" to CocktailDetails(
            ingredients = "Tequila, likier pomarańczowy, sok z limonki",
            preparation = "1. Wymieszać tequila, likier pomarańczowy i sok z limonki w shakerze. 2. Przelać do szklanki."
        ),

    )

    val details = cocktailDetails[cocktailName] ?: return

    var timeLeft by remember { mutableStateOf(60) }
    var isRunning by remember { mutableStateOf(false) }
    var inputTime by remember { mutableStateOf("60") }

    LaunchedEffect(isRunning) {
        while (isRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    Column(modifier = modifier.padding(16.dp)) {
        // Tytuł koktajlu
        Text(text = cocktailName, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        // Składniki w osobnych liniach
        Text(
            text = "Składniki:\n${details.ingredients.replace(", ", "\n")}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Przygotowanie w osobnych liniach
        Text(
            text = "Przygotowanie:\n${details.preparation.replace(". ", ".\n")}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Minutnik
        Text(text = "Czas: $timeLeft sek.")
        Row(modifier = Modifier.padding(top = 16.dp)) {
            Button(onClick = { isRunning = true }) {
                Text("Start")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { isRunning = false }) {
                Text("Pauza")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { timeLeft = inputTime.toIntOrNull() ?: 60; isRunning = false }) {
                Text("Reset")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text("Wróć")
        }
    }
}

//model danych dla koktaili
data class CocktailDetails(val ingredients: String, val preparation: String)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CocktailMobileAppTheme {
        CocktailMenu()
    }
}