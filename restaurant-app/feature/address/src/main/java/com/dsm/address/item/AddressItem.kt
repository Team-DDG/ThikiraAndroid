package com.dsm.address.item

import com.dsm.address.R
import com.dsm.address.databinding.ItemAddressBinding
import com.dsm.address.viewModel.AddressSearchViewModel
import com.dsm.model.Address
import com.xwray.groupie.databinding.BindableItem

class AddressItem(
    private val address: Address,
    private val addressSearchViewModel: AddressSearchViewModel
) : BindableItem<ItemAddressBinding>() {

    override fun getLayout(): Int = R.layout.item_address

    override fun bind(viewBinding: ItemAddressBinding, position: Int) {
        viewBinding.address = address
        viewBinding.viewModel = addressSearchViewModel
    }
}