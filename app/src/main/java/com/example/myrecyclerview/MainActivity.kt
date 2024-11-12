package com.example.myrecyclerview

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rv_recipes: RecyclerView
    private val list = ArrayList<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_recipes = findViewById(R.id.rv_recipes)
        rv_recipes.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()

        val btnAboutMe: Button = findViewById(R.id.about_page)
        btnAboutMe.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutMeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getListHeroes(): ArrayList<Recipe> {
        val dataName = resources.getStringArray(R.array.data_title)
        val dataIngredients = resources.getStringArray(R.array.data_ingredients)
        val description = resources.getStringArray(R.array.data_description)
        val steps = resources.getStringArray(R.array.data_steps)
        val dataPhoto = resources.obtainTypedArray(R.array.data_image)
        val listRecipe = ArrayList<Recipe>()
        for (i in dataName.indices) {
            val hero = Recipe(
                dataName[i],
                dataIngredients[i],
                steps[i],
                description[i],
                dataPhoto.getResourceId(i, -1)
            )
            listRecipe.add(hero)
        }
        dataPhoto.recycle()
        return listRecipe
    }

    private fun showRecyclerList() {
        rv_recipes.layoutManager = LinearLayoutManager(this)
        val listRecipeAdapter = ListRecipeAdapter(list)
        rv_recipes.adapter = listRecipeAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rv_recipes.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rv_recipes.layoutManager =  GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
