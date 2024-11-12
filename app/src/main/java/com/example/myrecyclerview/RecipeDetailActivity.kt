package com.example.myrecyclerview

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailActivity : AppCompatActivity() {
    private var recipeTitle: String? = null
    private var recipeIngredients: String? = null
    private var recipeSteps: String? = null
    private var recipeDescription: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recipeTitle = intent.getStringExtra("EXTRA_TITLE")
        recipeIngredients = intent.getStringExtra("EXTRA_INGREDIENTS")
        recipeSteps = intent.getStringExtra("EXTRA_STEPS")
        recipeDescription = intent.getStringExtra("EXTRA_DESCRIPTION")
        val recipeImage = intent.getIntExtra("EXTRA_IMAGE", 0)

        val imgPhoto: ImageView = findViewById(R.id.img_detail_photo)
        val tvTitle: TextView = findViewById(R.id.tv_detail_title)
        val tvDescription: TextView = findViewById(R.id.tv_detail_description)
        val tvIngredients: TextView = findViewById(R.id.tv_detail_ingredients)
        val tvSteps: TextView = findViewById(R.id.tv_detail_steps)

        tvTitle.text = recipeTitle
        tvIngredients.text = recipeIngredients
        tvDescription.text = recipeDescription
        tvSteps.text = recipeSteps
        imgPhoto.setImageResource(recipeImage)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_share -> {
                shareRecipe()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareRecipe() {
        val shareText = buildString {
            append("$recipeTitle\n\n")
            append("Deskripsi:\n$recipeDescription\n\n")
            append("Bahan-bahan:\n$recipeIngredients\n\n")
            append("Cara Membuat:\n$recipeSteps")
        }

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }

        startActivity(Intent.createChooser(shareIntent, "Bagikan Resep"))
    }
}