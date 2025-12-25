package com.example.questapi_214.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.questapi_214.modeldata.DataSiswa
import com.example.questapi_214.repositori.RepositoryDataSiswa

sealed interface StatusUiDetail {
    data class Success(val siswa: DataSiswa) : StatusUiDetail
    object Error : StatusUiDetail
    object Loading : StatusUiDetail
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
)
