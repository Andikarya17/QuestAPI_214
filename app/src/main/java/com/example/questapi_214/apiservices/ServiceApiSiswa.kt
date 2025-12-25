package com.example.questapi_214.apiservices

import com.example.questapi_214.modeldata.DataSiswa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ServiceApiSiswa {

    // GET ALL
    @GET("bacaTeman.php")
    suspend fun getSiswa(): List<DataSiswa>

    // GET BY ID (DETAIL)
    @GET("baca1teman.php")
    suspend fun getSiswaById(
        @Query("id") id: Int
    ): DataSiswa

    // INSERT
    @POST("insert.php")
    suspend fun postSiswa(
        @Body dataSiswa: DataSiswa
    ): Response<Void>

    // UPDATE
    @PUT("edit.php")
    suspend fun editSiswa(
        @Query("id") id: Int,
        @Body dataSiswa: DataSiswa
    ): Response<Void>

    // DELETE
    @DELETE("delete.php")
    suspend fun hapusSiswa(
        @Query("id") id: Int
    ): Response<Void>
}