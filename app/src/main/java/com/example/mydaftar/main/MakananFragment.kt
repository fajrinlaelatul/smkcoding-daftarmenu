package com.example.mydaftar.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydaftar.R.layout
import com.example.mydaftar.adapter.RvAdapterMakanan
import com.example.mydaftar.data.MenuDB
import com.example.mydaftar.data.MenuDB.Companion
import com.example.mydaftar.data.MenuMakananModel
import kotlinx.android.synthetic.main.makanan_fragment.rv_makanan

class MakananFragment : Fragment() {

    companion object{
        fun getInstance(): MakananFragment {
            return MakananFragment()
        }
    }

    val dataMakanan= mutableListOf<MenuMakananModel>()
     var  mRvAdapterMakanan= RvAdapterMakanan(dataMakanan)
    var db: MenuDB?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            layout.makanan_fragment,
            container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_makanan.adapter=mRvAdapterMakanan
        rv_makanan.layoutManager=
            LinearLayoutManager(context)
        db= com.example.mydaftar.data.MenuDB.getInstance(context!!)
        getMenuMakanan()
    }

    private fun getMenuMakanan() {
        db?.menuDao()?.ambilMenuMakanan()?.observe(this, Observer { hasil -> when(hasil.size==0){
            true->{
                Toast.makeText(
                context, "Data Makanan Masih Kosong",
                Toast.LENGTH_SHORT).show()
            }
            false-> {
                dataMakanan.clear()
                dataMakanan.addAll(hasil)
                mRvAdapterMakanan.notifyDataSetChanged()
            }}})}}