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
        val checkboxCooler = findViewById<CheckBox>(R.id.checkbox_cooler)
        val checkboxLeatherSeats = findViewById<CheckBox>(R.id.checkbox_leatherSeats)
        val checkboxGps = findViewById<CheckBox>(R.id.checkbox_gps)
        val imageviewCar = findViewById<ImageView>(R.id.imageview_car)
        val textviewCar = findViewById<TextView>(R.id.textview_car)
        var text = ""


        // Aktualizacja listy wybranych dodatków
        fun updateSelectedFeatures() {
            text = text.replace("Sedan, ", "")
                .replace("Suv, ", "")
                .replace("Hatchback, ", "")

            val selectedFeatures = mutableListOf<String>()

            val selectedRadioId = radioGroup.checkedRadioButtonId
            if (selectedRadioId != -1) {
                val checkedRadioButton: RadioButton = findViewById(selectedRadioId)
                selectedFeatures.add(checkedRadioButton.text.toString())

                var resId: Int = 0
                if (checkedRadioButton.text.contains("Suv")) resId = R.drawable.suv
                if (checkedRadioButton.text.contains("Sedan")) resId = R.drawable.sedan
                if (checkedRadioButton.text.contains("Hatchback")) resId = R.drawable.hatchback
                imageviewCar.setImageResource(resId)
            }
            if (checkboxCooler.isChecked) {
                selectedFeatures.add(checkboxCooler.text.toString())
            }
            if (checkboxLeatherSeats.isChecked) {
                selectedFeatures.add(checkboxLeatherSeats.text.toString())
            }
            if (checkboxGps.isChecked) {
                selectedFeatures.add(checkboxGps.text.toString())
            }

            text = selectedFeatures.joinToString(",\n")
            textviewCar.text = text
        }

        radioGroup.setOnCheckedChangeListener { _, _ ->
            updateSelectedFeatures()
        }

        checkboxCooler.setOnCheckedChangeListener { _, _ ->
            updateSelectedFeatures()
        }

        checkboxLeatherSeats.setOnCheckedChangeListener { _, _ ->
            updateSelectedFeatures()
        }

        checkboxGps.setOnCheckedChangeListener { _, _ ->
            updateSelectedFeatures()
        }
    }

}