package com.dsm.restaurant.presentation.ui.register

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentCategorySelectBinding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_category_select.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategorySelectFragment : BaseFragment<FragmentCategorySelectBinding>() {
    override val layoutResId: Int = R.layout.fragment_category_select

    private val viewModel: RegisterViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_category.setNavigationOnClickListener { findNavController().popBackStack() }

        ll_category.children.forEach {
            it.setOnClickListener { view ->
                viewModel.onSelectCategory((view as TextView).text.toString())
                findNavController().popBackStack()
            }
        }
    }
}