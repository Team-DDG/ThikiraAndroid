package com.dsm.restaurant.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.databinding.ItemMenuOptionRegistrationAddGroupBinding
import com.dsm.restaurant.databinding.ItemMenuOptionRegistrationGroupBinding
import com.dsm.restaurant.databinding.ItemMenuOptionRegistrationOptionBinding
import com.dsm.restaurant.presentation.ui.main.menu.registration.MenuRegistrationViewModel

class MenuOptionRegistrationListAdapter(
    private val viewModel: MenuRegistrationViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ADD_GROUP = 0
        private const val TYPE_GROUP = 1
        private const val TYPE_OPTION = 2
    }

    sealed class MenuRegistrationOption {

        data class Group(val groupName: String, val maxCount: Int) : MenuRegistrationOption()

        data class Option(val optionName: String, val price: Int) : MenuRegistrationOption()

        object AddGroup : MenuRegistrationOption()
    }

    var listItems = arrayListOf<MenuRegistrationOption>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class AddGroupHolder(val binding: ItemMenuOptionRegistrationAddGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.viewModel = viewModel
        }
    }

    inner class GroupHolder(val binding: ItemMenuOptionRegistrationGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = listItems[adapterPosition] as MenuRegistrationOption.Group
            binding.viewModel = viewModel
            binding.position = adapterPosition
            binding.group = item
        }
    }

    inner class OptionHolder(val binding: ItemMenuOptionRegistrationOptionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = listItems[adapterPosition] as MenuRegistrationOption.Option
            binding.option = item
            binding.position = adapterPosition
            binding.viewModel = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_ADD_GROUP -> AddGroupHolder(ItemMenuOptionRegistrationAddGroupBinding.inflate(layoutInflater, parent, false))
            TYPE_GROUP -> GroupHolder(ItemMenuOptionRegistrationGroupBinding.inflate(layoutInflater, parent, false))
            TYPE_OPTION -> OptionHolder(ItemMenuOptionRegistrationOptionBinding.inflate(layoutInflater, parent, false))
            else -> AddGroupHolder(ItemMenuOptionRegistrationAddGroupBinding.inflate(layoutInflater, parent, false))
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
            is MenuRegistrationOption.AddGroup -> TYPE_ADD_GROUP
            is MenuRegistrationOption.Group -> TYPE_GROUP
            is MenuRegistrationOption.Option -> TYPE_OPTION
        }
}

