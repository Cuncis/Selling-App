package com.cuncis.sellingapp.ui.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.category.Kategori
import com.cuncis.sellingapp.util.Constants
import com.cuncis.sellingapp.util.Utils.Companion.setGlideImage
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(val context: Context, var categoryList: ArrayList<Kategori>,
                      val clickListener: (Kategori, Int) -> Unit)
    : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false))
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoryList[position])
        context.setGlideImage(holder.view.imvImage, categoryList[position].gambarKategori!!, holder.view.progressBar)
        holder.view.relCategory.setOnClickListener {
            Constants.CATEGORY_ID = categoryList[position].kdKategori!!
            clickListener(categoryList[position], position)
        }
    }

    fun setData(newCategory: List<Kategori>) {
        categoryList.clear()
        categoryList.addAll(newCategory)
        notifyDataSetChanged()
    }

    inner class CategoryHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(category: Kategori) {
            view.txvCategory.text = category.kategori
        }
    }
}