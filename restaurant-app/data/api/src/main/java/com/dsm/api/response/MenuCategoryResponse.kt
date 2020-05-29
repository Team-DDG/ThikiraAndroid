package com.dsm.api.response

import com.google.gson.annotations.SerializedName

data class MenuCategoryResponse(

    @SerializedName("mc_id")
    val menuCategoryId: Int,

    val name: String
)