package com.example.mtmp_cv2

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class AnimationActivity : AppCompatActivity() {
    lateinit var mainLayout: LinearLayout
    lateinit var xArray: ArrayList<*>
    lateinit var yArray: ArrayList<*>
    var duration: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        xArray = intent.getSerializableExtra("xArray") as ArrayList<*>
        yArray = intent.getSerializableExtra("yArray") as ArrayList<*>
        duration = intent.getFloatExtra("duration", 0f)

        val ballImageView = findViewById<ImageView>(R.id.ball)
        mainLayout = findViewById(R.id.main_layout)

        animate(ballImageView)
    }

    private fun animate(view: View) {
        val x = view.x
        val y = view.y + 1700f

        val path = calculatePath(x, y)

        val animationMove = ObjectAnimator.ofFloat(
            view, View.X,
            View.Y, path,
        )
        animationMove.duration = duration.toLong() * 200


        val animationRotate = ObjectAnimator.ofFloat(
            view, "rotation", 0f, 360f * duration/5
        )
        animationRotate.duration = duration.toLong() * 200

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animationMove, animationRotate)
        animatorSet.start()
    }

    private fun calculatePath(x: Float, y: Float): Path {
        val path = Path()
        path.moveTo(x, y)
        for (i in xArray.indices) {
            path.lineTo(x + xArray[i].toString().toFloat(), y - yArray[i].toString().toFloat())
        }
        return path
    }
}