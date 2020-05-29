package com.dsm.menu

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.menu.databinding.FragmentMenuDetailBinding
import com.dsm.menu.viewModel.MenuDetailViewModel
import com.dsm.model.Menu
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuDetailFragment : BaseFragment<FragmentMenuDetailBinding>() {
    override val layoutResId: Int = R.layout.fragment_menu_detail

    private val viewModel: MenuDetailViewModel by viewModel()

    private val menu: Menu by lazy { arguments?.getSerializable("menu") as Menu }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupPopup()
        setupToastEvent(viewModel.toastEvent)
        setupNavigate()

        binding.menu = menu
    }

    private fun setupRecyclerView() {
        binding.rvOption.adapter = GroupAdapter<GroupieViewHolder>()
    }

    private fun setupPopup() {
        binding.ivPopup.setOnClickListener {
            PopupMenu(activity, it).apply {
                menu.add(R.string.delete_menu)

                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.title.toString()) {
                        getString(R.string.delete_menu) -> viewModel.onClickDeleteMenu(this@MenuDetailFragment.menu.menuId)
                    }
                    true
                }

                show()
            }
        }
    }

    private fun setupNavigate() {
        viewModel.popEvent.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }
}
