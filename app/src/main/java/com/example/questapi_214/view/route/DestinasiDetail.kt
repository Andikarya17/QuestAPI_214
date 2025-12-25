package com.example.questapi_214.view.route

import com.example.questapi_214.R

object DestinasiDetail : DestinasiNavigasi {

    override val route = "item_detail"
    override val titleRes = R.string.detail_siswa

    const val itemIdArg = "itemId"

    val routeWithArgs = "$route/{$itemIdArg}"
}