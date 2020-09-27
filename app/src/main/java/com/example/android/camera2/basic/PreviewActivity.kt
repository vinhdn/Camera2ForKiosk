package com.example.android.camera2.basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        val uri = intent.getStringExtra("uri") ?: return
        Glide.with(this).load(uri).into(ivPreview)
    }
}