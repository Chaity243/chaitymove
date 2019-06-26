package com.chaity.android.easy.move.ui.map


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MapsViewModelFactory(private val mApplication: Application, private val id: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MapsViewModel(mApplication, id) as T
    }
}