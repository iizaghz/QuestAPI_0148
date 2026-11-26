package com.example.mydatasiswa.repositori

import com.example.mydatasiswa.apiservice.ServiceApiSiswa
import com.example.mydatasiswa.modeldata.DataSiswa
import retrofit2.Response

interface RepositoryDataSiswa {
    suspend fun getSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void>
    suspend fun updateSiswa(id: Int, dataSiswa: DataSiswa): Response<Void>
    suspend fun deleteSiswa(id: Int): Response<Void>
    suspend fun getSiswaById(id: Int): DataSiswa
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa,
) : RepositoryDataSiswa {
    override suspend fun getSiswa(): List<DataSiswa> =
        serviceApiSiswa.getSiswa()

    override suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void> =
        serviceApiSiswa.postSiswa(dataSiswa)

    override suspend fun updateSiswa(id: Int, dataSiswa: DataSiswa): Response<Void> =
        serviceApiSiswa.updateSiswa(id, dataSiswa)

    override suspend fun deleteSiswa(id: Int): Response<Void> =
        serviceApiSiswa.deleteSiswa(id)

    override suspend fun getSiswaById(id: Int): DataSiswa =
        serviceApiSiswa.getSiswaById(id)
}
