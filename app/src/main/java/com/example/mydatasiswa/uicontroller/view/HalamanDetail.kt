package com.example.mydatasiswa.uicontroller.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mydatasiswa.modeldata.DataSiswa
import com.example.mydatasiswa.uicontroller.viewmodel.DetailUiState
import com.example.mydatasiswa.uicontroller.viewmodel.DetailViewModel
import com.example.mydatasiswa.uicontroller.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanDetail(
    navigateBack: () -> Unit,
    onEditClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SiswaTopAppBar(
                title = "Detail Siswa",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            val state = viewModel.detailUiState
            if (state is DetailUiState.Success) {
                FloatingActionButton(
                    onClick = { onEditClick(state.siswa.id) },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(18.dp)
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Siswa")
                }
            }
        }
    ) { innerPadding ->
        BodyDetailSiswa(
            detailUiState = viewModel.detailUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailSiswa(
    detailUiState: DetailUiState,
    modifier: Modifier = Modifier
) {
    when (detailUiState) {
        is DetailUiState.Loading -> Text("Loading...", modifier = modifier)
        is DetailUiState.Success -> DetailCard(siswa = detailUiState.siswa, modifier = modifier.padding(16.dp))
        is DetailUiState.Error -> Text("Error loading data", modifier = modifier)
    }
}

@Composable
fun DetailCard(
    siswa: DataSiswa,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "ID: ${siswa.id}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Nama: ${siswa.nama}", style = MaterialTheme.typography.titleLarge)
            Text(text = "Alamat: ${siswa.alamat}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Telepon: ${siswa.telpon}", style = MaterialTheme.typography.titleMedium)
        }
    }
}
