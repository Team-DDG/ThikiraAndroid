package com.dsm.menu

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.menu.databinding.FragmentAddMenuCategoryBinding
import com.dsm.menu.viewModel.AddMenuCategoryViewModel
import kotlinx.android.synthetic.main.fragment_add_menu_category.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddMenuCategoryFragment : BaseFragment<FragmentAddMenuCategoryBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_add_menu_category

    private val viewModel: AddMenuCategoryViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNavigate()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        tb_add_menu_category.setNavigationOnClickListener { findNavController().popBackStack() }

        viewModel.popEvent.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }
}