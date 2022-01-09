package com.assemblermaticstudio.ioasysclient.view.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.assemblermaticstudio.ioasysclient.R

class SearchFragment : Fragment() {

    lateinit var fragment: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment = inflater.inflate(R.layout.search_fragment, container, false)
        initViews()
        return fragment
    }

    private fun initViews() {
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        requireActivity().menuInflater.inflate(R.menu.main_menu, menu)
        val menuItem = menu.findItem(R.id.search_menu)
        val searchView = menuItem.actionView as SearchView
    }


    companion object {
        fun newInstance() : SearchFragment = SearchFragment()
    }

}