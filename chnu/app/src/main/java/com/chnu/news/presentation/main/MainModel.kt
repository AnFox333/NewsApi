package com.chnu.news.presentation.main

import org.koin.dsl.module

import org.koin.androidx.viewmodel.dsl.viewModel


val mainViewModelModule = module {
  viewModel{
       NewsViewModel(get())
   }
}