package com.project.practical.newproject.utils

import android.R.attr.height
import android.R.attr.width
import android.graphics.Bitmap
import android.graphics.Canvas
import com.project.practical.newproject.canvas.GameView
import java.util.Random


class Item(var gameView:GameView, var bitmap:Bitmap,var type:TypeOfItem , var id:Int) {

  var x=10f
  var y=10f
    var xSpeed=10f
     var ySpeed=10f


    init {


        var rand:Random=Random(System.currentTimeMillis())
        if (type==TypeOfItem.PAPER){
            x=rand.nextInt(10).toFloat()+1
            y=rand.nextInt(200).toFloat()+1
        }
        if(type==TypeOfItem.SCISSORS){
            x=rand.nextInt(400).toFloat()+1
            y=rand.nextInt(300).toFloat()+1
        }
        if(type==TypeOfItem.ROCK){
            x=rand.nextInt(500).toFloat()+1
            y=rand.nextInt(1500).toFloat()+1
        }


    }
    private fun updatePosition(){
        if( x>gameView.width-bitmap.width-xSpeed){
            xSpeed=-xSpeed
        }
        if(x+xSpeed<0){
            xSpeed=15f
        }
        x += xSpeed
        if( y>gameView.width-bitmap.width-ySpeed){
            ySpeed=-ySpeed

        }
        if(y+ySpeed<0){
            ySpeed=15f
        }
        y += ySpeed
    }

    public fun onDraw(canvas: Canvas){
        updatePosition()
        canvas.drawBitmap(bitmap,x,y,null)


    }


    fun isCollition(x2: Float, y2: Float): Boolean {
        return x2 > x && x2 < x + width || y2 > y && y2 < y + height
    }

}