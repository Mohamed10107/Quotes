package com.coderbot.quotes.firebase

import com.coderbot.quotes.model.Quote
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import java.lang.Exception
import java.util.*

class FireStoreManager
{
    private val db = FirebaseFirestore.getInstance()

    fun getQuotes(): Observable<MutableList<Quote>>
    {
        return Observable.create {
            try
            {
                val quotes = mutableListOf<Quote>()
                db.collection("quotes").get().addOnCompleteListener { task ->
                    if (task.isSuccessful && task.result != null)
                    {
                        for (document in task.result!!)
                        {
                            val data = document.data
                            quotes.add(Quote(data["quote"].toString(), data["author"].toString(), data["date"].toString().toLong()))
                        }
                        it.onNext(quotes)
                    }
                    else
                    {
                        if (task.exception != null)
                        {
                            task.exception!!.printStackTrace()
                            it.onError(Throwable(task.exception!!.message))
                        }
                    }
                }.addOnFailureListener { error ->
                    error.printStackTrace()
                    it.onError(error)
                }
            }
            catch (ex: Exception)
            {
                ex.printStackTrace()
                it.onError(ex)
            }
        }
    }

    fun addQuotes(quote: String, author: String): Observable<String>
    {
        return Observable.create { emitter ->
            try
            {
                val date = Date().time

                val data = hashMapOf<String, Any>()
                data.put("quote", quote)
                data.put("author", author)
                data.put("date", date)

                db.collection("quotes").document(date.toString()).set(data).addOnSuccessListener {
                    emitter.onNext("SUCCESS")
                }.addOnFailureListener {
                    it.printStackTrace()
                    emitter.onError(it)
                }
            }
            catch (ex: Exception)
            {
                ex.printStackTrace()
                emitter.onError(ex)
            }
        }
    }

    fun listenToNewQuotes(): Observable<MutableList<Quote>>
    {
        return Observable.create {
            try
            {
                val reference = db.collection("quotes")
                reference.addSnapshotListener { snapshot, e ->
                    if (e != null)
                    {
                        e.printStackTrace()
                        it.onError(e)
                    }
                    else if (snapshot != null && !snapshot.isEmpty)
                    {
                        val quotes = mutableListOf<Quote>()
                        for (document in snapshot.documents)
                        {
                            val data = document.data
                            if (data != null)
                            {
                                quotes.add(Quote(data["quote"].toString(), data["author"].toString(), data["date"].toString().toLong()))
                            }
                        }
                        it.onNext(quotes)
                    }
                }
            }
            catch (ex: Exception)
            {
                ex.printStackTrace()
                it.onError(ex)
            }
        }
    }
}