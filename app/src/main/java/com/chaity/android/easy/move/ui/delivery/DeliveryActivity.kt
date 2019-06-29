package com.chaity.android.easy.move.ui.delivery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import com.chaity.android.easy.move.BuildConfig.BUNDLE_KEY_DELIVERY
import com.chaity.android.easy.move.R
import com.chaity.android.easy.move.listener.DeliveryItemClickListener
import com.chaity.android.easy.move.model.Delivery
import com.chaity.android.easy.move.ui.base.BaseActivity
import com.chaity.android.easy.move.ui.map.MapsActivity
import kotlinx.android.synthetic.main.activity_delivery.*
import javax.inject.Inject

class DeliveryActivity : BaseActivity<DeliveriesViewModel>(), DeliveryItemClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    var adapter =  DeliveryAdapter(this)

    private var viewModel: DeliveriesViewModel? = null
    private var showProgres: Boolean = false


    override fun getViewModel(): DeliveriesViewModel {
        // get the view model
        viewModel = ViewModelProviders.of(this, factory)
                .get(DeliveriesViewModel::class.java)

        return viewModel as DeliveriesViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        // get the view model
        getViewModel()


        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)

        initAdapter()

    }


    private fun initAdapter() {
        list.adapter = adapter
        viewModel?.repos?.observe(this, Observer<PagedList<Delivery>> {
            Log.d("Activity", "list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter?.submitList(it)
        })
        viewModel?.networkErrors?.observe(this, Observer<String> {
            Toast.makeText(this, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
        })

        viewModel?.loader?.observe(this, Observer {
            showProgres = it

            showHideProgress(showProgres)
        })
    }

    private fun showHideProgress(showProgres: Boolean?) {
        if (showProgres!!) {
            progressBar.visibility = View.VISIBLE
        } else progressBar.visibility = View.GONE


    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            list.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            list.visibility = View.VISIBLE
        }
    }


    override fun onItemClicked(delivery: Delivery) {
        delivery?.let { delivery ->
            val intent = Intent(this, MapsActivity::class.java).putExtra(BUNDLE_KEY_DELIVERY, delivery)
            startActivity(intent)
        }

    }
}
