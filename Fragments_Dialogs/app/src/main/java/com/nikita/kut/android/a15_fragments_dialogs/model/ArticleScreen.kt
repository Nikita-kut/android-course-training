package com.nikita.kut.android.a15_fragments_dialogs.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleScreen(
    @DrawableRes val drawableRes: Int,
    @StringRes val stringRes: Int,
    val tags: List<ArticleTag>
) : Parcelable