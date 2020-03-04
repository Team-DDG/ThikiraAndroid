package com.dsm.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsm.menu.R
import com.dsm.menu.databinding.ItemAddGroupBinding
import com.dsm.menu.databinding.ItemGroupBinding
import com.dsm.menu.databinding.ItemOptionBinding
import com.dsm.menu.viewModel.MenuRegistrationViewModel
import com.dsm.model.MenuOption

class MenuOptionListAdapter(
    private val viewModel: MenuRegistrationViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ADD_GROUP = 0
        private const val TYPE_GROUP = 1
        private const val TYPE_OPTION = 2
    }

    var listItems = arrayListOf<MenuOption>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class AddGroupHolder(val binding: ItemAddGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.root.setOnClickListener {
                it.findNavController().navigate(R.id.action_menuRegistration2Fragment_to_addGroupDialog)
            }
        }
    }

    inner class GroupHolder(val binding: ItemGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = listItems[adapterPosition] as MenuOption.Group
            binding.group = item
            binding.ivAdd.setOnClickListener {
                it.findNavController().navigate(
                    R.id.action_menuRegistration2Fragment_to_addOptionDialog,
                    bundleOf("position" to adapterPosition, "groupName" to item.groupName)
                )
            }
            binding.ivDelete.setOnClickListener {
                viewModel.onClickDeleteGroup(adapterPosition)
            }
        }
    }

    inner class OptionHolder(val binding: ItemOptionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = listItems[adapterPosition] as MenuOption.Option
            binding.option = item
            binding.ivDelete.setOnClickListener {
                viewModel.onClickDeleteOption(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_ADD_GROUP -> AddGroupHolder(ItemAddGroupBinding.inflate(layoutInflater, parent, false))
            TYPE_GROUP -> GroupHolder(ItemGroupBinding.inflate(layoutInflater, parent, false))
            TYPE_OPTION -> OptionHolder(ItemOptionBinding.inflate(layoutInflater, parent, false))
            else -> AddGroupHolder(ItemAddGroupBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is AddGroupHolder -> holder.bind()
            is GroupHolder -> holder.bind()
            is OptionHolder -> holder.bind()
            else -> Unit
        }

    override fun getItemViewType(position: Int): Int =
        when (listItems[position]) {
            is MenuOption.AddGroup -> TYPE_ADD_GROUP
            is MenuOption.Group -> TYPE_GROUP
            is MenuOption.Option -> TYPE_OPTION
        }
}