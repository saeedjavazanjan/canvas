package com.project.practical.newproject.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.os.Handler
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.project.practical.newproject.R
import com.project.practical.newproject.utils.GameThread
import com.project.practical.newproject.utils.Item
import com.project.practical.newproject.utils.TypeOfItem


class GameView(context:Context) : SurfaceView(context) {
    private lateinit var bmp:Bitmap
    private var holder: SurfaceHolder = getHolder()
    private var gameThread: GameThread = GameThread(this)



    private var items:MutableList<Item> = mutableListOf()

    init {

        items.add(createItem(R.drawable.rock, typeOfItem = TypeOfItem.ROCK,0))
        items.add(createItem(R.drawable.rock, typeOfItem = TypeOfItem.ROCK,1))
        items.add(createItem(R.drawable.rock, typeOfItem = TypeOfItem.ROCK,2))
       items.add(createItem(R.drawable.rock, typeOfItem = TypeOfItem.ROCK,3))
        items.add(createItem(R.drawable.rock, typeOfItem = TypeOfItem.ROCK,4))
        items.add(createItem(R.drawable.paper, typeOfItem = TypeOfItem.PAPER,5))
        items.add(createItem(R.drawable.paper, typeOfItem = TypeOfItem.PAPER,6))
        items.add(createItem(R.drawable.paper, typeOfItem = TypeOfItem.PAPER,7))
        items.add(createItem(R.drawable.paper, typeOfItem = TypeOfItem.PAPER,8))
       items.add(createItem(R.drawable.paper, typeOfItem = TypeOfItem.PAPER,9))
        items.add(createItem(R.drawable.scissors, typeOfItem = TypeOfItem.SCISSORS,10))
      items.add(createItem(R.drawable.scissors, typeOfItem = TypeOfItem.SCISSORS,11))
       items.add(createItem(R.drawable.scissors, typeOfItem = TypeOfItem.SCISSORS,12))
       items.add(createItem(R.drawable.scissors, typeOfItem = TypeOfItem.SCISSORS,13))
        items.add(createItem(R.drawable.scissors, typeOfItem = TypeOfItem.SCISSORS,14))



        holder.addCallback(object :SurfaceHolder.Callback{
            @SuppressLint("WrongCall")
            override fun surfaceCreated(holder: SurfaceHolder) {
                Handler().postDelayed({
                    gameThread.setGameRunning(true)
                    gameThread.start()
                                      }, 1000)


            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                var retry=true
                gameThread.setGameRunning(false)
                while (retry){
                    try {
                        gameThread.join()
                        retry=false
                    }catch (e:InterruptedException){
                        e.printStackTrace()
                    }
                }
            }
        })




    }

    private fun createItem(resource:Int,typeOfItem: TypeOfItem,id:Int):Item{
        var bitmap:Bitmap=BitmapFactory.decodeResource(resources,resource)
        return Item(this,bitmap,typeOfItem,id)
    }


    private fun  removeItem(item1: Item,item2: Item){
        synchronized(getHolder()) {
                if (item1.isCollition(item2.x, item2.y)) {
                    if(item1.type==TypeOfItem.PAPER && item2.type==TypeOfItem.ROCK){
                        items.remove(item2)
                    }
                    if(item1.type==TypeOfItem.PAPER && item2.type==TypeOfItem.SCISSORS){
                        items.remove(item1)

                    }
                    if(item1.type==TypeOfItem.ROCK && item2.type==TypeOfItem.PAPER){
                        items.remove(item1)

                    }
                    if(item1.type==TypeOfItem.ROCK && item2.type==TypeOfItem.SCISSORS){
                        items.remove(item2)

                    }
                    if(item1.type==TypeOfItem.SCISSORS && item2.type==TypeOfItem.ROCK){
                        items.remove(item1)

                    }
                    if(item1.type==TypeOfItem.SCISSORS && item2.type==TypeOfItem.PAPER){
                        items.remove(item2)
                    }
                }

        }

    }
    public override fun onDraw(canvas: Canvas?) {
       // super.onDraw(canvas)

        canvas!!.drawColor(Color.BLUE)
        //canvas.drawBitmap(bmp,x1,y1,null)
        for (item in items) {
            item.onDraw(canvas)

        }


    for (i in 0 until items.size){
        for (j in i+1 until items.size){
            if(i<items.size && j<items.size)
                  removeItem(items[i], items[j])
            break


           }


    }

    }


}