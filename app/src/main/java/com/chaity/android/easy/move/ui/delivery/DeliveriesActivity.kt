/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chaity.android.easy.move.ui.delivery

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    override fun getViewModel(): DeliveriesViewModel {
        // get the view model
        viewModel = ViewModelProviders.of(this, factory)
                .get(DeliveriesViewModel::class.java)

        return viewModel as DeliveriesViewModel
    }

    @Inject lateinit  var factory: ViewModelProvider.Factory

    private var viewModel: DeliveriesViewModel? = null


    @Inject lateinit var adapter:DeliveryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        // get the view model
        getViewModel()

        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)

        initAdapter()
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        /*  viewModel.searchRepo(query)
          initSearch(query)*/
    }

    /*  override fun onSaveInstanceState(outState: Bundle) {
          super.onSaveInstanceState(outState)
          outState.putString(LAST_SEARCH_QUERY, viewModel.lastQueryValue())
      }*/

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
/*
    private fun initSearch(query: String) {
        search_repo.setText(query)

        search_repo.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateRepoListFromInput()
                true
            } else {
                false
            }
        }
        search_repo.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRepoListFromInput()
                true
            } else {
                false
            }
        }
    }

    private fun updateRepoListFromInput() {
        search_repo.text.trim().let {
            if (it.isNotEmpty()) {
                list.scrollToPosition(0)
                viewModel.searchRepo(it.toString())
                adapter.submitList(null)
            }
        }
    }*/

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            list.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            list.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "Delivery"
    }
}
