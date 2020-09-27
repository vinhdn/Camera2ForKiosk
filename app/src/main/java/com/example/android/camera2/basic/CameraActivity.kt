/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.camera2.basic

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Image.Plane
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.camerakit.CameraKitView
import com.google.zxing.BinaryBitmap
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class CameraActivity : AppCompatActivity() {

    private lateinit var cameraKitView: CameraKitView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        cameraKitView = findViewById(R.id.camera)
        cameraKitView.setPreviewCallback { _, bitmap ->
            Log.d("a", "a")
//            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//            runOnUiThread {
//                ivView.setImageBitmap(bitmap)
//            }
            runOnUiThread {
                ivView.setImageBitmap(bitmap)
            }
        }
        btnCapture.setOnClickListener {
            cameraKitView.captureImage { _, jpeg ->
                val uri = ImageUtils.saveBitmap(this, BitmapFactory.decodeByteArray(jpeg, 0, jpeg.size), SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US)
                        .format(System.currentTimeMillis()) + ".jpg")
                val intent = Intent(this, PreviewActivity::class.java)
                intent.putExtra("uri", uri)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        cameraKitView!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        cameraKitView!!.onResume()
    }

    override fun onPause() {
        cameraKitView!!.onPause()
        super.onPause()
    }

    override fun onStop() {
        cameraKitView!!.onStop()
        super.onStop()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults!!)
        cameraKitView?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        /** Combination of all flags required to put activity into immersive mode */
        const val FLAGS_FULLSCREEN =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        /** Milliseconds used for UI animations */
        const val ANIMATION_FAST_MILLIS = 50L
        const val ANIMATION_SLOW_MILLIS = 100L
        private const val IMMERSIVE_FLAG_TIMEOUT = 500L
        /** Use external media if it is available, our app's file directory otherwise */
        fun getOutputDirectory(context: Context): File {
            val appContext = context.applicationContext
            val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
                File(it, appContext.resources.getString(R.string.app_name)).apply { mkdirs() } }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else appContext.filesDir
        }
    }
}
