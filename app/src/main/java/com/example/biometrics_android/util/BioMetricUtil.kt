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

class BioMetricUtil(private val context: Context) {
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var biometricPromptInfo: BiometricPrompt.PromptInfo
    private val tvText: TextView = (context as MainActivity).findViewById(R.id.tvText)
    private val btnLogin: Button = (context as MainActivity).findViewById(R.id.btnLogin)

    init {
        setBioMetric()
    }

    private fun setBioMetric() {
        val biometricManager: BiometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                tvText.text = context.getText(R.string.finger_print_enabled_message)
                tvText.setTextColor(Color.parseColor("#FAFAFA"))
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                tvText.text = context.getText(R.string.incompatible_device_message)
                btnLogin.visibility = View.GONE
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                tvText.text = context.getText(R.string.finger_print_disabled_message)
                btnLogin.visibility = View.GONE
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                tvText.text = context.getText(R.string.finger_print_not_configured_message)
                btnLogin.visibility = View.GONE
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                tvText.text = context.getText(R.string.security_update_message)
                btnLogin.visibility = View.GONE
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                tvText.text = context.getText(R.string.finger_print_not_supported_message)
                btnLogin.visibility = View.GONE
            }

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                tvText.text = context.getText(R.string.finger_print_unknown_message)
                btnLogin.visibility = View.GONE
            }
        }

        val executor = ContextCompat.getMainExecutor(context)
        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                btnLogin.text = context.getText(R.string.login_successful)
            }
        }
        biometricPrompt = BiometricPrompt(context as MainActivity, executor, callback)

        biometricPromptInfo = BiometricPrompt.PromptInfo.Builder().setTitle("Shaw Info Solutions")
            .setDescription("Use your finger print to login").setNegativeButtonText("Cancel")
            .build()
    }

    fun authenticate() {
        biometricPrompt.authenticate(biometricPromptInfo)
    }
}