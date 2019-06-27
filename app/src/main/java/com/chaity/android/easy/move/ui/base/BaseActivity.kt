package com.chaity.android.easy.move.ui.base


import android.os.Bundle
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity<T : ViewModel> : DaggerAppCompatActivity() {

    private var viewModel: T? = null

    /**
     *
     * @return view model instance
     */
    abstract fun getViewModel(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = if (viewModel == null) getViewModel() else viewModel
    }

}
