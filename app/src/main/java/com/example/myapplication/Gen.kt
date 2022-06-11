import androidx.compose.foundation.Image
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R
import org.json.JSONObject

fun main() {
//    println(System.currentTimeMillis())
    val json = JSONObject("\"b\":0")
    println(json.getBoolean("b"))
}

@Composable
fun A() {
    IconButton(onClick = { /*TODO*/ }) {
        Image(painter = painterResource(id = R.drawable.ic_mhj), contentDescription = "")
    }
    R.drawable.ic_ask
}

@Preview
@Composable
fun D() {
    A()
}