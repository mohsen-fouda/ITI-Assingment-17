package com.Mohsin.ITI.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.Mohsin.ITI.OnClick
import com.Mohsin.ITI.adapter.PostsAdapter
import com.Mohsin.ITI.api.RetrofitClient
import com.Mohsin.ITI.api.UserApi
import com.Mohsin.ITI.model.Post
import com.Mohsin.ITI.model.User
import com.mohsin.iti.R
import com.mohsin.iti.databinding.ActivityPostsBinding


class PostsActivity : AppCompatActivity(), OnClick {
    private lateinit var adapter: PostsAdapter
    private lateinit var postList: ArrayList<Post>
    private lateinit var sharedPref :SharedPreferences
    private lateinit var binding: ActivityPostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        sharedPref=applicationContext.getSharedPreferences("UserPref", MODE_PRIVATE)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvName.text="welcome ${sharedPref.getString("USERNAME","")}"
        postList = ArrayList()
        postList.add(Post("User1", "2023-08-01", "post 1.", R.drawable.person))
        postList.add(Post("User2", "2023-08-02", "post 2.", R.drawable.android2))
        postList.add(Post("User3", "2023-08-03", "post 3.", R.drawable.android))


        val retrofit=RetrofitClient.getInstance().create(UserApi::class.java)
        lifecycleScope.launchWhenStarted { val response=retrofit.getUser()
        if(response.isSuccessful){
            adapter = PostsAdapter(response.body()?.data ?: listOf(),this@PostsActivity)
            binding.rvPosts.adapter = adapter
            binding.rvPosts.layoutManager = LinearLayoutManager(this@PostsActivity)
        }
            else{
                Toast.makeText(this@PostsActivity,"error",Toast.LENGTH_LONG).show()
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_posts_activity,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout -> {
              val editor = sharedPref.edit()
                editor.remove("USERNAME")
                editor.remove("PASSWORD")
                editor.putBoolean("IS_LOGIN",false)
                editor.commit()
                startActivity(Intent(this, SplashScreenActivity::class.java))
                finish()
                true
            }
            else -> {
              super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onClick(user: User, position: Int) {
        val intent=Intent(baseContext, MoreActivity::class.java)
        intent.putExtra("username",user.firstName)
        intent.putExtra("date",user.id)
        intent.putExtra("text",user.email)
        intent.putExtra("image",user.avatar)
        startActivity(intent)
    }
}