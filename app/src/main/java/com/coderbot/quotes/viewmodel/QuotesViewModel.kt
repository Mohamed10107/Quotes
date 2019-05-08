package com.coderbot.quotes.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.coderbot.quotes.model.Quote
import com.coderbot.quotes.repository.Repository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class QuotesViewModel constructor(protected val repository: Repository) : ViewModel()
{
    private val quotesLiveData: MutableLiveData<MutableList<Quote>> = MutableLiveData()

    fun observeQuotes(): MutableLiveData<MutableList<Quote>>
    {
        return quotesLiveData
    }

    private val successLiveData: MutableLiveData<String> = MutableLiveData()

    fun observeSuccess(): MutableLiveData<String>
    {
        return successLiveData
    }

    private val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun observeError(): MutableLiveData<String>
    {
        return errorLiveData
    }

    init
    {
        listenToNewQuotes()
    }

    fun getQuotes()
    {
        repository.getQuotes().subscribe(object : Observer<MutableList<Quote>>
        {
            override fun onSubscribe(d: Disposable)
            {

            }

            override fun onNext(t: MutableList<Quote>)
            {
                quotesLiveData.postValue(t)
            }

            override fun onError(e: Throwable)
            {
                errorLiveData.postValue(e.message)
            }

            override fun onComplete()
            {

            }
        })
    }

    fun addQuotes(quote: String)
    {
        repository.addQuotes(quote).subscribe(object : Observer<String>
        {
            override fun onSubscribe(d: Disposable)
            {

            }

            override fun onNext(t: String)
            {
                successLiveData.postValue(t)
            }

            override fun onError(e: Throwable)
            {
                errorLiveData.postValue(e.message)
            }

            override fun onComplete()
            {

            }
        })
    }

    private fun listenToNewQuotes()
    {
        repository.listenToNewQuotes().subscribe(object : Observer<MutableList<Quote>>
        {
            override fun onSubscribe(d: Disposable)
            {

            }

            override fun onNext(t: MutableList<Quote>)
            {
                quotesLiveData.postValue(t)
            }

            override fun onError(e: Throwable)
            {
                errorLiveData.postValue(e.message)
            }

            override fun onComplete()
            {

            }
        })
    }
}