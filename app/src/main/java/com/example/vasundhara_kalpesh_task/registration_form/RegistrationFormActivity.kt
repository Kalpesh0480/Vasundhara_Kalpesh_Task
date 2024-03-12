package com.example.vasundhara_kalpesh_task.registration_form

import android.app.Activity
import android.app.DatePickerDialog
import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.example.vasundhara_kalpesh_task.databinding.ActivityRegistrationformBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.regex.Pattern


class RegistrationFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationformBinding
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_registrationform)

        binding = ActivityRegistrationformBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        //hide input soft keypad
        binding.dobEditText.setInputType(InputType.TYPE_CLASS_TEXT)
      //  binding.dobEditText.requestFocus()
        val mgr = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.showSoftInput(binding.dobEditText, InputMethodManager.SHOW_FORCED)

    }

    private fun initView() {
       // editText.clearFocus()
        setOnclickLis()


    }
    private fun setOnclickLis() {
        binding.toolbar.setNavigationOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        // set DatePicker Dialog
        binding.dobEditText.setOnClickListener {
            showDatePickerDialog()
        }

        binding.submitBtn.setOnClickListener {
            if(validateForm())
            {
                Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_SHORT).show()
            }


        }

    }



    private fun validateForm(): Boolean {
        val fullName = binding.fullNameEditText.text.toString().trim()
        val dob = binding.dobEditText.text.toString().trim()
        val address = binding.addressEditText.text.toString().trim()
        val email = binding.emailEditText.text.toString().trim()
        val mobile = binding.mobileEditText.text.toString().trim()

        // Validating full name
        if (fullName.isEmpty()) {
            binding.fullNameEditText.error = "Full Name is required"
            binding.fullNameEditText.requestFocus()
            return false
        }

        // Validating date of birth
        if (dob.isEmpty()) {
            binding. dobEditText.error = "Date of Birth is required"
            binding.dobEditText.requestFocus()
            return false
        }

        // Validating address
        if (address.isEmpty()) {
            binding. addressEditText.error = "Address is required"
            binding.addressEditText.requestFocus()
            return false
        }

        // Validating email
        if (email.isEmpty()) {
            binding. emailEditText.error = "Email is required"
            binding.emailEditText.requestFocus()
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEditText.error = "Invalid Email"
            binding.emailEditText.requestFocus()
            return false
        }

        // Validating mobile number
        if (mobile.isEmpty()) {
            binding.mobileEditText.error = "Mobile Number is required"
            binding.mobileEditText.requestFocus()
            return false
        } else if (!Pattern.matches("[0-9]{10}", mobile)) {
            binding.mobileEditText.error = "Invalid Mobile Number"
            binding.mobileEditText.requestFocus()
            return false
        }

        // All validations passed
        return true
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding.dobEditText.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Set the maximum date (optional)
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()

        datePickerDialog.show()
    }


}
