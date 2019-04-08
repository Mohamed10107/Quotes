package com.coderbot.quotes.koin

import com.coderbot.quotes.firebase.FireStoreManager
import com.coderbot.quotes.repository.Repository
import com.coderbot.quotes.viewmodel.QuotesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    single { FireStoreManager() }

    single { Repository(get()) }

    viewModel { QuotesViewModel(get()) }
}