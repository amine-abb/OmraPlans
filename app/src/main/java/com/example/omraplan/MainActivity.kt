package com.example.omraplan

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                Scaffold (modifier = Modifier.fillMaxSize()){ innerPadding ->
                    OmraScreen(
                        modifier = Modifier.padding(innerPadding)
                    ) }

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
        TextInfo(modifier = Modifier, infoPlan = infoPlan,infoTime = infoTime )
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
                Text(text = "Back")
            }
            Button(onClick = {
                when (currentPlan) {
                    firstplan -> {
                        currentPlan = secondplan
                        infoPlan = R.string.infoFeb
                        infoTime = R.string.timeFeb
                    }
                    secondplan -> {
                        currentPlan = thirdplan
                        infoPlan =R.string.infochaarban
                        infoTime = R.string.timechaaban

                    }
                    thirdplan -> {
                        currentPlan = fourthplan
                        infoPlan =R.string.inforamdan
                        infoTime = R.string.tameramdan
                    }
                    else -> {
                        currentPlan = firstplan
                        infoPlan =R.string.infoJun
                        infoTime = R.string.timeJun
                    }
                }
            }) {
                Text(text = "Next")
            }
        }

    }
}

@Composable
fun DisplayPlan(
    modifier: Modifier=Modifier,
    @DrawableRes currentPlan: Int
) {
    Surface(
        modifier = modifier.padding(20.dp),
        border = BorderStroke(2.dp, color = Color.Transparent), shadowElevation = 15.dp)
    {
    Image(
        painter = painterResource(currentPlan),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(400.dp)
            .padding(20.dp, 25.dp, 20.dp, 15.dp)
    )
}}

@Composable
fun TextInfo(modifier: Modifier=Modifier,@StringRes infoPlan: Int, @StringRes infoTime: Int) {
    Card(modifier = Modifier.padding(20.dp,30.dp) , colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant ) ) {
        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp) ,Arrangement.Center,Alignment.CenterHorizontally){
    Text(text = stringResource(infoPlan), fontSize = 35.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
    Text(modifier = Modifier,text = stringResource(infoTime), fontSize = 20.sp, textAlign = TextAlign.Center)

}}}

@Preview
@Composable
fun OmraPlanPreview(){
    OmraPlanTheme{
        OmraScreen()
    }
}
