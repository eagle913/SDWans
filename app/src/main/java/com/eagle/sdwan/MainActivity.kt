package com.eagle.sdwan

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eagle.utils.SDUtil

class KMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SDUtil.d()
    }


}
