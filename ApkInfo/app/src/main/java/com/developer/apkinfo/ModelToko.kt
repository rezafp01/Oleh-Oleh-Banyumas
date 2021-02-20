package com.developer.apkinfo


data class ModelToko(
    var NamaToko: String? = null,
    var Deskripsi: String? = null,
    var AlamatToko: String? = null,
    var GambarToko: String? = null,
    var coordinate: List<Double>? = null
)