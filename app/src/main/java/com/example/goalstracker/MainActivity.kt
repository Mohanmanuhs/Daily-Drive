package com.example.goalstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.goalstracker.ui.theme.GoalsTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoalsTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    Greeting()
                }
            }
        }
    }
}
val ReorderItem = listOf(
    "Item 1",
    "Item 2",
    "Item 3",
    "Item 4",
    "Item 5",
    "Item 6",
    "Item 7",
    "Item 8",
    "Item 9",
    "Item 10",
    "Item 11",
    "Item 12",
    "Item 13",
    "Item 14",
    "Item 15",
    "Item 16",
    "Item 17",
    "Item 18",
    "Item 19",
    "Item 20"
).toMutableStateList()
@Composable
fun Greeting() {

    DragDropList(
        items = ReorderItem,
        onMove = { fromIndex, toIndex -> ReorderItem.move(fromIndex, toIndex)}
    )

//    LazyColumn {
//        items(10) {
//            Box(modifier = Modifier
//                .height(40.dp)
//                .padding(3.dp)
//                .shadow(elevation = 2.dp)){
//                Row(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(start = 10.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(text = "Task Name")
//                    Spacer(modifier = Modifier.width(40.dp))
//                    Text(text = "DeadLine", textAlign = TextAlign.End)
//                }
//            }
//        }
//    }



}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        Greeting()
    }
}