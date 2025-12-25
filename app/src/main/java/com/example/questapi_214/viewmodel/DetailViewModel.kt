package com.example.questapi_214.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_214.modeldata.DataSiswa
import com.example.questapi_214.repositori.RepositoryDataSiswa
import com.example.questapi_214.view.route.DestinasiDetail
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface StatusUiDetail {
    data class Success(val siswa: DataSiswa) : StatusUiDetail
    object Error : StatusUiDetail
    object Loading : StatusUiDetail
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var statusUiDetail: StatusUiDetail by mutableStateOf(StatusUiDetail.Loading)
        private set

    private val idSiswa: Int =
        checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    init {
        getSiswaById()
    }
    fun getSiswaById() {
        viewModelScope.launch {
            statusUiDetail = StatusUiDetail.Loading
            statusUiDetail = try {
                StatusUiDetail.Success(
                    repositoryDataSiswa.getSiswaById(idSiswa)
                )
            } catch (e: IOException) {
                StatusUiDetail.Error
            }
        }
    }
    suspend fun hapusSiswa() {
        repositoryDataSiswa.hapusSiswa(idSiswa)
    }}


