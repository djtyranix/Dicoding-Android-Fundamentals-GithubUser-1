package com.nixstudio.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nixstudio.githubuser.databinding.ActivityItemDetailBinding
import com.nixstudio.githubuser.databinding.ActivityMainBinding

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityItemDetailBinding

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        binding.tvUserName.text = user.name
        binding.tvUserUsername.text = "u/${user.username}"
        binding.tvUserCompany.text = "Company: ${user.company}"
        binding.tvUserDetail.text = "Repo Count: ${user.repository}\nFollowers: ${user.follower}\nFollowing: ${user.following}"

        val userPhotoId = this.resources.getIdentifier(user.avatar, "drawable", this.packageName)

        Glide.with(this)
            .load(userPhotoId)
            .apply(RequestOptions().override(550, 550))
            .into(binding.imgUserPhoto)

        setActionBarTitle(user.name)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
}