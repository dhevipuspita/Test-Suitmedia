package com.dhevi.suitmediaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhevi.suitmediaapp.adapter.UserAdapter
import com.dhevi.suitmediaapp.data.DataItem
import com.dhevi.suitmediaapp.databinding.ActivityMainBinding
import com.dhevi.suitmediaapp.databinding.ActivityThirdScreenBinding
import com.dhevi.suitmediaapp.utils.FIRST_NAME
import com.dhevi.suitmediaapp.utils.LAST_NAME
import com.dhevi.suitmediaapp.utils.RESULT_CODE

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var thirdScreenViewModel: ThirdScreenViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupAction()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupAction() {
        thirdScreenViewModel = ThirdScreenViewModel()
        adapter = UserAdapter()

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@ThirdScreenActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
        }

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                val resultIntent = Intent()
                resultIntent.putExtra(FIRST_NAME, data.firstName)
                resultIntent.putExtra(LAST_NAME, data.lastName)
                setResult(RESULT_CODE, resultIntent)
                finish()
            }

        })

        thirdScreenViewModel.setUsers()
        thirdScreenViewModel.getUsers().observe(this){
            if(it!=null){
                adapter.setList(it)
                binding.tvEmpty.visibility = View.GONE
            }
        }

        thirdScreenViewModel.isLoading.observe(this){
            showLoading(it)
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}