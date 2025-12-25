package com.example.questapi_214.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_214.modeldata.DetailSiswa
import com.example.questapi_214.modeldata.UIStateSiswa
import com.example.questapi_214.modeldata.toDataSiswa
import com.example.questapi_214.repositori.RepositoryDataSiswa
import com.example.questapi_214.view.route.DestinasiEdit
import kotlinx.coroutines.launch
import java.io.IOException

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int =
        checkNotNull(savedStateHandle[DestinasiEdit.itemIdArg])

    init {
        getSiswaById()
    }

    private fun getSiswaById() {
        viewModelScope.launch {
            try {
                val siswa = repositoryDataSiswa.getSiswaById(idSiswa)
                uiStateSiswa = UIStateSiswa(
                    detailSiswa = DetailSiswa(
                        id = siswa.id,
                        nama = siswa.nama,
                        alamat = siswa.alamat,
                        telpon = siswa.telpon
                    ),
                    isEntryValid = true
                )
            } catch (e: IOException) {
                // Error bisa ditangani sesuai kebutuhan (praktikum tidak membahas UI error edit)
            }
        }
    }

    private fun validasiInput(
        detailSiswa: DetailSiswa = uiStateSiswa.detailSiswa
    ): Boolean {
        return with(detailSiswa) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }

    suspend fun editSiswa() {
        if (validasiInput()) {
            repositoryDataSiswa.editSiswa(
                idSiswa,
                uiStateSiswa.detailSiswa.toDataSiswa()
            )
        }
    }
}

