package com.dsm.restaurant.presentation.ui.category

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentCategoryBinding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    override val layoutResId: Int = R.layout.fragment_category

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_category.setNavigationOnClickListener { findNavController().popBackStack() }
    }
}