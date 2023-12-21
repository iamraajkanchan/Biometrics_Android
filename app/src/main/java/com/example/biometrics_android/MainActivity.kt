package com.example.biometrics_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.biometrics_android.util.BioMetricUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var bioMetricUtil: BioMetricUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bioMetricUtil = BioMetricUtil(this)
        btnLogin.setOnClickListener {
            bioMetricUtil.authenticate()
        }
    }
}