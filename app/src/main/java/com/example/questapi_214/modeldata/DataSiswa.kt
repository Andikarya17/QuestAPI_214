package com.example.questapi_214.modeldata

import kotlinx.serialization.Serializable

@Serializable
data class DataSiswa(
    val id: Int,
    val nama: String,
    val telepon: String,
    val alamat: String
)

data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val telepon: String = "",
    val alamat: String = ""
)
fun DetailSiswa.toDataSiwa(): DataSiswa = DataSiswa(
    id = id,
    nama = nama,
    telepon = telepon,
    alamat = alamat
)

fun DataSiswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)

fun DataSiswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    telepon = telepon,
    alamat = alamat
)