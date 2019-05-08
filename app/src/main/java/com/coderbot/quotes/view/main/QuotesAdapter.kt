package com.coderbot.quotes.view.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.coderbot.quotes.R
import com.coderbot.quotes.model.Quote
import com.coderbot.quotes.utils.DateFormatter

class QuotesAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private var quotes = mutableListOf<Quote>()

    fun setData(experiences: MutableList<Quote>)
    {
        this.quotes = mutableListOf()
        this.quotes.addAll(experiences)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_quote_item, parent, false))
    }

    override fun getItemCount(): Int
    {
        return quotes.size
    }

    private fun getItem(position: Int): Quote
    {
        return quotes[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        val viewHolder = holder as ViewHolder
        val model = getItem(position)

        viewHolder.quote.text = model.quote
        viewHolder.date.text = DateFormatter.format(model.date)
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        @BindView(R.id.quote)
        lateinit var quote: TextView

        @BindView(R.id.date)
        lateinit var date: TextView

        init
        {
            ButterKnife.bind(this, itemView)
        }
    }
}