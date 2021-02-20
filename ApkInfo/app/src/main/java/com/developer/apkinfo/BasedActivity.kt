package com.developer.apkinfo

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.developer.apkinfo.DialogHelper.showLoading
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.github.achmadhafid.lottie_dialog.dismissLottieDialog
import kotlinx.android.synthetic.main.activity_based.*
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout


class BasedActivity : AppCompatActivity() {
    private lateinit var rvData : RecyclerView
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_based)
        setSupportActionBar(toolbar)
        toolbar_layout.title = title

        rvData = findViewById(R.id.rv_item)

        showData()

    }


    //fungsi untuk menampikan data pada firestore
    fun showData() {
        showLoading()
        db.collection("Data").get()
            .addOnSuccessListener {
                val list = mutableListOf<ModelToko>()
                for (data in it){ list.add(data.toObject(ModelToko::class.java)) }
                populateData(list)
                dismissLottieDialog()

            }.addOnFailureListener {

            }
    }


    //fungsi untuk mengatur tata letak tampilan item didalam recyclerview
    private fun populateData(list: MutableList<ModelToko>) {
        val linearLayoutManager = ZoomRecyclerLayout(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        rvData.layoutManager = linearLayoutManager
        val gridDataAdapter = AdapterToko(list)
        rvData.adapter = gridDataAdapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvData) // Menambah recycle view
        rvData.isNestedScrollingEnabled = false
    }


}