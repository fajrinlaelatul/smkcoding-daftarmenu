package com.example.mydaftar.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydaftar.R
import com.example.mydaftar.R.layout
import com.example.mydaftar.data.MenuModel
import kotlinx.android.synthetic.main.makanan_fragment.rv_makanan

class MakananFragment : Fragment() {

    companion object{
        fun getInstance(): MakananFragment {
            return MakananFragment()
        }
    }

    val dataMakanan= mutableListOf<MenuModel>()
    val rvAdapter=RvAdapter(dataMakanan)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            layout.makanan_fragment,
            container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_makanan.adapter=rvAdapter
        rv_makanan.layoutManager=
            LinearLayoutManager(context)

        addDummyData()
    }

    private fun addDummyData() {
        dataMakanan.add(MenuModel("Mie kepang", "Rp25.000",
            R.drawable.mie_kepang))
        dataMakanan.add(MenuModel("Ramen", "Rp30.000",
            R.drawable.ramen))
        dataMakanan.add(MenuModel("Yamie", "Rp35.000",
            R.drawable.yamie_panda))

        rvAdapter.notifyDataSetChanged()
    }
}