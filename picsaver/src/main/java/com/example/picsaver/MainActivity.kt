package com.example.picsaver

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picsaver.Adapter.MyHitAdapter
import com.example.picsaver.Common.Common
import com.example.picsaver.Interface.RetrofitServices
import com.example.picsaver.Model.Pix
import com.example.picsaver.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mService: RetrofitServices
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyHitAdapter
    lateinit var dialog: AlertDialog

    var tvOut: TextView? = null
    var btnSearch: Button? = null
    var inputText: TextInputEditText? = null
    var to2act: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnSearch = findViewById(R.id.search_button);
        tvOut = findViewById(R.id.textView2)
        inputText = findViewById(R.id.inputText)

        mService = Common.retrofitService
        recyclerHitList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerHitList.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

    }

    fun to2act(view: View) {
        val intent = Intent(this, Activity_two::class.java)
        startActivity(intent)
    }

    fun search(view: View) {
        val myToast = Toast.makeText(this, "Ожидайте результаты", Toast.LENGTH_SHORT)
        myToast.show()
        tvOut?.text = "Результаты по запросу: " + inputText?.text
        getAllPictureList(inputText?.text.toString())
    }


    private fun getAllPictureList(q: String) {
        dialog.show()
        mService.getPictureList(q, "photo").enqueue(object : Callback<Pix> {
            override fun onFailure(call: Call<Pix>, t: Throwable) {
            }
            override fun onResponse(call: Call<Pix>, response: Response<Pix>) {
                adapter = MyHitAdapter(baseContext, response.body() as Pix)
                adapter.notifyDataSetChanged()
                recyclerHitList.adapter = adapter

                dialog.dismiss()
            }
        })
    }
}