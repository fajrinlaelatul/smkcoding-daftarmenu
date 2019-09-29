package com.example.mydaftar.main

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings.Global
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mydaftar.R
import com.example.mydaftar.data.MenuDB
import com.example.mydaftar.data.MenuMakananModel
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.request.PermissionRequest
import kotlinx.android.synthetic.main.layout_data.bt_simpan
import kotlinx.android.synthetic.main.layout_data.et_harga
import kotlinx.android.synthetic.main.layout_data.et_nama
import kotlinx.android.synthetic.main.layout_data.ib_makanan
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.IOException

class AddFragment: Fragment(),
        PermissionRequest.AcceptedListener,PermissionRequest.DeniedListener
{

    override fun onPermissionsAccepted(permissions: Array<out String>) {
        showMessageDialog()
    }

    override fun onPermissionsDenied(permissions: Array<out String>) {
    requestPermision()
    }

    companion object{
        fun getInstance():AddFragment{
            return AddFragment()
        }
    }
    val Galery=1
    val Camera=2
    var imageData:ByteArray?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_data, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
        val db=MenuDB.getInstance(context!!)


        ib_makanan.setOnClickListener{
            checkVersion()
        }
        bt_simpan.setOnClickListener {
            simpanData(db)
        }
    }

    private fun simpanData(db: MenuDB?): Job {
        return GlobalScope.launch {
            db?.menuDao()?.tambahMakanan(MenuMakananModel(
            namaMenu = et_nama.text.toString(),
                hargaMenu = et_harga.text.toString(),
                gambarMenu = imageData
            ))
        }
    }

    private fun checkVersion() {
        if(android.os.Build.VERSION.SDK_INT>=
                android.os.Build.VERSION_CODES.M){
            requestPermision()
        }else {
            showMessageDialog()
        }
}

    private fun requestPermision() {
        val request=permissionsBuilder(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE). build()
        request.acceptedListener(this)
        request.deniedListener(this)
        request.send()
    }

    private fun showMessageDialog() {
        val pictureDialog= AlertDialog.Builder(activity!!)
        pictureDialog.setTitle("Silahkan Pilih")
        val pictureDialogItems = arrayOf(
            "Ambil foto dari galeri",
        "Ambil foto dengan kamera")
        pictureDialog.setItems(pictureDialogItems) { dialog, which -> when (which){
            0 -> pilihGalery()
            1-> pilihKamera()
        } }
        pictureDialog.show()
    }

    private fun pilihKamera() {
    val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,Camera)
    }

    private fun pilihGalery() {
        val intent=Intent(Intent.ACTION_PICK,MediaStore.Images
            .Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,Galery)
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Galery) {
            if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap = MediaStore.Images.Media
                        .getBitmap(activity!!.contentResolver, contentURI)
                    ib_makanan.setImageBitmap(bitmap)

                val stream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
                    imageData = stream.toByteArray()

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(activity, "Failed!",
                        Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == Camera) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            ib_makanan.setImageBitmap(thumbnail)

            val stream = ByteArrayOutputStream()
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, stream)
            imageData = stream.toByteArray()
        }
    }
}
