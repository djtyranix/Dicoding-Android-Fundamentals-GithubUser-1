package com.nixstudio.githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nixstudio.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var rvUser : RecyclerView
    private var list : ArrayList<User> = arrayListOf()
    var doubleBackToExitOnce : Boolean = false
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUser.setHasFixedSize(true)

        list.addAll(UserData(this@MainActivity).listUser)
        showRecyclerCardView()
    }

    private fun showRecyclerCardView() {
        setActionBarTitle("Github User List")
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(list, this@MainActivity)
        binding.rvUser.adapter = userAdapter

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data : User) {
                showItemDetail(data)
            }
        })
    }

    override fun onBackPressed() {
        if (doubleBackToExitOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitOnce = true

        Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            kotlin.run { doubleBackToExitOnce = false }
        }, 2000)
    }

    private fun showItemDetail(item : User) {
        val itemDetailIntent = Intent(this@MainActivity, ItemDetailActivity::class.java)
        itemDetailIntent.putExtra(ItemDetailActivity.EXTRA_USER, item)
        startActivity(itemDetailIntent)
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
}