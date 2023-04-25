package com.sk.patientsignupform.views.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sk.patientsignupform.R
import com.sk.patientsignupform.databinding.FragmentRegisterBinding
import com.sk.patientsignupform.models.PatienDetails

/**
 * Fragment to register the patient
 */
class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnRegister.setOnClickListener {
            val patientDetails = PatienDetails(
                binding.etName.text.toString(),
                binding.rgGender.checkedRadioButtonId.toString(),
                binding.etEmail.toString(),
                binding.etAddress.toString(),
                binding.etPinCode.toString()
            )
            if (patientDetails.name == "" || patientDetails.gender == "-1") {
                Toast.makeText(
                    requireContext(),
                    "Please enter the mandatory details",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!binding.cbTerms.isChecked) {
                Toast.makeText(
                    requireContext(),
                    "Please agree the terms and conditions",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(requireContext(), "Registered", Toast.LENGTH_SHORT).show()
                binding.btnRegister.text = "Registered"
            }
        }

        binding.cbTerms.setOnClickListener {
            if (binding.cbTerms.isChecked)
                startAlertDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    private fun startAlertDialog() {
        var builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
        val alert = builder.create();
        alert.setTitle("AlertDialogExample");
        alert.setCancelable(true)
        alert.show();
    }
}