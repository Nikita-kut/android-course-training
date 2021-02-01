package com.nikita.kut.android.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_long_content.*

class LongContentActivity : AppCompatActivity() {

    val text: String = R.id.text_view.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long_content)
        toolbar.title = "Long content"

        toolbar.setNavigationOnClickListener { toast("Navigation clicked") }

        initSearch()

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_1 -> {
                    toast("Action 1 clicked")
                    true
                }
                R.id.action_save -> {
                    toast("Action save clicked")
                    true
                }
                R.id.action_archive -> {
                    toast("Action archive clicked")
                    true
                }
                else -> false
            }
        }
    }

    private fun initSearch() {
        val searchItem = toolbar.menu.findItem(R.id.action_search)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                text_view.text = "search expanded"
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                text_view.setText(R.string.text_view)
                return true
            }
        })

        (searchItem.actionView as SearchView).setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (text.contains(query ?: "", true)) {
                        toast("Text contains")
                    } else toast("No contains")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            })
    }


    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}