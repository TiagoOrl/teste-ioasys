package com.assemblermaticstudio.ioasysclient.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.assemblermaticstudio.ioasysclient.R
import com.assemblermaticstudio.ioasysclient.model.enterprise.Enterprise
import com.assemblermaticstudio.ioasysclient.view.fragment.DetailsFragment
import com.assemblermaticstudio.ioasysclient.view.fragment.FragmentDirector
import com.bumptech.glide.Glide
import java.text.MessageFormat

class EnterprisesListAdapter(private val fragmentManager: FragmentManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        val imgView : ImageView = itemView.findViewById<ImageView>(R.id.card_img)
        val companyName : TextView = itemView.findViewById<TextView>(R.id.companyNameTv)
        val companyType : TextView = itemView.findViewById<TextView>(R.id.businessTypeTv)
        val country : TextView = itemView.findViewById<TextView>(R.id.countryNameTv)
        private val detailsFragment : DetailsFragment = DetailsFragment.newInstance()

        fun bind(enterprise: Enterprise) {
            Glide
                .with(itemView.context)
                .load("https://empresas.ioasys.com.br" + enterprise.photo)
                .error(R.drawable.ic_eye_on)
                .into(imgView)

            companyName.text = enterprise.name
            companyType.text = enterprise.enterpriseType.typeName
            country.text = MessageFormat.format("{0}, {1}", enterprise.city, enterprise.country)

            itemView.setOnClickListener {
                detailsFragment.setFragmentData(
                    enterprise.name,
                    enterprise.enterpriseType.typeName,
                    MessageFormat.format("{0}, {1}", enterprise.city, enterprise.country),
                    enterprise.description,
                    "https://empresas.ioasys.com.br" + enterprise.photo
                )
                FragmentDirector.replace(fragmentManager, detailsFragment)
            }
        }
    }
}