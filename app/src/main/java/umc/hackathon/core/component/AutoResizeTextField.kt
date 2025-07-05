package umc.hackathon.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AutoResizeTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    textStyle: TextStyle = TextStyle(fontSize = 16.sp),
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.Gray
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(backgroundColor)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, borderColor, RoundedCornerShape(20.dp))
        ,
        textStyle = textStyle,
        maxLines = Int.MAX_VALUE,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(text = placeholder, style = textStyle.copy(color = Color.Gray)
                , modifier = Modifier
                        .padding(start=16.dp,top =16.dp
                ))
            }
            innerTextField()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun viewTextField() {
    AutoResizeTextField(
        value = "",
        onValueChange = {},
        placeholder = "검색어를 입력해주세요"
    )
}
