package com.example.vasundhara_kalpesh_task.summation

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.vasundhara_kalpesh_task.databinding.ActivitySummationBinding


class SummationActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_summation)

        binding = ActivitySummationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }
    private fun initView() {
        setOnclickLis()
    }

    private fun setOnclickLis() {

        binding.toolbar.setNavigationOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        binding.addButton.setOnClickListener {
            addEditText()
        }
        binding.sumButton.setOnClickListener {
            calculateSum()
        }
    }

    private fun addEditText() {
        val editText = EditText(this)
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        editText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        editText.hint = "Enter number"
        binding.editTextContainer.addView(editText)
       // container.addView(editText)
    }

    private fun calculateSum() {
        var sum = 0
        for (i in 0 until  binding.editTextContainer.childCount) {
            val view =  binding.editTextContainer.getChildAt(i)
            if (view is EditText) {
                val text = view.text.toString()
                if (text.isNotEmpty()) {
                    sum += text.toInt()
                }else{
                    Log.e("sum","Sumation");

                }
            }
        }
        binding.sumText.text="Sum: $sum"
        //sumText.text = "Sum: $sum"
    }
}