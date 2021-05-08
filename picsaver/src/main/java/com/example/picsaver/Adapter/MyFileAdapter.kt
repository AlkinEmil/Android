package com.example.picsaver.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.picsaver.R
import kotlinx.android.synthetic.main.item_layout2.view.*
import java.io.ByteArrayOutputStream

class MyFileAdapter(private val context: Context, private val fileList: Array<String>): RecyclerView.Adapter<MyFileAdapter.MyViewHolder2>() {

    class MyViewHolder2(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.image2
        val delete_button: Button = itemView.button5
        var name: TextView = itemView.file_name

        fun bind(listItem: String) {
            delete_button.setOnClickListener {
                Toast.makeText(it.context,
                    "Удаление картинки $listItem", Toast.LENGTH_SHORT).show()
                it.context.deleteFile(listItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout2, parent, false)
        return MyViewHolder2(itemView)
    }

    override fun getItemCount() = fileList.size

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        if (position < fileList.size) {
            val listItem = fileList[position]
            holder.bind(listItem)
            holder.name.text = listItem
            val filename = listItem
            val byteArray = context.openFileInput(filename).readBytes()
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            val image: ImageView = holder.image
            image.setImageBitmap(
                Bitmap.createScaledBitmap(
                    bmp,
                    500,
                    400,
                    false
                )
            )
        }
    }


}