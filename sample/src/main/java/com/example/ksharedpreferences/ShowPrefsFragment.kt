package com.example.ksharedpreferences

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_show_prefs.*

class ShowPrefsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_show_prefs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        updateUI(Prefs.trigger.value)
    }

    private fun updateUI(isChecked: Boolean) {
        trigger.isChecked = isChecked
        nameText.text = Prefs.text
        numberText.text = Prefs.uid.toString()
        view?.setBackgroundColor(if (isChecked) Color.GREEN else Color.YELLOW)
    }

    private fun setUp() {
        Prefs.trigger.observe(
            owner = { this.lifecycle },
            observer = { updateUI(it) }
        )
    }
}