package com.nikita.kut.android.a15_fragments_dialogs.screens

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.nikita.kut.android.a15_fragments_dialogs.R
import com.nikita.kut.android.a15_fragments_dialogs.databinding.FragmentArticleBinding
import com.nikita.kut.android.a15_fragments_dialogs.model.ArticleTag
import kotlinx.android.parcel.Parcelize

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private var _binding: FragmentArticleBinding? = null
    private val binding
        get() = _binding!!

    private val listener: ClickListener? by lazy { parentFragment as ClickListener }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.ivArticlePicture.setImageResource(
            requireArguments().getInt(
                KEY_DRAWABLE
            )
        )
        binding.tvArticleText.setText(
            requireArguments().getInt(
                KEY_TEXT
            )
        )
        binding.btnGenerateBadge.setOnClickListener {
            listener?.onGenerateBadgeButtonClick()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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