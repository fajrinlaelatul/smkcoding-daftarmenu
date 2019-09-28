package com.example.mydaftar.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydaftar.R
import com.example.mydaftar.data.MenuModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_menu.makanan_harga1
import kotlinx.android.synthetic.main.item_list_menu.makanan_image1
import kotlinx.android.synthetic.main.item_list_menu.makanan_nama1

class RvAdapter(private val data:List<MenuModel>) :
    RecyclerView.Adapter<RvAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
       return MenuViewHolder( LayoutInflater.from(parent.context)
           .inflate(R.layout.item_list_menu,parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    class MenuViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView)
    ,LayoutContainer
    {
        fun bindData(item:MenuModel){
            makanan_nama1.text=item.namaMenu
            makanan_harga1.text=item.hargaMenu
            makanan_image1.setImageResource(item.gambarMenu)
        }
    }
}