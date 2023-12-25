package com.dhevi.suitmediaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.dhevi.suitmediaapp.R
import com.dhevi.suitmediaapp.databinding.ActivitySecondScreenBinding
import com.dhevi.suitmediaapp.utils.FIRST_NAME
import com.dhevi.suitmediaapp.utils.LAST_NAME
import com.dhevi.suitmediaapp.utils.NAME
import com.dhevi.suitmediaapp.utils.RESULT_CODE

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var selectedUsername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()

        supportActionBar?.hide()
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == RESULT_CODE && result.data != null) {
            val firstName = result.data?.getStringExtra(FIRST_NAME)
            val lastName = result.data?.getStringExtra(LAST_NAME)
            selectedUsername.text = "$firstName $lastName"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupAction() {
        val name =intent.getStringExtra(NAME)

        if (name.isNullOrEmpty()) {
            binding.nameUser.text = getString(R.string.input_name)
        } else {
            binding.nameUser.text = name
        }

        selectedUsername = binding.selectUser

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnChoose.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}