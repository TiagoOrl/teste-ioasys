package com.assemblermaticstudio.ioasysclient.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.assemblermaticstudio.ioasysclient.R
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {

    private lateinit var fragment: View
    private lateinit var companyNameTv: TextView
    private lateinit var companyTypeTv: TextView
    private lateinit var countryTv: TextView
    private lateinit var detailsTv: TextView
    private lateinit var photoIv : ImageView

    private var companyName : String = ""
    private var companyType : String = ""
    private var country : String = ""
    private var details : String = ""
    private var photo : String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragment = inflater.inflate(R.layout.details_fragment, container, false)
        initViews()

        return fragment
    }

    override fun onResume() {
        super.onResume()

        companyNameTv.text = companyName
        companyTypeTv.text = companyType
        countryTv.text = country
        detailsTv.text = details
        Glide.with(fragment.context)
            .load(photo)
            .error(R.drawable.ic_eye_on)
            .into(photoIv)
    }

    fun setFragmentData(companyName: String, companyType: String, country: String, details: String, photo: String) {
        this.companyName = companyName
        this.companyType = companyType
        this.country = country
        this. details = details
        this.photo = photo
    }


    private fun initViews() {
        companyNameTv = fragment.findViewById(R.id.details_companyNameTv)
        companyTypeTv = fragment.findViewById(R.id.details_businessTypeTv)
        countryTv = fragment.findViewById(R.id.details_countryNameTv)
        detailsTv = fragment.findViewById(R.id.details_textTv)
        photoIv = fragment.findViewById(R.id.details_card_img)
    }

    companion object {
        fun newInstance() : DetailsFragment {
            return DetailsFragment()
        }
    }

}