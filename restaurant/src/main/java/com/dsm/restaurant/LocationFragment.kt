package com.dsm.restaurant

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.dsm.baseapp.BaseFragment
import kotlinx.android.synthetic.main.fragment_location.*

class LocationFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_location

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_location.setNavigationOnClickListener { findNavController().popBackStack() }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = LocationListAdapter()
        rv_location.adapter = adapter
        rv_location.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        adapter.locationItems = listOf(
            LocationModel("지번지번지번지번", "도로명도로명도로명도로명도로명도로명"),
            LocationModel("지번지번지번지번지번지번지번지번", "도로명도로명도로명도로명도로명도로명도로명도로명도로명도로명도로명도로명")
        )
    }
}