package com.example.questapi_214.repositori

import com.example.questapi_214.apiservices.ServiceApiSiswa
import com.example.questapi_214.modeldata.DataSiswa
import retrofit2.Response

interface RepositoriDataSiswa {

    suspend fun getDataSiswa(): List<DataSiswa>

    suspend fun postDataSiswa(
        dataSiswa: DataSiswa
    ): Response<Void>

    class JaringanRepositoriDataSiswa(
        private val serviceApiSiswa: ServiceApiSiswa
    ) : RepositoriDataSiswa {

        override suspend fun getDataSiswa(): List<DataSiswa> =
            serviceApiSiswa.getSiswa()

        override suspend fun postDataSiswa(
            dataSiswa: DataSiswa
        ): Response<Void> =
            serviceApiSiswa.postSiswa(dataSiswa)
    }
}
