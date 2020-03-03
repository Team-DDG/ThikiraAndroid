package com.dsm.menu

import android.os.Bundle
import android.view.View
import com.dsm.androidcomponent.base.BaseDialog
import com.dsm.menu.databinding.DialogAddOptionBinding
import com.dsm.menu.viewModel.MenuRegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddOptionDialog : BaseDialog<DialogAddOptionBinding>() {
    override val layoutResId: Int = R.layout.dialog_add_option

    private val viewModel: MenuRegistrationViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupName = arguments!!.getString("groupName") ?: ""
        val position = arguments!!.getInt("position")

        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val price = binding.etPrice.text.toString().toInt()
            viewModel.onClickAddOption(name, price, position, groupName)
            dismiss()
        }
    }
}