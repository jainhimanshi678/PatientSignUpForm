package com.sk.patientsignupform.models

import java.io.Serializable

data class PatienDetails(
    var name: String,
    var gender: String,
    val email: String?,
    val address: String?,
    val pinCode: String?,
): Serializable
