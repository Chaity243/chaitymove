

package com.chaity.android.easy.move.ui.delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.chaity.android.easy.move.model.Delivery
import com.chaity.android.easy.move.data.DeliveryRepository
import com.chaity.android.easy.move.model.DeliveriesResult
import javax.inject.Inject

/**
 * ViewModel for the [DeliveryActivity] screen.
 * The ViewModel works with the [DeliveryRepository] to get the data.
 */
class DeliveriesViewModel @Inject constructor (private val repository: DeliveryRepository) : ViewModel() {

    private val repoResult = MutableLiveData<DeliveriesResult>()
     var loader= MutableLiveData<Boolean>()
    val repos: LiveData<PagedList<Delivery>>
    val networkErrors: LiveData<String>



    init {
        repoResult.postValue( repository.getDeliveries())
        loader =repository.loader
        repos  = Transformations.switchMap(repoResult) { it -> it.data }
        networkErrors=Transformations.switchMap(repoResult) { it ->
            it.networkErrors
        }
    }


}
