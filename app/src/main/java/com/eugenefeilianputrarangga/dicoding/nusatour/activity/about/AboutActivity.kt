package com.eugenefeilianputrarangga.dicoding.nusatour.activity.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.eugenefeilianputrarangga.dicoding.nusatour.R
import com.eugenefeilianputrarangga.dicoding.nusatour.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@AboutActivity, R.layout.activity_about)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        Glide
            .with(this@AboutActivity)
            .load(resources.getString(R.string.my_photo))
            .into(binding.image)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}