package com.assemblermaticstudio.ioasysclient.view.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assemblermaticstudio.ioasysclient.R
import com.assemblermaticstudio.ioasysclient.utils.createDialog
import com.assemblermaticstudio.ioasysclient.utils.createProgressDialog
import com.assemblermaticstudio.ioasysclient.view.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    lateinit var fragment: View
    private val viewModel by viewModel<SearchViewModel>()
    private val dialog by lazy { requireContext().createProgressDialog() }

    private lateinit var containerRv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment = inflater.inflate(R.layout.search_fragment, container, false)
        initViews()
        startViewModel()
        return fragment
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.main_menu, menu)
        val menuItem = menu.findItem(R.id.search_menu)
        val searchView = menuItem.actionView as SearchView
    }


    private fun initViews() {
        // init recycler  view
        containerRv = fragment.findViewById(R.id.container_rv)
        containerRv.layoutManager = LinearLayoutManager(requireActivity())

    }


    private fun startViewModel() {
        viewModel.output.observe(viewLifecycleOwner) {
            when (it) {

                is SearchViewModel.State.Loading -> {
                    dialog.show()
                }

                is SearchViewModel.State.Error -> {
                    requireContext().createDialog {
                        setMessage(it.msg)
                    }.show()
                    dialog.dismiss()
                }

                is SearchViewModel.State.Success -> {
                    dialog.dismiss()
                    viewModel.setIdleState()
                }


            }
        }
    }





    companion object {
        fun newInstance() : SearchFragment = SearchFragment()
    }

}