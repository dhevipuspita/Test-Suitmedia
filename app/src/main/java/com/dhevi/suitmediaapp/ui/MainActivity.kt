package com.dhevi.suitmediaapp.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dhevi.suitmediaapp.R
import com.dhevi.suitmediaapp.databinding.ActivityMainBinding
import com.dhevi.suitmediaapp.utils.NAME

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        supportActionBar?.hide()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnCheck.setOnClickListener {
            val palindrome = binding.poliEditText.text.toString().trim()

            if (palindrome.isEmpty()){
                Toast.makeText(
                    applicationContext,
                    getString(R.string.inputFirst),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else if (palindrome.equals(palindrome.reversed(), true)){
                Toast.makeText(
                    applicationContext,
                    getString(R.string.isPalindrome),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.notPalindrome),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra(NAME, name)
            startActivity(intent)
        }
    }
}