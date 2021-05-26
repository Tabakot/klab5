package com.example.klab5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private val STORAGE = "storage"
    private val sharedPreferences: SharedPreferences
        get() {
            return this.getSharedPreferences(STORAGE, Context.MODE_PRIVATE)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var counterYes = getString("yes", "0")
        var counterNo = getString("no", "0")
        val textViewYes: TextView = findViewById(R.id.textView3)
        val textViewNo: TextView = findViewById(R.id.textView4)
        val radioButtonYes: RadioButton = findViewById(R.id.radioButton)
        val button: Button = findViewById(R.id.button)

        textViewYes.text = counterYes
        textViewNo.text = counterNo


        button.setOnClickListener {
            if (radioButtonYes.isChecked){
                counterYes = ((counterYes?.toInt() ?: 0) + 1).toString()
                saveString(counterYes!!, "yes")
                textViewYes.text = counterYes
            } else {
                counterNo = ((counterNo?.toInt() ?: 0) + 1).toString()
                saveString(counterNo!!, "no")
                textViewNo.text = counterNo
            }
        }
    }

    private fun saveString(string: String, key: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, string)
        editor.apply()
    }

    private fun getString(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue)
    }
}