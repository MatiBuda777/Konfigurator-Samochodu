package com.example.konfiguratorsamochodu

import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val radioGroup = findViewById<RadioGroup>(R.id.radiogroup_car)
        val checkbox_cooler = findViewById<CheckBox>(R.id.checkbox_cooler)
        val checkbox_leatherSeats = findViewById<CheckBox>(R.id.checkbox_leatherSeats)
        val checkbox_gps = findViewById<CheckBox>(R.id.checkbox_gps)
        val imageview_car = findViewById<ImageView>(R.id.imageview_car)
        val textview_car = findViewById<TextView>(R.id.textview_car)
        var text = ""

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val checkedRadioButton: RadioButton = findViewById(checkedId)
            val selectedText = checkedRadioButton.text.toString()

            // Usuwanie niechcianych nazw
            text = text.replace("Sedan, ", "")
                .replace("Suv, ", "")
                .replace("Hatchback, ", "")

            text += "$selectedText, "
        }


        // Aktualizacja listy wybranych dodatków
        fun updateSelectedFeatures() {
            val selectedFeatures = mutableListOf<String>()

            val selectedRadioId = radioGroup.checkedRadioButtonId
            if (selectedRadioId != -1) {
                val checkedRadioButton: RadioButton = findViewById(selectedRadioId)
                selectedFeatures.add(checkedRadioButton.text.toString())
            }
            if (checkbox_cooler.isChecked) {
                selectedFeatures.add(checkbox_cooler.text.toString())
            }
            if (checkbox_leatherSeats.isChecked) {
                selectedFeatures.add(checkbox_leatherSeats.text.toString())
            }
            if (checkbox_gps.isChecked) {
                selectedFeatures.add(checkbox_gps.text.toString())
            }

            text = selectedFeatures.joinToString(", ")
            textview_car.text = text
        }

        checkbox_cooler.setOnCheckedChangeListener { _, isChecked ->
            updateSelectedFeatures()
        }

        checkbox_leatherSeats.setOnCheckedChangeListener { _, isChecked ->
            updateSelectedFeatures()
        }

        checkbox_gps.setOnCheckedChangeListener { _, isChecked ->
            updateSelectedFeatures()
        }
    }

}