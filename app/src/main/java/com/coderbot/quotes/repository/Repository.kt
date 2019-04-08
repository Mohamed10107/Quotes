package com.coderbot.quotes.repository

import com.coderbot.quotes.firebase.FireStoreManager
import com.coderbot.quotes.model.Quote
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class Repository constructor(protected val fireStoreManager: FireStoreManager)
{
    fun getQuotes(): Observable<MutableList<Quote>>
    {
        return fireStoreManager.getQuotes().subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
    }

    fun addQuotes(quote: String, author: String): Observable<String>
    {
        return fireStoreManager.addQuotes(quote, author).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
    }

    fun listenToNewQuotes(): Observable<MutableList<Quote>>
    {
        return fireStoreManager.listenToNewQuotes().subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
    }
}
