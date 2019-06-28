package com.chaity.android.easy.move.ui.delivery

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import com.chaity.android.easy.move.R
import com.chaity.android.easy.move.model.Deliveries
import com.chaity.android.easy.move.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_delivery.*
import javax.inject.Inject

class DeliveriesActivity : BaseActivity<DeliveriesViewModel>(){

    @Inject lateinit  var factory: ViewModelProvider.Factory
    @Inject lateinit var adapter:DeliveryAdapter

    private var viewModel: DeliveriesViewModel? = null




    override fun getViewModel(): DeliveriesViewModel {
        // get the view model
        viewModel = ViewModelProviders.of(this, factory)
                .get(DeliveriesViewModel::class.java)

        return viewModel as DeliveriesViewModel
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)
        setupBindings(savedInstanceState)



        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)

        initAdapter()

    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        val activityBinding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_delivery)

        // get the view model
        getViewModel()

        setupListUpdate()

    }


    private fun initAdapter() {
        list.adapter = adapter
        viewModel?.repos?.observe(this, Observer<PagedList<Deliveries>> {
            Log.d("Activity", "list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter?.submitList(it)
        })
        viewModel?.networkErrors?.observe(this, Observer<String> {
            Toast.makeText(this, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
        })
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

}
