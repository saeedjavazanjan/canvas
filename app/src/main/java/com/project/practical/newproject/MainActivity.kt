package com.project.practical.newproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout.LayoutParams
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.practical.newproject.canvas.GameView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myCanvas=GameView(this)
        setContentView(myCanvas)


    }
}