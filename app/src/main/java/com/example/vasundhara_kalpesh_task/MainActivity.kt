package com.example.vasundhara_kalpesh_task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vasundhara_kalpesh_task.databinding.ActivityMainBinding
import com.example.vasundhara_kalpesh_task.registration_form.RegistrationFormActivity
import com.example.vasundhara_kalpesh_task.summation.SummationActivity
import com.example.vasundhara_kalpesh_task.wallnotes.WallNotesActivity


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         initView()
    }

    private fun initView() {
        setOnclickLis()
    }

    private fun setOnclickLis() {
        binding.btnSummationFunctionality.setOnClickListener {
            setIntentSumActivity();
        }
        binding.btnRegistrationFrom.setOnClickListener {
            setRegistrationFrom();
        }
        binding.btnStickyWallNotes.setOnClickListener {
            setStickyWallNotes();
        }
        //textRegistrationFrom
    }

    private fun setIntentSumActivity() {
        val intent = Intent(applicationContext, SummationActivity::class.java)
        startActivity(intent);
    }
    private fun setRegistrationFrom() {
        val intent = Intent(applicationContext, RegistrationFormActivity::class.java)
        startActivity(intent);
    }

    private fun setStickyWallNotes() {
        val intent = Intent(applicationContext, WallNotesActivity::class.java)
        startActivity(intent);
    }


}