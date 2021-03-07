package com.nikita.kut.android.a15_fragments_dialogs.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ArticleTag() : Parcelable {
    ANIMALS,
    FRUIT,
    OTHER;
}