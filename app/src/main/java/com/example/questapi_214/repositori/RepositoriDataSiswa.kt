package com.example.questapi_214.repositori

import com.example.questapi_214.apiservices.ServiceApiSiswa
import com.example.questapi_214.modeldata.DataSiswa
import retrofit2.Response

interface RepositoryDataSiswa {

    suspend fun getDataSiswa(): List<DataSiswa>

    suspend fun getSiswaById(id: Int): DataSiswa

    suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void>

    suspend fun editSiswa(id: Int, dataSiswa: DataSiswa): Response<Void>

    suspend fun hapusSiswa(id: Int): Response<Void>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoryDataSiswa {

    override suspend fun getDataSiswa(): List<DataSiswa> =
        serviceApiSiswa.getSiswa()

    override suspend fun getSiswaById(id: Int): DataSiswa =
        serviceApiSiswa.getSiswaById(id)

    override suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void> =
        serviceApiSiswa.postSiswa(dataSiswa)

    override suspend fun editSiswa(id: Int, dataSiswa: DataSiswa): Response<Void> =
        serviceApiSiswa.editSiswa(id, dataSiswa)

    override suspend fun hapusSiswa(id: Int): Response<Void> =
        serviceApiSiswa.hapusSiswa(id)
}