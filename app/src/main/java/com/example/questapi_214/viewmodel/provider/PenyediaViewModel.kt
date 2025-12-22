package com.example.questapi_214.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.questapi_214.viewmodel.EntryViewModel
import com.example.questapi_214.viewmodel.HomeViewModel
import com.example.questapi_214.repositori.AplikasiDataSiswa


fun CreationExtras.aplikasiDataSiswa() : AplikasiSiswa = (
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(aplikasiDataSiswa().container.repositoriSiswa) }
        initializer { EntryViewModel(aplikasiDataSiswa().container.repositoriSiswa) }
    }
}