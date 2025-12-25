package com.example.questapi_214.viewmodel

sealed interface StatusUiDetail {
    data class Success(val siswa: DataSiswa) : StatusUiDetail
    object Error : StatusUiDetail
    object Loading : StatusUiDetail
}