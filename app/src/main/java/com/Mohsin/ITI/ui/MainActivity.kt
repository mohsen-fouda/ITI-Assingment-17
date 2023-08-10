package com.Mohsin.ITI.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mohsin.iti.R
import com.mohsin.iti.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnViewPosts.setOnClickListener { startActivity(Intent(baseContext, PostsActivity::class.java)) }


        binding.btnLogin.setOnClickListener {
            val name = binding.etUserName.text.toString()
            val sharedPreferences=applicationContext.getSharedPreferences("UserPref", MODE_PRIVATE)
            val editor=sharedPreferences.edit()
            editor.putString("USERNAME",name)
            editor.putString("PASSWORD",binding.etPassword.text.toString())
            editor.putBoolean("IS_LOGIN",true)
            editor.commit()
            startActivity(Intent(baseContext, PostsActivity::class.java))
            Toast.makeText(baseContext,"Hello $name }",Toast.LENGTH_LONG).show()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            Toast.makeText(baseContext, data?.getStringExtra("LOGIN_BY"), Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.goToSecondActivity -> {
                startActivity(Intent(baseContext, SecondActivity::class.java))
            }
            R.id.exit -> {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Are you sure to exit?")
                    .setPositiveButton("yes") { _, _ ->
                        finish()
                    }.setNegativeButton("No") { dialog, _ ->
                        dialog.cancel()
                    }
                val dialog = dialogBuilder.create()
                dialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
