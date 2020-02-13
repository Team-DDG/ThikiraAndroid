package com.dsm.restaurant.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.restaurant.R
import com.dsm.restaurant.data.remote.ThikiraApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val thikiraApi: ThikiraApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            thikiraApi.deleteMenuCategoryList(
                listOf(
                    0,1,3,4
                )
            )
        }
    }
}
