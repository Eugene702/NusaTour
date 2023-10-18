package com.eugenefeilianputrarangga.dicoding.nusatour.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.eugenefeilianputrarangga.dicoding.nusatour.R
import com.eugenefeilianputrarangga.dicoding.nusatour.activity.about.AboutActivity
import com.eugenefeilianputrarangga.dicoding.nusatour.activity.detail.DetailActivity
import com.eugenefeilianputrarangga.dicoding.nusatour.databinding.ActivityMainBinding
import com.eugenefeilianputrarangga.dicoding.nusatour.model.Tour

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val data: ArrayList<Tour> = arrayListOf()
        resources.getStringArray(R.array.tour_name).mapIndexed { index, s ->
            data.add(Tour(
                photo = resources.getStringArray(R.array.tourist_photos)[index],
                title = s,
                description = resources.getStringArray(R.array.tour_description)[index],
                price = resources.getStringArray(R.array.tour_price)[index]
            ))
        }

        val recyclerAdapter = RecyclerAdapter(data)
        recyclerAdapter.setOnItemClickCallback(object: RecyclerAdapter.OnItemClickCallback{
            override fun onItemClick(data: Tour) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(intent)
            }
        })

        binding.recyclerView.adapter = recyclerAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about -> startActivity(Intent(this@MainActivity, AboutActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}