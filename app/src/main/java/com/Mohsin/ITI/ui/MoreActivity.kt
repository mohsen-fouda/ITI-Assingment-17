package com.Mohsin.ITI.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mohsin.iti.databinding.ActivityDetailsBinding

class MoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.getStringExtra("username")
        binding.textUsername.text = username
        val date = intent.getStringExtra("date")
        binding.textPostDate.text = date
        val text = intent.getStringExtra("text")
        val image = intent.getIntExtra("image", 0)
        binding.ivPerson.setImageResource(image)
    }
}