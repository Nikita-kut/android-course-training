package com.nikita.kut.android.a17_lists_2_home

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.nikita.kut.android.a17_lists_2_home.data.WitcherPerson
import com.nikita.kut.android.a17_lists_2_home.databinding.FragmentDialogMonstersBinding
import com.nikita.kut.android.a17_lists_2_home.databinding.FragmentDialogWildHuntBinding
import com.nikita.kut.android.a17_lists_2_home.databinding.FragmentDialogWitcherBinding
import kotlin.random.Random

class ChoicePersonsDialogFragment : DialogFragment() {

    private val listener: ClickListener? by lazy { parentFragment as ClickListener }
    private val witchView: View by lazy {activity!!.layoutInflater.inflate(R.layout.fragment_dialog_witcher,null)}
    private val _witchBinding: FragmentDialogWitcherBinding? by lazy {FragmentDialogWitcherBinding.bind(witchView)}
    private val witchBinding: FragmentDialogWitcherBinding
            get() = _witchBinding!!
    private val wDView: View by lazy {activity!!.layoutInflater.inflate(R.layout.fragment_dialog_wild_hunt,null)}
    private val _wdBinding: FragmentDialogWildHuntBinding? by lazy {FragmentDialogWildHuntBinding.bind(wDView)}
    private val wdBinding: FragmentDialogWildHuntBinding
            get() = _wdBinding!!
    private val monstView: View by lazy {activity!!.layoutInflater.inflate(R.layout.fragment_dialog_monsters,null)}
    private val _monstBinding: FragmentDialogMonstersBinding? by lazy {FragmentDialogMonstersBinding.bind(monstView)}
    private val monstBinding
            get() = _monstBinding!!
    private lateinit var gender: String
    private lateinit var school: String
    private lateinit var color: String
    private lateinit var kind: String
    private lateinit var size: String
    private val witchAvatars = arrayListOf(
        "https://pixabay.com/get/g050f93229c6490bfd5fcdeb30d4bb5de81987cc693efeccd65e8bf52c00e6feda2f49bbebaf9dd437ba509020c2e9da2477545eb161091528e9677af998b5f5f_1280.jpg",
        "https://pixabay.com/get/g272a9aeda74238fc18c38f6db8f88fa523847b9671f45d183f3a266bc07941c3a689eef507f6d5c7a71af3a6e238ed6dbe36199fbbc86e1b8ec4bdbc06d6a964_1280.jpg",
        "https://pixabay.com/get/gf89214ace95e4727d931841d10398146754e98cae5697ce4053d32f46b1434f110048cedbfd1390df314ba696596786be90bb565f23c84a8ccbff4a20b96f35f_1280.jpg"
    )
    private val wDAvatars = arrayListOf(
        "https://i.pinimg.com/originals/3d/53/6e/3d536e9740c89d9bf2b216e60d7abd88.jpg",
        "https://i.pinimg.com/originals/8a/ad/b8/8aadb8ae810ffa3a7892c34af96608cc.jpg"
    )
    private val monstAvatars = arrayListOf(
        "https://s1.1zoom.me/big0/18/The_Witcher_3_Wild_Hunt_Werewolf_Monsters_567739_1280x817.jpg",
        "https://s1.1zoom.ru/big0/611/The_Witcher_3_Wild_Hunt_503973.jpg",
        "https://i.pinimg.com/originals/8a/08/a0/8a08a02991f6fbef900bf31a04da3f8d.jpg",
        "https://i.pinimg.com/originals/8f/e4/8c/8fe48cdb9f8a92b5060e274c9c40e61d.jpg"
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val persons: Array<String> = arrayOf("Witcher", "Wild Hunt", "Monster")
        var checked = -1

        return AlertDialog.Builder(requireContext())
            .setTitle("What do you want to create?")
            .setSingleChoiceItems(persons, checked) { _, which -> checked = which }
            .setPositiveButton("Ok") { dialog, id ->
                when (checked) {
                    0 -> {
                        val witcher = WitcherPerson.Witchers(0, "", 0, "", "", "")
                        setAdapter(R.array.gender, witchView.context, witchBinding.spnrGender)
                        setAdapter(R.array.witcher_school, witchView.context, witchBinding.spnrSchool)
                        getSpinnerItem(witchBinding.spnrGender)
                        getSpinnerItem(witchBinding.spnrSchool)
                        createDialogFragment(witchView, witcher)
                    }
                    1 -> {
                        val wildHunt = WitcherPerson.WildHunt(0, "", "", "")
                        setAdapter(R.array.wild_hunt_color, wDView.context, wdBinding.spnrColor)
                        getSpinnerItem(wdBinding.spnrColor)
                        createDialogFragment(wDView, wildHunt)
                    }
                    2 -> {
                        val monster = WitcherPerson.Monsters(0, "", "", "", "")
                        setAdapter(R.array.monster_kind, monstView.context, monstBinding.spnrKind)
                        setAdapter(R.array.monster_size, monstView.context, monstBinding.spnrSize)
                        getSpinnerItem( monstBinding.spnrKind)
                        getSpinnerItem(monstBinding.spnrSize)
                        createDialogFragment(monstView, monster)
                    }
                }
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .create()
    }

    private fun createDialogFragment(view: View, person: WitcherPerson) {
        var createdPerson: WitcherPerson = person
        AlertDialog.Builder(view.context)
            .setView(view)
            .setPositiveButton("Create") { _, _ ->
                when (person) {
                    is WitcherPerson.Witchers -> createdPerson = WitcherPerson.Witchers(
                        id = Random.nextLong(),
                        name = witchBinding.etWitchName.text.toString(),
                        age = witchBinding.etWitcherAge.text.toString().toIntOrNull() ?: 0,
                        gender = gender,
                        school = school,
                        avatarLink = witchAvatars.random()
                    )
                    is WitcherPerson.WildHunt -> createdPerson = WitcherPerson.WildHunt(
                        id = Random.nextLong(),
                        name = wdBinding.etWdName.text.toString(),
                        color = color,
                        avatarLink = wDAvatars.random()
                    )
                    is WitcherPerson.Monsters -> createdPerson = WitcherPerson.Monsters(
                        id = Random.nextLong(),
                        name = monstBinding.etMonsterName.text.toString(),
                        kind = kind,
                        size = size,
                        avatarLink = monstAvatars.random()
                    )
                }
                listener?.onCreateClick(createdPerson)
            }
            .create()
            .show()
    }

    private fun setAdapter(
        arrayRes: Int,
        context: Context,
        spinner: Spinner
    ) {
        val adapter: ArrayAdapter<CharSequence>?
        adapter = ArrayAdapter.createFromResource(
            context,
            arrayRes,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun getSpinnerItem(spinner: Spinner) {
        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (spinner) {
                        witchBinding.spnrGender -> gender = parent?.getItemAtPosition(position).toString()
                        witchBinding.spnrSchool -> school = parent?.getItemAtPosition(position).toString()
                        wdBinding.spnrColor -> color = parent?.getItemAtPosition(position).toString()
                        monstBinding.spnrSize -> size = parent?.getItemAtPosition(position).toString()
                        monstBinding.spnrSize -> kind = parent?.getItemAtPosition(position).toString()
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    interface ClickListener {
        fun onCreateClick(witcherPerson: WitcherPerson)
    }
}