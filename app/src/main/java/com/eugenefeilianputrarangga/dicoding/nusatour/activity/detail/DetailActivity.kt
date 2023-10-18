package com.eugenefeilianputrarangga.dicoding.nusatour.activity.detail

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.eugenefeilianputrarangga.dicoding.nusatour.R
import com.eugenefeilianputrarangga.dicoding.nusatour.databinding.ActivityDetailBinding
import com.eugenefeilianputrarangga.dicoding.nusatour.model.Tour

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object{
        const val EXTRA_DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@DetailActivity, R.layout.activity_detail)
        setSupportActionBar(binding.toolbar)

        val data = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_DATA, Tour::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if(data != null){
            supportActionBar?.apply {
                title = "Tentang ${data.title}"
                setDisplayHomeAsUpEnabled(true)
            }

            Glide
                .with(this@DetailActivity)
                .load(data.photo)
                .into(binding.image)

            binding.price.text = data.price
            binding.title.text = data.title
            binding.description.text = data.description
        }else{
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_share -> {
                val intent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, binding.description.text)
                    putExtra(Intent.EXTRA_TITLE, binding.title.text)
                    type = "text/plain"
                }

                startActivity(Intent.createChooser(intent, null))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}