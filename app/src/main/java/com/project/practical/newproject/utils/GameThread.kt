package com.project.practical.newproject.utils

import android.annotation.SuppressLint
import android.graphics.Canvas
import com.project.practical.newproject.canvas.GameView

class GameThread(var view:GameView) :Thread() {
    private var running:Boolean=false


    fun setGameRunning(run:Boolean){
        running=run
    }

    @SuppressLint("WrongCall")
    override fun run() {
        while (running){
            var c:Canvas?=null
            try {

                c=view.holder.lockCanvas()

                synchronized(view.holder){
                    view.onDraw(c)
                }

            }finally {
                if(c!=null)
                    view.holder.unlockCanvasAndPost(c)
            }

        }
    }
}