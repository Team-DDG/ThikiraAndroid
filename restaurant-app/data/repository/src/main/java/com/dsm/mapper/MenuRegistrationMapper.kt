package com.dsm.mapper

import com.dsm.api.request.GroupItem
import com.dsm.api.request.MenuRegistrationRequest
import com.dsm.api.request.OptionItem
import com.dsm.model.MenuOption
import com.dsm.model.MenuRegistration

internal fun MenuRegistration.toRequest() =
    MenuRegistrationRequest(
        menuCategoryId = menuCategoryId,
        price = price,
        name = name,
        description = description,
        image = image,
        group = mapGroupToRequest(group)
    )

private fun mapGroupToRequest(group: List<MenuOption>): List<GroupItem> {
    val list = arrayListOf<GroupItem>()
    group.forEach {
        if (it is MenuOption.Group) {
            list.add(GroupItem(name = it.groupName, maxCount = it.maxCount, option = arrayListOf()))
        } else if (it is MenuOption.Option) {
            (list[list.size - 1].option as ArrayList).add(OptionItem(name = it.optionName, price = it.price))
        }
    }
    return list
}