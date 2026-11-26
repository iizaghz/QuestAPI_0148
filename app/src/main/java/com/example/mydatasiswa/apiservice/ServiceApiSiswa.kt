package com.example.mydatasiswa.apiservice

import com.example.mydatasiswa.modeldata.DataSiswa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ServiceApiSiswa {
    @GET("bacateman.php")
    suspend fun getSiswa(): List<DataSiswa>

    @GET("bacateman.php")
    suspend fun getSiswaById(@Query("id") id: Int): DataSiswa

    @POST("insertTM.php")
    suspend fun postSiswa(@Body dataSiswa: DataSiswa): Response<Void>

    @PUT("editTM.php")
    suspend fun updateSiswa(@Query("id") id: Int, @Body dataSiswa: DataSiswa): Response<Void>

    @DELETE("deleteTM.php")
    suspend fun deleteSiswa(@Query("id") id: Int): Response<Void>
}
