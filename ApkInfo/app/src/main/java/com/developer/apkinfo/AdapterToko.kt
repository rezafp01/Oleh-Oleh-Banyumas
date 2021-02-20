package com.developer.apkinfo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mapbox.mapboxsdk.geometry.LatLng

class AdapterToko(private val listData: List<ModelToko>) :
    RecyclerView.Adapter<AdapterToko.ViewHolder>() {
    //kumpulan data yang ingin ditampilkan
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama: TextView = itemView.findViewById(R.id.tvNama)
        var tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
        var tvDeskripsi: TextView = itemView.findViewById(R.id.tvDeskripsi)
        var ivGambar: ImageView = itemView.findViewById(R.id.ivGambar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.tvNama.text = data.NamaToko
        holder.tvAlamat.text = data.AlamatToko
        holder.tvDeskripsi.text = data.Deskripsi
        Glide.with(holder.itemView.context)
            .load(data.GambarToko)
            .apply(RequestOptions().fitCenter())
            .into(holder.ivGambar)


        holder.itemView.setOnClickListener() {
            val context = holder.itemView.context
            context.startActivity(
                Intent(context, MapboxActivity::class.java)
                    .apply {
                        //mengirim data toLatling pada mapbox
                        putExtra(MapboxActivity.EXTRA_COORDINATE, data.coordinate.toLatLng()) }
            )
        }
    }
    // create fungsi tersendiri untuk latitude dan langtitude
    private fun List<Double>?.toLatLng(): LatLng = LatLng(this?.get(0)!!, this[1])
}


