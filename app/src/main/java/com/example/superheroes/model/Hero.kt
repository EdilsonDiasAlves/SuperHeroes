package com.example.superheroes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Hero(
    @StringRes val nameResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @DrawableRes val imageResourceId: Int
)