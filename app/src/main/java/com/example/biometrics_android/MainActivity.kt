package com.example.biometrics_android

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.biometrics_android.util.BioMetricUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener
{
    private lateinit var bioMetricUtil : BioMetricUtil
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin.setOnClickListener(this)
        bioMetricUtil = BioMetricUtil(this)
    }

    override fun onClick(view : View?)
    {
        if (view?.id == R.id.btnLogin)
        {
            bioMetricUtil.authenticate()
        }
    }
}