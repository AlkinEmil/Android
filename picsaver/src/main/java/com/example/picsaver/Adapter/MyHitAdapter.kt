package com.example.picsaver.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.picsaver.Model.Hit
import com.example.picsaver.Model.Pix
import com.example.picsaver.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*
import java.io.ByteArrayOutputStream
import kotlin.math.min

class MyHitAdapter(private val context: Context, private val hitList: Pix):RecyclerView.Adapter<MyHitAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.image
        val user: TextView = itemView.txt_user
        val views: TextView = itemView.txt_views_count
        val tags: TextView = itemView.txt_tags
        val download_button: Button = itemView.button4

        private fun saveImageToInternalStorage() {
            val filename: String = user.text.toString() + itemView.context.fileList().size.toString()
            val bitmap = (image.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray: ByteArray = stream.toByteArray()
            itemView.context.openFileOutput(filename, Context.MODE_PRIVATE).use {
                it.write(byteArray)
            }

            //var file = wrapper.getDir("images", Context.MODE_PRIVATE)
            // Create a file to save the image
            //file = File(file, "${UUID.randomUUID()}.jpg")
            //try {
                // Get the file output stream
                //val stream: OutputStream = FileOutputStream(file)
                // Compress bitmap
                //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                // Flush the stream
                //stream.flush()
                // Close stream
                //stream.close()
            //} catch (e: IOException){ // Catch the exception
                //e.printStackTrace()
            //}

            // Return the saved image uri
            //return Uri.parse(file.absolutePath)
        }

        fun bind(listItem: Hit) {
            download_button.setOnClickListener {
                Toast.makeText(it.context,
                    "Скачивание картинки пользователя ${itemView.txt_user.text}", Toast.LENGTH_SHORT).show()
                saveImageToInternalStorage()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = min(hitList.totalHits, 20)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = hitList.hits?.get(position)
        if (listItem != null) {
            holder.bind(listItem)
            Picasso.get().load(listItem.previewURL).into(holder.image)
            holder.user.text = listItem.user
            holder.views.text = "Просмотры: " + listItem.views.toString()
            holder.tags.text = listItem.tags
        }
    }


}