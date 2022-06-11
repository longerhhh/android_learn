package com.example.myapplication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.postDelayed
import coil.decode.ImageDecoderDecoder
import coil.load
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.gifdecoder.GifDecoder
import com.drake.engine.databinding.loadGif
import com.dylanc.longan.toast
import com.example.myapplication.widget.AnimateFlipView
import com.example.myapplication.widget1.GirdDragLayout
import com.example.myapplication.widget1.GridDragHelperLayout
import com.example.myapplication.widget1.TagLayout
import kotlinx.coroutines.Dispatchers

// hilt 尚未支持 ksp
//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var fv: AnimateFlipView
    val TAG = "MAIN"

//    val vm by viewModels<HomeViewModel>()

//    TODO("从头开始搭建app协程网络请求框架")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_gif)
        TimeUtils.millis2String()
        findViewById<ImageView>(R.id.iv).apply {
            coil.decode.GifDecoder
            load(R.drawable.ic_gif_test){
                // iamge decoderdecoder 在 api 大于 28 版本可用，性能更好
                decoder(ImageDecoderDecoder()) // 稳如狗，没有任何波动，无内存抖动
//                decoder(coil.decode.GifDecoder()) // 稳如狗，没有任何波动，无内存抖动
            }
//
        //            Glide.with(this).asGif().load(R.drawable.ic_gif_test).into(this) // 有内存抖动
        }
        System.loadLibrary("")
        
//        testSwitch()
        Log.d(TAG, "onCreate: ")
//        testViewFlipper()
//        testDragHelper()
//        testGridLayout()
//        testGridDragHelperLayout()
//        testGridDragLayout()
//        testTagLayout()
//        findViewById<ViewPager2>(R.id.vp2).isVisible = false
        //
//        findViewById<ViewPager2>(R.id.vp2).apply {
//            adapter = object : FragmentStateAdapter
//        }
//        vm.t()
//        startActivity(Intent(this, EditTextActivity::class.java))


//        drawDrawable()
//        propVHAnim()
//        playFlipAnim()
    }
    
    private fun testViewFlipper() {
//        val sf = findViewById<ScrollViewFlipper>(R.id.vf).apply {
//            addView(getImageView(R.drawable.ic_dmb))
//            addView(getImageView(R.drawable.ic_ts))
//            addView(getImageView(R.drawable.ic_ask))
//            addView(getImageView(R.drawable.ic_mhj))
//        }
//        val a = ObjectAnimator.ofInt(sf, "scrollX", 0, 1080)
//        a.startDelay = 2000
//        a.duration = 5000
//        a.start()
//        a.doOnRepeat {
//            Log.d(TAG, "onCreate: ${a.isStarted}, ${a.isRunning}")
//        }
//        a.addUpdateListener {
//            Log.d(TAG, "onCreate: ${a.isStarted}, ${a.isRunning}")
//        }
//        runBlocking(Dispatchers.IO) {
//            delay(2000)
//            for (i in 0..320) {
//                Log.d(TAG, "onCreate: ${a.isStarted}, ${a.isRunning}")
//                delay(16)
//            }
//        }
    }
    
    private fun testSwitch() {
        findViewById<SwitchCompat>(R.id.sw).apply {
            setOnClickListener {
                toast("click$isChecked")
            }
            Log.wtf(TAG, "onCreate: ")
        
        
            startActivity(intent)
            postDelayed(3000) { isChecked = true }
            postDelayed(5000) { isChecked = true }
            postDelayed(8000) { isChecked = true }
        }
    }
    
    fun getImageView(@DrawableRes res: Int) = ImageView(this).apply {
        setImageResource(res)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun testDragHelper() {
        findViewById<View>(R.id.v).apply {

        }
    }

    private fun testGridLayout() {
        findViewById<GridLayout>(R.id.gl).apply {
            for (i in 0 until childCount) {
                getChildAt(i).run {
                    setOnLongClickListener { v ->
                        v.startDragAndDrop(null, View.DragShadowBuilder(v), v.contentDescription, 0)
                    }
                }
            }
        }
        findViewById<TextView>(R.id.tv).apply {
            setOnDragListener { v, event ->
                when (event.action) {
                    DragEvent.ACTION_DROP -> {
                        text = text.toString() + event.localState.toString()
                    }
                }
                true
            }
        }
    }

    private fun testGridDragHelperLayout() {
        findViewById<GridDragHelperLayout>(R.id.gdhl).apply {
            for (i in 0 until 6) {
                addView(View(context).apply {
                    setBackgroundColor(colors[i])
                })
            }
        }
    }

    private fun testGridDragLayout() {
        findViewById<GirdDragLayout>(R.id.gdl).apply {
            for (i in 0 until 6) {
                addView(View(context).apply {
                    setBackgroundColor(colors[i])
                })
            }
        }
    }

    private fun testTagLayout() {
        val tagLayout = findViewById<TagLayout>(R.id.tl)
        strs.forEachIndexed { i, item ->
            tagLayout.addView(getTextView(i, item))
        }
    }

    val strs = arrayOf(
        "阿尔及利亚", "安哥拉", "贝宁", "博茨瓦纳", "布基纳法索", "布隆迪", "喀麦隆", "佛得角",
        "中非", "乍得", "科摩罗", "刚果 (布)", "科特迪瓦", "吉布提", "埃及", "赤道几内亚", "埃塞俄比亚",
        "厄立特里亚", "加蓬", "冈比亚", "加纳", "几内亚", "几内亚比绍", "肯尼亚", "莱索托", "利比里亚",
        "利比亚", "马达加斯加", "马拉维", "马里", "毛里塔尼亚", "毛里求斯", "摩洛哥", "莫桑比", "尼日尔",
        "尼日利亚", "卢旺达", "圣多美和普林西比", "塞舌尔", "塞内加尔", "塞拉利昂", "索马里", "南非", "斯威士兰",
        "坦桑尼亚", "多哥", "突尼斯", "乌干达", "刚果（金）", "赞比亚", "津巴布韦", "苏丹"
    )

    val colors = intArrayOf(
        Color.parseColor("#E91E63"),
        Color.parseColor("#673AB7"),
        Color.parseColor("#3F51B5"),
        Color.parseColor("#2196F3"),
        Color.parseColor("#009688"),
        Color.parseColor("#FF9800"),
        Color.parseColor("#FF5722"),
        Color.parseColor("#795548")
    )

    private fun getTextView(i: Int, text: String): TextView {
        val tv = TextView(this)
        tv.setText(text)
        tv.setBackgroundColor(colors[i % colors.size])
        val lp = ViewGroup.MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        lp.setMargins(10.dp.toInt(), 10.dp.toInt(), 10.dp.toInt(), 10.dp.toInt())
        tv.layoutParams = lp
        tv.setPadding(0, 10.dp.toInt(), 0, 10.dp.toInt())
        tv.gravity = Gravity.CENTER
        tv.textSize = (5..20).random().toFloat()
        return tv
    }

    private fun drawDrawable() {
        val rx = 10.dp
        val ry = 10.dp
        val drawable = ShapeDrawable().apply {
            paint.setColor(Color.GREEN)
            val roundRectShape =
                RoundRectShape(floatArrayOf(rx, ry, rx, ry, rx, ry, rx, ry), null, null)
            this.shape = roundRectShape
        }
        findViewById<View>(R.id.v).background = drawable
    }

    private fun propVHAnim() {
        fv = findViewById(R.id.afv)
        val rotateVH = PropertyValuesHolder.ofFloat("rotateDegree", 0f, 30f)
        val flipVH = PropertyValuesHolder.ofFloat("flipDegree", 0f, 270f)
        val topRotateVH = PropertyValuesHolder.ofFloat("topRotateDegree", 0f, -30f)
        ObjectAnimator.ofPropertyValuesHolder(fv, rotateVH, flipVH, topRotateVH).apply {
            duration = 2000
            startDelay = 2000
            start()
        }
    }

    private fun playFlipAnim() {
        fv = findViewById(R.id.afv)

        val rotateAnim = ObjectAnimator.ofFloat(fv, "rotateDegree", 0f, 30f).apply {
            duration = 1000
            //            startDelay = 2000
            //            start()
        }
        val flipAnim = ObjectAnimator.ofFloat(fv, "flipDegree", 0f, 270f).apply {
            duration = 2000
            //            start()
        }
        val topRotateAnim = ObjectAnimator.ofFloat(fv, "topRotateDegree", 0f, -30f).apply {
            duration = 1000
        }
        AnimatorSet().apply {
            // 顺序播放
            playSequentially(rotateAnim, flipAnim, topRotateAnim)
            // 同时播放
            //            this.playTogether()
            startDelay = 2000
        }.start()
    }
}