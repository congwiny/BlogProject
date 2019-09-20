package com.congwiny.mydemo.textview

import android.animation.ObjectAnimator
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.congwiny.mydemo.R
import kotlinx.android.synthetic.main.activity_text_view_demo.*

class TextViewDemoActivity : AppCompatActivity(), View.OnClickListener {

    private var mRefreshAnimator: ObjectAnimator? = null
    private var mRefreshCount: Int = 0

    private var mAnimatedDrawable: Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view_demo)
        //initRefreshTextView()
        initRefreshTextView2()
        refresh_tv.setOnClickListener(this)
    }

    private fun initRefreshTextView() {
        //获取到旋转的Drawable
        val drawable = resources.getDrawable(R.drawable.textview_refresh_rotate)
        //定制Drawable的大小
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        //为TextView设置drawableLeft
        refresh_tv.setCompoundDrawables(drawable, null, null, null)

        //通过属性动画设置drawable的level值
        mRefreshAnimator = ObjectAnimator.ofInt(drawable, "level", 0, 10000)
        mRefreshAnimator?.apply {
            duration = 600 //动画执行600ms
            repeatCount = -1 //无限执行次数
            interpolator = LinearInterpolator() //匀速插值器
        }
    }

    fun initRefreshTextView2() {
        mAnimatedDrawable = resources.getDrawable(R.drawable.weather_refresh_rotate2)
        mAnimatedDrawable?.apply {
            setBounds(0,0,intrinsicWidth,intrinsicWidth)
        }
        refresh_tv.setCompoundDrawables(mAnimatedDrawable, null, null, null)
    }

    override fun onClick(v: View) {
        when (v.id) {
//            R.id.refresh_tv -> {
//                if (mRefreshCount % 2 == 0) {
//                    mRefreshAnimator?.start() //启动动画
//                } else {
//                    mRefreshAnimator?.cancel() //取消动画
//                }
//                mRefreshCount++
//            }
            R.id.refresh_tv -> {
                if (mRefreshCount % 2 == 0) {
                    (mAnimatedDrawable as? Animatable)?.start() //启动动画
                } else {
                    (mAnimatedDrawable as? Animatable)?.stop() //取消动画
                }
                mRefreshCount++
            }
        }
    }
}
