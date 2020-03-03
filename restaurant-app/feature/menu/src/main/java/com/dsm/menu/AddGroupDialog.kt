package com.dsm.menu

import android.os.Bundle
import android.view.View
import com.dsm.androidcomponent.base.BaseDialog
import com.dsm.menu.databinding.DialogAddGroupBinding
import com.dsm.menu.viewModel.MenuRegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddGroupDialog : BaseDialog<DialogAddGroupBinding>() {
    override val layoutResId: Int = R.layout.dialog_add_group

    private val viewModel: MenuRegistrationViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val maxCount = binding.etMaxCount.text.toString().toInt()
            viewModel.onClickAddGroup(name, maxCount)
            dismiss()
        }
    }
}