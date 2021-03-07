package com.nikita.kut.android.a15_fragments_dialogs.screens

import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.nikita.kut.android.a15_fragments_dialogs.R
import com.nikita.kut.android.a15_fragments_dialogs.model.ArticleTag
import kotlinx.android.parcel.Parcelize

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val listener: ClickListener? by lazy { parentFragment as ClickListener }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireView().findViewById<ImageView>(R.id.iv_article_picture).setImageResource(
            requireArguments().getInt(
                KEY_DRAWABLE
            )
        )
        requireView().findViewById<TextView>(R.id.tv_article_text).setText(
            requireArguments().getInt(
                KEY_TEXT
            )
        )
        requireView().findViewById<Button>(R.id.btn_generate_badge).setOnClickListener {
            listener?.onGenerateBadgeButtonClick()
        }
    }

    companion object {
        private const val KEY_DRAWABLE = "key_drawable"
        private const val KEY_TEXT = "key_text"

        fun newInstance(
            @DrawableRes drawableRes: Int,
            @StringRes stringRes: Int,
        ): ArticleFragment {
            val fragment = ArticleFragment()
            val args = Bundle().apply {
                putInt(KEY_DRAWABLE, drawableRes)
                putInt(KEY_TEXT, stringRes)
            }
            fragment.arguments = args
            return fragment
        }
    }

    interface ClickListener {
        fun onGenerateBadgeButtonClick()
    }
}