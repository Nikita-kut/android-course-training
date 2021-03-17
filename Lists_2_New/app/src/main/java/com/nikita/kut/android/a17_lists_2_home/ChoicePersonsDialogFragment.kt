package com.nikita.kut.android.a17_lists_2_home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.nikita.kut.android.a17_lists_2_home.data.WitcherPerson
import kotlin.random.Random


class ChoicePersonsDialogFragment : DialogFragment() {

    private val listener: ClickListener? by lazy { parentFragment as ClickListener }
    private val witcherView: View by lazy {
        activity!!.layoutInflater.inflate(
            R.layout.fragment_dialog_witcher,
            null
        )
    }
    private val wildHuntView: View by lazy {
        activity!!.layoutInflater.inflate(
            R.layout.fragment_dialog_wild_hunt,
            null
        )
    }
    private val monsterView: View by lazy {
        activity!!.layoutInflater.inflate(
            R.layout.fragment_dialog_monsters,
            null
        )
    }
    private var genderAdapter: ArrayAdapter<CharSequence>? = null
    private var schoolAdapter: ArrayAdapter<CharSequence>? = null
    private var colorAdapter: ArrayAdapter<CharSequence>? = null
    private var kindAdapter: ArrayAdapter<CharSequence>? = null
    private var sizeAdapter: ArrayAdapter<CharSequence>? = null
    private val etWitcherName: EditText by lazy { witcherView.findViewById(R.id.et_monster_name) }
    private val etWitcherAge: EditText by lazy { witcherView.findViewById(R.id.et_witcher_age) }
    private val etWildHuntName: EditText by lazy { wildHuntView.findViewById(R.id.et_monster_name) }
    private val etMonsterName: EditText by lazy { monsterView.findViewById(R.id.et_monster_name) }
    private lateinit var genderSpinner: Spinner
    private lateinit var schoolSpinner: Spinner
    private val colorSpinner: Spinner by lazy { wildHuntView.findViewById(R.id.spinner_color) }
    private val kindSpinner: Spinner by lazy { monsterView.findViewById(R.id.spinner_kind) }
    private val sizeSpinner: Spinner by lazy { monsterView.findViewById(R.id.spinner_monster_size) }
    private lateinit var gender: String
    private lateinit var school: String
    private lateinit var color: String
    private lateinit var kind: String
    private lateinit var size: String
    private val witcherAvatars = arrayListOf(
        "https://pixabay.com/get/g050f93229c6490bfd5fcdeb30d4bb5de81987cc693efeccd65e8bf52c00e6feda2f49bbebaf9dd437ba509020c2e9da2477545eb161091528e9677af998b5f5f_1280.jpg",
        "https://pixabay.com/get/g272a9aeda74238fc18c38f6db8f88fa523847b9671f45d183f3a266bc07941c3a689eef507f6d5c7a71af3a6e238ed6dbe36199fbbc86e1b8ec4bdbc06d6a964_1280.jpg",
        "https://pixabay.com/get/gf89214ace95e4727d931841d10398146754e98cae5697ce4053d32f46b1434f110048cedbfd1390df314ba696596786be90bb565f23c84a8ccbff4a20b96f35f_1280.jpg"
    )
    private val wildHuntAvatars = arrayListOf(
        "https://i.pinimg.com/originals/3d/53/6e/3d536e9740c89d9bf2b216e60d7abd88.jpg",
        "https://i.pinimg.com/originals/8a/ad/b8/8aadb8ae810ffa3a7892c34af96608cc.jpg"
    )
    private val monsterAvatars = arrayListOf(
        "https://s1.1zoom.me/big0/18/The_Witcher_3_Wild_Hunt_Werewolf_Monsters_567739_1280x817.jpg",
        "https://s1.1zoom.ru/big0/611/The_Witcher_3_Wild_Hunt_503973.jpg",
        "https://i.pinimg.com/originals/8a/08/a0/8a08a02991f6fbef900bf31a04da3f8d.jpg",
        "https://i.pinimg.com/originals/8f/e4/8c/8fe48cdb9f8a92b5060e274c9c40e61d.jpg"
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        genderSpinner = witcherView.findViewById(R.id.spinner_gender)
        schoolSpinner = witcherView.findViewById(R.id.spinner_witcher_school)
        setWitcherAdapters()
        getWitcherSpinnerItems()

        setWildHuntAdapter()
        getWildHuntSpinnerItems()

        setMonsterAdapters()
        getMonsterSpinnerItems()

        val persons: Array<String> = arrayOf("Witcher", "Wild Hunt", "Monster")
        var checked = -1

        return AlertDialog.Builder(requireContext())
            .setTitle("What do you want to create?")
            .setSingleChoiceItems(persons, checked) { _, which -> checked = which }
            .setPositiveButton("Ok") { dialog, id ->
                when (checked) {
                    0 -> {
                        val witcher = WitcherPerson.Witchers(0, "", 0, "", "", "")
                        createDialogFragment(witcherView, witcher)
                    }
                    1 -> {
                        val wildHunt = WitcherPerson.WildHunt(0, "", "", "")
                        createDialogFragment(wildHuntView, wildHunt)
                    }
                    2 -> {
                        val monster = WitcherPerson.Monsters(0, "", "", "", "")
                        createDialogFragment(monsterView, monster)
                    }
                }
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .create()
    }

    override fun onDestroyView() {
        colorAdapter = null
        genderAdapter = null
        kindAdapter = null
        sizeAdapter = null
        schoolAdapter = null
        super.onDestroyView()
    }

    private fun createDialogFragment(view: View, person: WitcherPerson) {
        var createdPerson: WitcherPerson = person
        AlertDialog.Builder(view.context)
            .setView(view)
            .setPositiveButton("Create") { _, _ ->
                when (person) {
                    is WitcherPerson.Witchers -> createdPerson = WitcherPerson.Witchers(
                        id = Random.nextLong(),
                        name = etWitcherName.text.toString(),
                        age = etWitcherAge.text.toString().toIntOrNull() ?: 0,
                        gender = gender,
                        school = school,
                        avatarLink = witcherAvatars.random()
                    )
                    is WitcherPerson.WildHunt -> createdPerson = WitcherPerson.WildHunt(
                        id = Random.nextLong(),
                        name = etWildHuntName.text.toString(),
                        color = color,
                        avatarLink = wildHuntAvatars.random()
                    )
                    is WitcherPerson.Monsters -> createdPerson = WitcherPerson.Monsters(
                        id = Random.nextLong(),
                        name = etMonsterName.text.toString(),
                        kind = kind,
                        size = size,
                        avatarLink = monsterAvatars.random()
                    )
                }
                listener?.onCreateClick(createdPerson)
            }
            .create()
            .show()
    }

    private fun setWitcherAdapters() {
        genderAdapter = ArrayAdapter.createFromResource(
            witcherView.context,
            R.array.gender,
            android.R.layout.simple_spinner_item
        )
        genderAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderSpinner.adapter = genderAdapter

        schoolAdapter = ArrayAdapter.createFromResource(
            witcherView.context,
            R.array.witcher_school,
            android.R.layout.simple_spinner_item
        )
        schoolAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        schoolSpinner.adapter = schoolAdapter
    }

    private fun setWildHuntAdapter() {
        colorAdapter = ArrayAdapter.createFromResource(
            wildHuntView.context,
            R.array.wild_hunt_color,
            android.R.layout.simple_spinner_item
        )
        colorAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        colorSpinner.adapter = colorAdapter
    }

    private fun setMonsterAdapters() {
        kindAdapter = ArrayAdapter.createFromResource(
            monsterView.context,
            R.array.monster_kind,
            android.R.layout.simple_spinner_item
        )
        kindAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        kindSpinner.adapter = kindAdapter

        sizeAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.monster_size,
            android.R.layout.simple_spinner_item
        )
        sizeAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sizeSpinner.adapter = sizeAdapter
    }

    private fun getWitcherSpinnerItems() {
        genderSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    gender = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        schoolSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    school = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun getWildHuntSpinnerItems() {
        colorSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    color = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun getMonsterSpinnerItems() {
        kindSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    kind = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        sizeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    size = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    interface ClickListener {
        fun onCreateClick(witcherPerson: WitcherPerson)
    }
}