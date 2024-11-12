package com.example.myrecyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerview.RecipeDetailActivity
import com.example.myrecyclerview.R

class ListRecipeAdapter(private val listRecipe: ArrayList<Recipe>) : RecyclerView.Adapter<ListRecipeAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_recipe, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, ingredients, steps, description, image) = listRecipe[position]
        holder.imgPhoto.setImageResource(image)
        holder.tvTitle.text = title
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, RecipeDetailActivity::class.java).apply {
                putExtra("EXTRA_TITLE", title)
                putExtra("EXTRA_INGREDIENTS", ingredients)
                putExtra("EXTRA_DESCRIPTION", description)
                putExtra("EXTRA_STEPS", steps)
                putExtra("EXTRA_IMAGE", image)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listRecipe.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }
}
