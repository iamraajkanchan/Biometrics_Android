package com.example.biometrics_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.biometrics_android.util.LoginBioMetricUtil

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBioMetricUtil: LoginBioMetricUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBioMetricUtil = LoginBioMetricUtil(this@LoginActivity)
        loginBioMetricUtil.authenticate()
    }
}