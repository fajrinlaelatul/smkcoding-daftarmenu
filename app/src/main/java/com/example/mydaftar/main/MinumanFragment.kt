package com.example.mydaftar.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydaftar.R.layout
import com.example.mydaftar.adapter.RvAdapterMakanan
import com.example.mydaftar.data.MenuMakananModel
import kotlinx.android.synthetic.main.minuman_fragment.rv_minuman

class MinumanFragment :Fragment() {

    companion object{
        fun getInstance(): MinumanFragment {
            return MinumanFragment()
        }
    }
    val dataMinuman= mutableListOf<MenuMakananModel>()
    val rvAdapter= RvAdapterMakanan(dataMinuman)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            layout.minuman_fragment,
            container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_minuman.adapter=rvAdapter
        rv_minuman.layoutManager=
            LinearLayoutManager(context)
        }
    }