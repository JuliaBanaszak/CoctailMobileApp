package com.example.cocktailmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.cocktailmobileapp.ui.theme.CocktailMobileAppTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.activity.compose.BackHandler

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
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            LazyColumn(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()) {
                items(cocktails) { cocktail ->
                    CocktailItem(cocktailName = cocktail, onClick = { selectedCocktail = cocktail })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            if (selectedCocktail != null) {
                CocktailDetailScreen(selectedCocktail!!, onBack = { selectedCocktail = null }, modifier = Modifier.weight(2f))
            }
        }
    } else {
        Column(modifier = modifier.fillMaxSize()) {
            if (selectedCocktail == null) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(cocktails) { cocktail ->
                        CocktailItem(cocktailName = cocktail, onClick = { selectedCocktail = cocktail })
                    }
                }
            } else {
                CocktailDetailScreen(selectedCocktail!!, onBack = { selectedCocktail = null })
            }
        }
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
    val cocktailDetails = mapOf(
        "Mojito" to CocktailDetails(
            ingredients = "rum, mięta, cukier, limonka, woda gazowana",
            preparation = "1. Zgnieść miętę w szklance. 2. Dodać cukier, rum i limonkę. 3. Wlać wodę gazowaną."
        ),
        "Aperol Spritz" to CocktailDetails(
            ingredients = "Aperol, prosecco, woda gazowana, pomarańcza, lód",
            preparation = "1. Napełnić kieliszek lodem. 2. Wlać Aperol i prosecco. 3. Dodać wodę gazowaną i plasterek pomarańczy."
        ),
        "Campari Orange" to CocktailDetails(
            ingredients = "Campari, sok pomarańczowy, lód",
            preparation = "1. Wypełnić szklankę lodem. 2. Wlać Campari i sok pomarańczowy. 3. Delikatnie wymieszać."
        ),
        "Martini" to CocktailDetails(
            ingredients = "gin, wermut, oliwka",
            preparation = "1. Wlać gin i wermut do szklanki z lodem. 2. Mieszać przez chwilę. 3. Przelać do kieliszka i dodać oliwkę."
        ),
        "Espresso Martini" to CocktailDetails(
            ingredients = "wódka, likier kawowy, espresso, syrop cukrowy",
            preparation = "1. Wstrząsnąć wszystkie składniki w shakerze z lodem. 2. Przelać do schłodzonego kieliszka. 3. Udekorować ziarnami kawy."
        ),
        "Jagerbomb" to CocktailDetails(
            ingredients = "Jägermeister, napój energetyczny",
            preparation = "1. Wlać napój energetyczny do szklanki. 2. Wrzucić kieliszek z Jägermeisterem do środka i wypić."
        ),
        "Malibu" to CocktailDetails(
            ingredients = "Malibu, sok ananasowy, lód",
            preparation = "1. Wypełnić szklankę lodem. 2. Wlać Malibu i sok ananasowy. 3. Delikatnie wymieszać."
        ),
        "Moscow Mule" to CocktailDetails(
            ingredients = "wódka, piwo imbirowe, sok z limonki, lód",
            preparation = "1. Wypełnić kubek lodem. 2. Wlać wódkę, piwo imbirowe i sok z limonki. 3. Delikatnie wymieszać."
        ),
        "Mimosa" to CocktailDetails(
            ingredients = "szampan, sok pomarańczowy",
            preparation = "1. Wlać sok pomarańczowy do kieliszka. 2. Delikatnie dolać szampana."
        ),
        "Sour Apple Vodka" to CocktailDetails(
            ingredients = "wódka jabłkowa, likier kwaśne jabłko, sok jabłkowy, lód",
            preparation = "1. Wstrząsnąć wszystkie składniki w shakerze z lodem. 2. Przelać do kieliszka."
        ),
        "Pina Colada" to CocktailDetails(
            ingredients = "rum, mleczko kokosowe, sok ananasowy, lód",
            preparation = "1. Wymieszać składniki w blenderze. 2. Przelać do szklanki. 3. Udekorować plasterkiem ananasa."
        ),
        "Old Fashioned" to CocktailDetails(
            ingredients = "whiskey, cukier, angostura, woda, skórka pomarańczy",
            preparation = "1. Rozpuścić cukier w wodzie z angosturą. 2. Dodać lód i whiskey. 3. Wymieszać i udekorować skórką pomarańczy."
        ),
        "Gin and Tonic" to CocktailDetails(
            ingredients = "gin, tonik, limonka, lód",
            preparation = "1. Wypełnić szklankę lodem. 2. Wlać gin i tonik. 3. Dodać plasterek limonki."
        ),
        "Sex on the Beach" to CocktailDetails(
            ingredients = "wódka, likier brzoskwiniowy, sok pomarańczowy, sok żurawinowy, lód",
            preparation = "1. Wypełnić szklankę lodem. 2. Wlać składniki i delikatnie wymieszać."
        ),
        "Cosmopolitan" to CocktailDetails(
            ingredients = "wódka cytrynowa, likier pomarańczowy, sok żurawinowy, sok z limonki",
            preparation = "1. Wstrząsnąć wszystkie składniki w shakerze z lodem. 2. Przelać do kieliszka."
        ),
        "Blue Kamikaze" to CocktailDetails(
            ingredients = "wódka, likier blue curacao, sok z limonki",
            preparation = "1. Wstrząsnąć składniki w shakerze. 2. Przelać do kieliszka."
        ),
        "Whiskey Sour" to CocktailDetails(
            ingredients = "whiskey, sok z cytryny, syrop cukrowy, białko jajka",
            preparation = "1. Wstrząsnąć wszystkie składniki w shakerze na sucho. 2. Dodać lód i ponownie wstrząsnąć. 3. Przelać do szklanki."
        ),
        "Daiquiri" to CocktailDetails(
            ingredients = "rum, sok z limonki, syrop cukrowy",
            preparation = "1. Wstrząsnąć składniki w shakerze z lodem. 2. Przelać do kieliszka."
        ),
        "Margarita" to CocktailDetails(
            ingredients = "tequila, likier pomarańczowy, sok z limonki",
            preparation = "1. Wymieszać tequilę, likier pomarańczowy i sok z limonki w shakerze. 2. Przelać do szklanki."
        )
    )

    val details = cocktailDetails[cocktailName] ?: return

    var timeLeft by remember { mutableStateOf(60) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(onClick = onBack) {
                Text("Wróć")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = cocktailName,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Składniki: ",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(11.dp))
        Text(
            text = details.ingredients.replace(", ", "\n"),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Przygotowanie: ",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(11.dp))
        details.preparation.split("\\d+\\. ".toRegex()) // Podział na podstawie numerów kroków
            .filter { it.isNotBlank() }
            .forEachIndexed { index, step ->
                Text(
                    text = "${index + 1}. $step",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

        //miejsce minutnika
        Spacer(modifier = Modifier.height(30.dp))

        //minutnik
        val minutesLeft = timeLeft / 60
        val secondsLeft = timeLeft % 60

        Text(
            text = "%02d:%02d".format(minutesLeft, secondsLeft),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        //pola do wpisywania minut i sekund
        var inputMinutes by remember { mutableStateOf(minutesLeft.toString()) }
        var inputSeconds by remember { mutableStateOf(secondsLeft.toString()) }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = inputMinutes,
                onValueChange = { value ->
                    if (value.all { it.isDigit() }) {
                        inputMinutes = value
                        timeLeft = (inputMinutes.toIntOrNull() ?: 0) * 60 + (inputSeconds.toIntOrNull() ?: 0)
                    }
                },
                label = { Text("Minuty") },
                modifier = Modifier.width(100.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = inputSeconds,
                onValueChange = { value ->
                    if (value.all { it.isDigit() }) {
                        inputSeconds = value
                        timeLeft = (inputMinutes.toIntOrNull() ?: 0) * 60 + (inputSeconds.toIntOrNull() ?: 0)
                    }
                },
                label = { Text("Sekundy") },
                modifier = Modifier.width(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        //kontrolki minutnika
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { isRunning = true }) { Text("Start") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { isRunning = false }) { Text("Pauza") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                timeLeft = 60 //resetowanie czasu
                inputMinutes = "1" //resetowanie minut
                inputSeconds = "00" //resetowanie sekund
            }) { Text("Reset") }
        }
    }
}

data class CocktailDetails(val ingredients: String, val preparation: String)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CocktailMobileAppTheme {
        CocktailMenu()
    }
}
