package com.assemblermaticstudio.ioasysclient.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assemblermaticstudio.ioasysclient.R
import com.assemblermaticstudio.ioasysclient.model.enterprise.Enterprise
import com.bumptech.glide.Glide

class EnterprisesListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var enterprisesList: List<Enterprise> = mutableListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return EnterpriseViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.card_itemview, p0, false))
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        when (p0) {
            is EnterpriseViewHolder -> {
                p0.bind(enterprisesList[p1])
            }
        }
    }

    override fun getItemCount(): Int {
        return enterprisesList.size
    }


    fun submitList(list: List<Enterprise>) {
        this.enterprisesList = list
    }


    inner class EnterpriseViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val imgView = itemView.findViewById<ImageView>(R.id.card_img)
        val companyName = itemView.findViewById<TextView>(R.id.companyNameTv)
        val companyType = itemView.findViewById<TextView>(R.id.businessTypeTv)
        val country = itemView.findViewById<TextView>(R.id.countryNameTv)

        fun bind(enterprise: Enterprise) {
            Glide
                .with(itemView.context)
                .load(enterprise.photo)
                .error(R.drawable.ic_eye_on)
                .into(imgView)

            companyName.text = enterprise.name
            companyType.text = enterprise.enterpriseType.typeName
            country.text = String.format("{0}, {1]", enterprise.city, enterprise.country)
        }
    }
}