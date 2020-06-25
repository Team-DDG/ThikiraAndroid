package com.dsm.review

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.review.databinding.ActivityReviewBinding
import kotlin.reflect.KClass

class ReviewActivity : BaseActivity<ActivityReviewBinding>() {
    override val layoutResId: Int
        get() = R.layout.activity_review
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}