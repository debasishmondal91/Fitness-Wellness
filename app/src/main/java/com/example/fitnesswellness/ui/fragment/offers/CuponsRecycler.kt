package com.example.fitnesswellness.ui.fragment.offers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesswellness.R
import com.example.fitnesswellness.data.model.Cupon

class CuponsRecycler(
    private val cupons: List<Cupon>,
    private val activity: FragmentActivity?
) : RecyclerView.Adapter<CuponsRecycler.MyCupons>() {


    class MyCupons(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
        val desc = view.findViewById<TextView>(R.id.desc)
        val price = view.findViewById<TextView>(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCupons {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cupons_layout, parent, false)
        return MyCupons(view)
    }

    override fun getItemCount(): Int = cupons.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyCupons, position: Int) {
        holder.title.text = cupons[position].title
        holder.desc.text = cupons[position].description
        holder.price.text = "Estimated savings : ${cupons[position].price}"
    }
}