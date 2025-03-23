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
                imageview_car.setImageResource(resId)
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

            text = selectedFeatures.joinToString(",\n")
            textview_car.text = text
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            updateSelectedFeatures()
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