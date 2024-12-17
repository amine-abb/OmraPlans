package com.example.omraplan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.omraplan.ui.theme.OmraPlanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OmraPlanTheme {
                OmraScreen()
            }
        }
    }
}

@Composable
fun OmraScreen(modifier: Modifier = Modifier) {
    val firstplan = R.drawable.jun_plan
    val secondplan = R.drawable.feb_plan
    val thirdplan = R.drawable.chaaban_plan
    val fourthplan = R.drawable.ramadan_plan
    var currentPlan by remember { mutableStateOf(R.drawable.jun_plan) }
    var infoPlan by remember { mutableStateOf(R.string.infoJun) }
    var infoTime by remember { mutableStateOf(R.string.timeJun) }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DisplayPlan(currentPlan = currentPlan)
        Spacer(modifier = Modifier.padding(10.dp))
        TextInfo(infoPlan = infoPlan, infoTime = infoTime)
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
            Button(onClick = {
                when (currentPlan) {
                    firstplan -> {
                        currentPlan = fourthplan
                        infoPlan =R.string.inforamdan
                        infoTime = R.string.tameramdan
                    }
                    secondplan -> {
                        currentPlan = firstplan
                        infoPlan =R.string.infoJun
                        infoTime = R.string.timeJun
                    }

                    thirdplan -> {
                        currentPlan = secondplan
                        infoPlan =R.string.infoFeb
                        infoTime = R.string.timeFeb
                    }

                    else -> {
                        currentPlan = thirdplan
                        infoPlan =R.string.infochaarban
                        infoTime = R.string.timechaaban
                    }
                }
            }) {
                Text(text = "back")
            }
        }

    }
}

@Composable
fun DisplayPlan(
    modifier: Modifier=Modifier,
    @DrawableRes currentPlan: Int
) {
    Image(
        painter = painterResource(currentPlan),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TextInfo(@StringRes infoPlan: Int, @StringRes infoTime: Int) {
    Text(text = stringResource(infoPlan), fontSize = 35.sp)
    Text(text = stringResource(infoTime), fontWeight = FontWeight.Bold, fontSize = 20.sp)

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OmraPlanTheme {
        OmraScreen()
    }
}