package com.sk.patientsignupform.models

import java.io.Serializable

/***
 * Data class for patient details
 */
data class PatientDetails(
    var name: String,
    var gender: String,
    val email: String?,
    val address: String?,
    val pinCode: String?,
): Serializable
