package com.example.questapi_214.view.route

import com.example.questapi_214.R

object DestinasiEdit : DestinasiNavigasi {

    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa

    const val itemIdArg = "itemId"

    val routeWithArgs = "$route/{$itemIdArg}"
}