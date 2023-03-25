package com.example.codemath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var editTextNumberDecimal: EditText
    private lateinit var seekBar: SeekBar
    private lateinit var PercentLabel: TextView
    private lateinit var TipPercentLabel: TextView
    private lateinit var TotaltotalLabel: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal)
        seekBar = findViewById(R.id.seekBar)
        PercentLabel = findViewById(R.id.PercentLabel)
        TipPercentLabel = findViewById(R.id.TipPercentLabel)
        TotaltotalLabel = findViewById(R.id.TotaltotalLabel)


        seekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            PercentLabel.text = "$progress%"
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
        editTextNumberDecimal.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                computeTipAndTotal()
            }

        })
    }

    private fun computeTipAndTotal() {
        if(editTextNumberDecimal.text.isEmpty()) {
            TipPercentLabel.text = ""
            TotaltotalLabel.text = ""

            return
        }
        val baseAmount = editTextNumberDecimal.text.toString().toDouble()
        val tipPercent = seekBar.progress

        val tipAmount = baseAmount * tipPercent /100
        val totalAmount = tipAmount + baseAmount

        TipPercentLabel.text = "%.2f".format(tipAmount)
        TotaltotalLabel .text = "%.2f".format(totalAmount)
    }
}