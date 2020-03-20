package com.dsm.order

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.checkSelfPermission
import com.dsm.androidcomponent.base.BaseDialog
import com.dsm.model.Order
import com.dsm.order.databinding.DialogOrderDetailBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class OrderDetailDialog : BaseDialog<DialogOrderDetailBinding>() {
    companion object {
        private const val PHONE_CALL_CODE = 203
    }

    override val layoutResId: Int = R.layout.dialog_order_detail

    private val order: Order by lazy { arguments?.getSerializable("order") as Order }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupCall()

        binding.order = order
    }

    private fun setupRecyclerView() {
        binding.rvOrdered.adapter = GroupAdapter<GroupieViewHolder>()
    }

    private fun setupCall() {
        binding.btnCall.setOnClickListener {
            if (isCallPermissionGranted()) {
                callToUser()
                dismiss()
            } else {
                requestCallPermission()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PHONE_CALL_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callToUser()
            } else {
                dialToUser()
            }
            dismiss()
        }
    }

    private fun callToUser() {
        // val tel = "tel:$order.phone"
        val tel = "tel:010329099751"
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse(tel)))
    }

    private fun dialToUser() {
        val tel = "tel:010329099751"
        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(tel)))
    }

    private fun isCallPermissionGranted(): Boolean =
        checkSelfPermission(context!!, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED

    private fun requestCallPermission() =
        requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), PHONE_CALL_CODE)
}