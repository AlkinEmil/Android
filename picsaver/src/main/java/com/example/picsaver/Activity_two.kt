package com.example.picsaver

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picsaver.Adapter.MyFileAdapter
import com.example.picsaver.Common.Common
import com.example.picsaver.Interface.RetrofitServices
import com.example.picsaver.databinding.ActivityTwoBinding
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_two.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Activity_two : AppCompatActivity() {

    private lateinit var binding: ActivityTwoBinding

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyFileAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_two)


        binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerFileList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerFileList.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()


        dialog.show()
        val fileList : Array<String> = this.fileList()

        adapter = MyFileAdapter(baseContext, fileList)
        adapter.notifyDataSetChanged()
        recyclerFileList.adapter = adapter

        dialog.dismiss()

        var counter: TextView = findViewById(R.id.textView3)
        counter.text = counter.text.toString() + " (" + this.fileList().size.toString() + ")"
    }


    fun to1act(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}