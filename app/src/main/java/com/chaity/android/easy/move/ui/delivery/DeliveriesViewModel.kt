

package com.chaity.android.easy.move.ui.delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.chaity.android.easy.move.model.Deliveries
import com.chaity.android.easy.move.data.DeliveryRepository
import com.chaity.android.easy.move.model.DeliveriesResult
import javax.inject.Inject

/**
 * ViewModel for the [DeliveriesActivity] screen.
 * The ViewModel works with the [DeliveryRepository] to get the data.
 */
class DeliveriesViewModel @Inject constructor (private val repository: DeliveryRepository) : ViewModel() {

    private val repoResult = MutableLiveData<DeliveriesResult>()
    val repos: LiveData<PagedList<Deliveries>>
    val networkErrors: LiveData<String>



    init {
        repoResult.postValue( repository.getDeliveries())
        repos  = Transformations.switchMap(repoResult) { it -> it.data }
        networkErrors=Transformations.switchMap(repoResult) { it ->
            it.networkErrors
        }
    }


}
