package com.example.artspace.data


import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.artspace.R

data class Masterpiece(
        var imageRes: Int,
        var artNameRes: Int,
        var artistNameRes: Int,
        var dateMadeRes: Int,
    )

var masterpieces = listOf(
    Masterpiece(R.drawable.der_wanderer, R.string.the_wanderer, R.string.casper_david_friedrich, R.string.the_wanderer_date),
    Masterpiece(R.drawable.cabanel_fallen_angel, R.string.fallen_angel, R.string.alexandre_cabanel, R.string.fallen_angel_date),
    Masterpiece(R.drawable.cabanel_death_of_icarus, R.string.icarus_death, R.string.alexandre_cabanel, R.string.icarus_death_date),
    Masterpiece(R.drawable.redon_cyclops, R.string.cyclops, R.string.cyclops, R.string.cyclops_date),
    Masterpiece(R.drawable.vermeer_dienst_magd, R.string.milk_maid, R.string.johannes_vermeer, R.string.milk_maid_date),
    Masterpiece(R.drawable.caravaggio_calling_st_mathew, R.string.calling_st_mathew, R.string.caravaggio, R.string.calling_st_mathew_date),
    Masterpiece(R.drawable.hopper_night_hawks, R.string.night_hawks, R.string.edward_hopper, R.string.night_hawks_date),
)
