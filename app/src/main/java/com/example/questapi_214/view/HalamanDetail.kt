package com.example.questapi_214.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questapi_214.viewmodel.provider.PenyediaViewModel
import com.example.questapi_214.R
import com.example.questapi_214.modeldata.DataSiswa
import com.example.questapi_214.viewmodel.DetailViewModel
import com.example.questapi_214.viewmodel.StatusUiDetail
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanDetail(
    navigateBack: () -> Unit,
    navigateToEdit: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(R.string.detail_siswa),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            val uiState = viewModel.statusUiDetail
            FloatingActionButton(
                onClick = {
                    if (uiState is StatusUiDetail.Success) {
                        navigateToEdit(uiState.siswa.id)
                    }
                },
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
            ) {
                Text("Edit") }
        }
    ){ innerPadding ->
                BodyDetail(
                    statusUiDetail = viewModel.statusUiDetail,
                    onDelete = {
                        coroutineScope.launch {
                            viewModel.hapusSiswa()
                            navigateBack()
                        }
                    },
                    modifier = modifier
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                )
        }
}

@Composable
fun BodyDetail(
    statusUiDetail: StatusUiDetail,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        when (statusUiDetail) {
            is StatusUiDetail.Loading -> LoadingScreen()
            is StatusUiDetail.Success -> {
                DetailItem(
                    siswa = statusUiDetail.siswa,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            is StatusUiDetail.Error -> {
                Text(text = stringResource(R.string.gagal))
            }
        }
        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.delete))
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = {
                    deleteConfirmationRequired = false
                }
            )
        }
    }
}

@Composable
fun DetailItem(
    siswa: DataSiswa,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DetailRow(R.string.nama1, siswa.nama)
            DetailRow(R.string.alamat1, siswa.alamat)
            DetailRow(R.string.telpon1, siswa.telpon)
        }
    }
}
@Composable
fun DetailRow(
    labelRes: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelRes))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = value, fontWeight = FontWeight.Bold)
    }
}
@Composable
fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(stringResource(R.string.attention)) },
        text = { Text(stringResource(R.string.tanya)) },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(stringResource(R.string.yes))
            }
        },
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(stringResource(R.string.no))
            }
        }
    )
}


