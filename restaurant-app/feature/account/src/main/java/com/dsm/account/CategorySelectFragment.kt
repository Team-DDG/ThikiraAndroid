package com.dsm.account

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import com.dsm.account.databinding.FragmentCategorySelectBinding
import com.dsm.account.viewModel.RegisterViewModel
import com.dsm.androidcomponent.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategorySelectFragment : BaseFragment<FragmentCategorySelectBinding>() {
    override val layoutResId: Int = R.layout.fragment_category_select

    private val viewModel: RegisterViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupChildView()
    }

    private fun setupChildView() {
        binding.llCategory.children.forEach {
            it.setOnClickListener { view ->
                viewModel.setCategory((view as TextView).text.toString())
                findNavController().popBackStack()
            }
        }
    }
}