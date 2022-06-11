package com.example.giflibtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.rastermill.FrameSequence
import android.support.rastermill.FrameSequenceDrawable
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import java.nio.Buffer
import java.nio.ByteBuffer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bitmap = ResourcesCompat.getDrawable(resources,R.drawable.ic_gif_test_drawable,theme)?.toBitmap()
        if (bitmap == null) {
            toast("bitmap is null")
            return
        }
        val dst = ByteBuffer.allocate(bitmap.byteCount)
        bitmap.copyPixelsToBuffer(dst)
        val b = ByteArray(1024_000)
        assets.open("ic_gif_test.gif").read(b)

        val decodeByteArray = FrameSequence.decodeByteArray(b/*dst.array()*/)?:return
        toast("crate gif drawable")
        val frameSequenceDrawable =
            FrameSequenceDrawable(decodeByteArray)
        findViewById<ImageView>(R.id.imageView).apply {
            setImageDrawable(frameSequenceDrawable)
            frameSequenceDrawable.start()
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
    }
}