package com.example.questapi_214.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.questapi_214.repositori.AplikasiDataSiswa
import com.example.questapi_214.viewmodel.DetailViewModel
import com.example.questapi_214.viewmodel.EditViewModel
import com.example.questapi_214.viewmodel.EntryViewModel
import com.example.questapi_214.viewmodel.HomeViewModel

fun CreationExtras.aplikasiDataSiswa() : AplikasiDataSiswa = (
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
                AplikasiDataSiswa)
object PenyediaViewModel {
    val Factory = viewModelFactory  {
        initializer {
            HomeViewModel(aplikasiDataSiswa().container.repositoryDataSiswa)
        }
        initializer {
            EntryViewModel(aplikasiDataSiswa().container.repositoryDataSiswa)
        }
        initializer {
            DetailViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                repositoryDataSiswa = aplikasiDataSiswa().container.repositoryDataSiswa
            )
        }

        initializer {
            EditViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                repositoryDataSiswa = aplikasiDataSiswa().container.repositoryDataSiswa
            )
        }
    }
}