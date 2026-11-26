package com.example.mydatasiswa.uicontroller.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydatasiswa.modeldata.DataSiswa
import com.example.mydatasiswa.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch

sealed interface StatusUiSiswa {
    data class Success(val siswa: List<DataSiswa>) : StatusUiSiswa
    data class Error(val message: String = "Gagal Loading") : StatusUiSiswa
    object Loading : StatusUiSiswa
}

class HomeViewModel(private val repositoryDataSiswa: RepositoryDataSiswa) : ViewModel() {
    var listSiswa: StatusUiSiswa by mutableStateOf(StatusUiSiswa.Loading)
        private set

    init {
        loadSiswa()
    }

    fun loadSiswa() {
        viewModelScope.launch {
            listSiswa = StatusUiSiswa.Loading
            listSiswa = try {
                StatusUiSiswa.Success(repositoryDataSiswa.getSiswa())
            } catch (e: Exception) {
                e.printStackTrace()
                val errMsg = e.localizedMessage ?: e.message ?: "Gagal Loading"
                println("Error loading siswa: $errMsg")
                StatusUiSiswa.Error(errMsg)
            }
        }
    }

    fun deleteSiswa(id: Int) {
        viewModelScope.launch {
            try {
                repositoryDataSiswa.deleteSiswa(id)
                loadSiswa()
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error deleteSiswa: ${e.message}")
            }
        }
    }
}
