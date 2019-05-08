package com.coderbot.quotes.view.main

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.coderbot.quotes.R
import com.coderbot.quotes.utils.Views
import com.coderbot.quotes.viewmodel.QuotesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class QuotesActivity : AppCompatActivity()
{
    @BindView(R.id.quotes)
    protected lateinit var quotesList: RecyclerView

    private val viewModel: QuotesViewModel by viewModel()
    private lateinit var adapter: QuotesAdapter
    private lateinit var loading: Views.LoadingView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        ButterKnife.bind(this@QuotesActivity)

        loading = Views.LoadingView(this@QuotesActivity)

        initList()

        initListeners()

        getQuotes()
    }

    private fun initList()
    {
        quotesList.setHasFixedSize(true)
        quotesList.isFocusable = false
        quotesList.isNestedScrollingEnabled = false
        quotesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = QuotesAdapter(this)
        quotesList.adapter = adapter
        adapter.setData(mutableListOf())
    }

    private fun initListeners()
    {
        viewModel.observeQuotes().observe(this@QuotesActivity, Observer {
            loading.dismiss()
            if (it != null)
            {
                adapter.setData(it)
            }
        })

        viewModel.observeSuccess().observe(this@QuotesActivity, Observer {
            loading.dismiss()
        })

        viewModel.observeError().observe(this@QuotesActivity, Observer {
            loading.dismiss()
            if (it != null)
            {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getQuotes()
    {
        loading.show()
        viewModel.getQuotes()
    }
}
