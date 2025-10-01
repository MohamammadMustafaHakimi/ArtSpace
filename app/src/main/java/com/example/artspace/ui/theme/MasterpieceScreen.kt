package com.example.artspace.ui.theme
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import com.example.artspace.data.Masterpiece
import com.example.artspace.data.masterpieces
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.ui.res.stringResource
import com.example.artspace.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.launch


// UI features
// button next and previous; future: swipe next and previous
// how to keep the buttons on the screen while the cards are displayed one per screen
// animations for pager change
// making the corners of the image container pointed not round
// fixing the space between ArtInformation and button






@Composable fun ArtApp(modifier: Modifier = Modifier.safeDrawingPadding().safeContentPadding()) {
    val state = rememberPagerState(initialPage = 0) { masterpieces.size }
    val scope = rememberCoroutineScope()
    val lastIndex = masterpieces.size -1 // last index


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 55.dp, start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = state,
            userScrollEnabled = true
        ) { page -> // userScrollEnabled controls if the swiping for changing each page should work or no
            val masterpiece = masterpieces[page]
            MasterpieceItem(masterpiece)
        }
        Spacer(modifier = Modifier.height(20.dp))
        ButtonComponents(
            onPrevious = {
                scope.launch {
                    if (state.currentPage == 0) { state.scrollToPage(lastIndex) }
                    // else state.animateScrollToPage(state.currentPage - 1)
                    else state.scrollToPage(state.currentPage - 1)
                }
            },
            onNext = {
                scope.launch {
                    if (state.currentPage == lastIndex) { state.scrollToPage(0) }
                    // else state.animateScrollToPage(state.currentPage + 1)
                    else state.scrollToPage(state.currentPage + 1)
                }
            }
        )

    }
}

@Composable fun MasterpieceItem(
    masterpiece: Masterpiece,
    modifier: Modifier = Modifier
        .wrapContentSize(Alignment.Center)
    ) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white)
        )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            ImageRes(masterpiece.imageRes)
            Spacer(modifier = Modifier.height(80.dp))
            ArtInformation(masterpiece.artNameRes, masterpiece.artistNameRes, masterpiece.dateMadeRes)
//        ButtonComponents(modifier = modifier)
        }

    }
}

@Composable fun ImageRes(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier
        .background(colorResource(R.color.app_background))
        .padding(25.dp)
        .shadow(
            elevation = 12.dp,
            shape = MaterialTheme.shapes.medium, // Rounded corners if needed
            clip = true
        )
        .background(Color.White) // background so shadow shows properly
        .fillMaxWidth()
        .height(400.dp)

    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .height(400.dp),
            contentScale = ContentScale.FillBounds

        )
    }
}


@Composable fun ArtInformation(artNameRes: Int, artistNameRes: Int, dateMadeRes: Int) {
    Column(
        modifier = Modifier
            .width(280.dp)
            .background(colorResource(R.color.art_info_background))
            .padding(20.dp)
    ) {
        Row() {
            Text(text = stringResource(artNameRes), style = MaterialTheme.typography.bodyLarge)
        }
        Row() {
            Text(text = stringResource(artistNameRes), modifier = Modifier.padding(end = 1.dp), style = MaterialTheme.typography.bodyMedium)
            Text(text = stringResource(dateMadeRes), style = MaterialTheme.typography.labelMedium)
        }
    }
}



@Composable fun ButtonComponents(onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(modifier = Modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Button(onClick = onPrevious, modifier = Modifier.weight(1f)) {
            Text(text = stringResource(R.string.previous))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = onNext, modifier = Modifier.weight(1f)) {
            Text(text = stringResource(R.string.next))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MasterpieceItemReview() {
    ArtSpaceTheme {
        MasterpieceItem(Masterpiece(R.drawable.redon_cyclops, R.string.cyclops, R.string.odelon_redon, R.string.cyclops_date))
    }
}

