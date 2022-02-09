package com.example.biometrics_android.util

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.*
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.biometrics_android.MainActivity
import com.example.biometrics_android.R

class BioMetricUtil(private val context : Context)
{
    private lateinit var biometricPrompt : BiometricPrompt
    private lateinit var biometricPromptInfo : BiometricPrompt.PromptInfo
    private val tvText : TextView = (context as MainActivity).findViewById(R.id.tvText)
    private val btnLogin : Button = (context as MainActivity).findViewById(R.id.btnLogin)

    init
    {
        setBioMetric()
    }

    private fun setBioMetric()
    {
        val biometricManager : BiometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG))
        {
            BiometricManager.BIOMETRIC_SUCCESS ->
            {
                tvText.text = "You can use the finger sensor to login."
                tvText.setTextColor(Color.parseColor("#FAFAFA"))
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
            {
                tvText.text = "This device does not have a fingerprint scanner."
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
            {
                tvText.text = "The biometric sensor is currently unavailable."
                btnLogin.visibility = View.GONE
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
            {
                tvText.text = "Your device doesn't have a fingerprint scanner."
            }
        }

        val executor = ContextCompat.getMainExecutor(context)
        val callback = object : BiometricPrompt.AuthenticationCallback()
        {
            override fun onAuthenticationSucceeded(result : BiometricPrompt.AuthenticationResult)
            {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(context , "Login Successful" , Toast.LENGTH_LONG).show()
                btnLogin.text = "Login Successful"
            }
        }
        biometricPrompt = BiometricPrompt(context as MainActivity , executor , callback)

        biometricPromptInfo = BiometricPrompt.PromptInfo.Builder().setTitle("Chinky & Brothers")
            .setDescription("Use your finger print to login").setNegativeButtonText("Cancel")
            .build()
    }

    fun authenticate()
    {
        biometricPrompt.authenticate(biometricPromptInfo)
    }
}