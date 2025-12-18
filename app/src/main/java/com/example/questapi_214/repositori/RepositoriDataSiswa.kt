package com.example.questapi_214.repositori

import com.example.questapi_214.modeldata.DataSiswa

interface RepositoriDataSiswa{
    suspend fun getDataSiswa() : List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa) :retrofit2.Response<DataSiswa>

    class JaringanRepositoriDataSiswa(

    )

}

