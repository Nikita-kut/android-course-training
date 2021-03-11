package com.nikita.kut.android.a15_fragments_dialogs.screens

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nikita.kut.android.a15_fragments_dialogs.model.ArticleTag

class FilterTagsDialogFragment : DialogFragment() {

    private val listener: ConfirmClickListener? by lazy { parentFragment as ConfirmClickListener }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val tagsToString: Array<String> = ArticleTag.values().map { it.name }.toTypedArray()
        val selectedTagsString: ArrayList<String> = arrayListOf()
        val booleanTags: BooleanArray? = arguments?.getBooleanArray(KEY_TAGS)

        return AlertDialog.Builder(requireContext())
            .setTitle("Article filter")
            .setMultiChoiceItems(tagsToString, booleanTags) { dialog, which, isChecked ->
                booleanTags?.set(which, isChecked)
            }
            .setPositiveButton("Confirm") { dialog, id ->
                for (current in tagsToString.indices) {
                    val checked = booleanTags?.get(current)
                    if (checked == true) {
                        selectedTagsString.add(tagsToString[current])
                    }
                }
                listener?.onConfirmClick(selectedTagsString)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .create()
    }

    companion object {
        private const val KEY_TAGS = "key_tags"

        fun dialogFragmentNewInstance(
            tags: BooleanArray
        ): FilterTagsDialogFragment {
            val fragment = FilterTagsDialogFragment()
            val args = Bundle().apply {
                putBooleanArray(KEY_TAGS, tags)
            }
            fragment.arguments = args
            return fragment
        }
    }

    interface ConfirmClickListener {
        fun onConfirmClick(selectedItems:ArrayList<String>)
    }

}