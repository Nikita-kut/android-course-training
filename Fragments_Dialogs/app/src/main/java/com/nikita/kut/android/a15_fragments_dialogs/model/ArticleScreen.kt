package com.nikita.kut.android.a15_fragments_dialogs.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArticleScreen(
    @DrawableRes val drawableRes: Int,
    @StringRes val stringRes: Int,
    val tags: List<ArticleTag>
)