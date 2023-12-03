package presentation.uicomponent

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun CustomTextH1(
    text: String?, modifier: Modifier,
    color: Color = Color.White,
    fontFamily: FontFamily = FontFamily.SansSerif,
    fontSize: TextUnit = 26.sp
    ) {
    Text(
        text = text ?: "",
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        textAlign = TextAlign.Center,
        style = TextStyle(
            color = Color.Black,
            fontFamily = fontFamily,
        )
    )
}

@Composable
fun CustomTextH2(text: String?, modifier: Modifier, background: Boolean = false) {
    Text(
        text = text ?: "",
        modifier = modifier,
        color = if (background) Color.Gray else Color.White,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        maxLines = 4,
        style = TextStyle(
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
        )
    )
}

@Composable
fun CustomTextH3(text: String?, modifier: Modifier = Modifier) {
    Text(
        text = text ?: "",
        modifier = modifier,
        color = Color.Gray,
        fontSize = 18.sp,
        textAlign = TextAlign.Start,
        overflow = TextOverflow.Ellipsis,
        maxLines = 4,
        style = TextStyle(
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
        )
    )
}

@Composable
fun CustomTextH4(text: String?, modifier: Modifier = Modifier) {
    Text(
        text = text ?: "",
        modifier = modifier,
        color = Color.White,
        fontSize = 16.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 4,
        fontWeight = FontWeight.Bold,
        style = TextStyle(fontFamily = FontFamily.Monospace)
    )
}

@Composable
fun CustomTextH5(text: String?, modifier: Modifier = Modifier) {
    Text(
        text = text ?: "",
        modifier = modifier,
        color = Color.White,
        fontSize = 14.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 4,
        style = TextStyle(fontFamily = FontFamily.SansSerif)
    )
}


