package com.dsm.menu.binding

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dsm.androidcomponent.R
import com.dsm.menu.adapter.MenuOptionListAdapter
import com.dsm.menu.item.*
import com.dsm.menu.viewModel.MenuCategorySelectViewModel
import com.dsm.menu.viewModel.MenuCategoryViewModel
import com.dsm.model.Menu
import com.dsm.model.MenuCategory
import com.dsm.model.MenuGroupItem
import com.dsm.model.MenuOption
import com.xwray.groupie.GroupAdapter

@BindingAdapter("spinnerEntries")
internal fun Spinner.bindSpinnerEntries(dataLiveData: LiveData<List<MenuCategory>>) {
    this.adapter = ArrayAdapter<String>(
        this.context,
        R.layout.support_simple_spinner_dropdown_item,
        dataLiveData.value?.map { it.name }?.toTypedArray() ?: arrayOf("")
    )
}

@BindingAdapter("menus")
internal fun RecyclerView.bindMenus(menusLiveData: LiveData<List<Menu>>) {
    menusLiveData.value?.let {
        (this.adapter as GroupAdapter).update(it.map { MenuItem(it) })
    }
}

enum class MenuCategoryViewType {
    NORMAL,
    DELETE,
    UPDATE
}

@BindingAdapter("menuCategories", "viewType", "viewModel", "menuCategorySelectViewModel")
internal fun RecyclerView.bindMenuCategories(
    menuCategoriesLiveData: LiveData<List<MenuCategory>>,
    viewType: LiveData<MenuCategoryViewType>,
    viewModel: MenuCategoryViewModel,
    menuCategorySelectViewModel: MenuCategorySelectViewModel
) {
    menuCategoriesLiveData.value?.let {
        (this.adapter as GroupAdapter).update(it.map { menuCategory ->
            when (viewType.value!!) {
                MenuCategoryViewType.NORMAL -> MenuCategoryItem(menuCategory, menuCategorySelectViewModel)
                MenuCategoryViewType.DELETE -> MenuCategoryDeleteItem(menuCategory, viewModel)
                MenuCategoryViewType.UPDATE -> MenuCategoryUpdateItem(menuCategory, viewModel)
            }
        })
    }
}

@BindingAdapter("options")
internal fun RecyclerView.bindOptions(
    optionsLiveData: LiveData<ArrayList<MenuOption>>
) {
    optionsLiveData.value?.let {
        (this.adapter as MenuOptionListAdapter).listItems = it
    }
}

@BindingAdapter("menuDetailOptions")
internal fun RecyclerView.bindMenuDetailOptions(
    menuGroups: List<MenuGroupItem>
) {
    (this.adapter as? GroupAdapter)?.let { adapter ->
        menuGroups.forEach { groupItem ->
            adapter.add(MenuDetailGroupItem(groupItem))
            groupItem.option.forEach { optionItem ->
                adapter.add(MenuDetailOptionItem(optionItem))
            }
        }
    }
}