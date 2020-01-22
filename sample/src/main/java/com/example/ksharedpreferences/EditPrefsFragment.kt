package com.example.ksharedpreferences

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_edit_prefs.*

class EditPrefsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_prefs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        updateUI()
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI() {
        trigger.isChecked = Prefs.trigger.value
        nameText.setText(Prefs.text)
        numberText.setText(Prefs.uid.toString())
    }

    private fun setUp() {
        nameText.doOnTextChanged { text, _, _, _ ->
            Log.d("EditPrefsFragment", "nameText.onTextChanged: `$text`")
            Prefs.text = text?.toString() ?: ""
            Prefs.trigger.value = Prefs.trigger.value // hack update ShowFragment
        }

        numberText.doOnTextChanged { text, _, _, _ ->
            Log.d("EditPrefsFragment", "numberText.onTextChanged: `$text`")
            Prefs.uid = text?.toString()?.toLong() ?: 0
            Prefs.trigger.value = Prefs.trigger.value // hack update ShowFragment
        }

        trigger.setOnCheckedChangeListener { _, isChecked ->
            Log.d("EditPrefsFragment", "trigger.OnCheckedChanged: `$isChecked`")
            Prefs.trigger.value = isChecked
        }
    }
}