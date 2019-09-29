package com.example.mydaftar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mydaftar.R.layout
import com.example.mydaftar.data.MenuMinumanModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_menu.makanan_harga1
import kotlinx.android.synthetic.main.item_list_menu.makanan_image1
import kotlinx.android.synthetic.main.item_list_menu.makanan_nama1

class RvAdapterMinuman(private val data:List<MenuMinumanModel>) :
    RecyclerView.Adapter<RvAdapterMinuman.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(layout.item_list_menu, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    class MenuViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView)
        , LayoutContainer {

        fun bindData(item: MenuMinumanModel) {
            makanan_nama1.text = item.namaMenu
            makanan_harga1.text = item.hargaMenu
            Glide.with(containerView).load(item.gambarMenu).into(makanan_image1)
        }
    }}
