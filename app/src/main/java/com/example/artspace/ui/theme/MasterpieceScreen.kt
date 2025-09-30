package com.example.artspace.ui.theme
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import com.example.artspace.data.Masterpiece
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.ui.res.stringResource
import com.example.artspace.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

@Composable fun ArtApp() {

}

@Composable fun MasterpieceItem(
    masterpiece: Masterpiece,
    modifier: Modifier = Modifier
        .wrapContentSize(Alignment.Center)
    ) {
    Card(
        modifier = modifier,
    ) {
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            ImageRes(masterpiece.imageRes, modifier = modifier)
            Spacer(modifier = Modifier.height(50.dp))
            ArtInformation(masterpiece.artNameRes, masterpiece.artistNameRes, masterpiece.dateMadeRes, modifier = modifier)
            Spacer(modifier = Modifier.height(100.dp))
            ButtonComponents(modifier = modifier)
//        ButtonComponents(modifier = modifier)
        }

    }
}

@Composable fun ImageRes(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.background(colorResource(R.color.white))) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .padding(5.dp)
        )
    }
}
/*
* var imageRes: Int,
        var artNameRes: Int,
        var artistNameRes: Int,
        var dateMadeRes: Int,
*
*
* */

@Composable fun ArtInformation(artNameRes: Int, artistNameRes: Int, dateMadeRes: Int, modifier: Modifier) {
    Column(modifier = modifier.background(colorResource(R.color.teal_200)).padding(5.dp)) {
        Row(modifier) {
            Text(text = stringResource(artNameRes), modifier = modifier, style = MaterialTheme.typography.bodyLarge)
        }
        Row(modifier) {
            Text(text = stringResource(artistNameRes), modifier = modifier.padding(end = 1.dp), style = MaterialTheme.typography.bodyMedium)
            Text(text = stringResource(dateMadeRes), modifier = modifier, style = MaterialTheme.typography.labelMedium)
        }
    }
}



@Composable fun ButtonComponents(modifier: Modifier) {
    Row(modifier = modifier) {
        Button(onClick = {/**/}, modifier = modifier) {
            Text(text = stringResource(R.string.previous))
        }
        Button(onClick = {/**/}, modifier = modifier) {
            Text(text = stringResource(R.string.next))
        }
    }
}