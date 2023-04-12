package com.masdika.hermanbakso.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.masdika.hermanbakso.R
import com.masdika.hermanbakso.model.MenuMakananData

class MenuAdapter(private val menuList: ArrayList<MenuMakananData>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_menu, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val currentItem = menuList[position]
        holder.menuTitle.text = currentItem.menuTitle
        holder.menuPrice.text = currentItem.menuPrice
        holder.menuImage.setImageResource(currentItem.menuImage)
    }

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val menuTitle: TextView = itemView.findViewById(R.id.menu_title)
        val menuPrice: TextView = itemView.findViewById(R.id.menu_price)
        val menuImage: ImageView = itemView.findViewById(R.id.img_menu)

    }
}
