package com.fonagyma.vlsg.logic

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import com.fonagyma.vlsg.R
import java.lang.Float.min
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

class SimonSaysView(context: Context, width: Int, height: Int): SurfaceView(context), Runnable {
    private lateinit var thread: Thread
    private lateinit var canvas: Canvas
    private var random = Random(System.currentTimeMillis())
    private var surfaceSize = PointF(width.toFloat(),height.toFloat())
    private var maxSquareHeight = min(surfaceSize.x,surfaceSize.y)
    private var squareStartPointF= PointF(0f+(surfaceSize.x-maxSquareHeight)/2f,0f+(surfaceSize.y-maxSquareHeight)/2f)
    private var squareRectF = RectF(squareStartPointF.x,squareStartPointF.y,squareStartPointF.x+maxSquareHeight,squareStartPointF.y+maxSquareHeight)
    private var paint = Paint()
    @Volatile
    private var drawing = false
    private var paused = false
    private var gameOver = false
    private var showing = false

    private var lastFrameMillis : Long = 0
    private var millis: Long= 0
    private var fps: Long = 0
    private var gameTimer = Timer(0)

    private var score: Long = 0

    init{

    }
    override fun run(){
        while(drawing){
            val frameStartTime = System.currentTimeMillis()
            millis = if(lastFrameMillis>0){
                frameStartTime- lastFrameMillis
            }else{
                0
            }
            lastFrameMillis=frameStartTime

            if(!paused && !gameOver){
                update(millis)
                gameTimer.increase(millis)
            }

            draw()

            if(millis>0){
                fps=1000/millis
            }
        }
    }
    private fun draw(){
        if(holder.surface.isValid){
            if(!paused) {

                canvas = holder.lockCanvas()

                paint.style = Paint.Style.FILL

                canvas.drawColor(Color.argb(255, 255, 255, 255))

                /** TODO: Make this time bar
                paint.style = Paint.Style.FILL
                paint.color= Color.argb(255,255,0,0)
                canvas.drawRect(RectF(0f,0f,surfaceSize.x,ratioHeight*1f),paint)
                paint.color= Color.argb(255,0,255,0)
                canvas.drawRect(RectF(0f,0f,surfaceSize.x*health/maxHealth,ratioHeight*1f),paint)
                paint.style = Paint.Style.STROKE
                paint.color= Color.argb(255,0,0,0)
                canvas.drawRect(RectF(0f,0f,surfaceSize.x,ratioHeight*1f),paint)
                */
                paint.style = Paint.Style.FILL
                paint.textSize=maxSquareHeight/25f
                paint.color=(Color.argb(255, 11, 10, 5))
                canvas.drawText("Time: ${gameTimer.get()/60000} : ${gameTimer.get()/1000}",maxSquareHeight*.4f, maxSquareHeight*.4f,paint)

                }else{

                canvas = holder.lockCanvas()
                paint.style = Paint.Style.FILL
                canvas.drawColor(Color.argb(255, 255, 255, 255))
                if (gameOver){
                    }else {
                }
            }
            holder.unlockCanvasAndPost(canvas)
        }
    }
    private fun update(millis: Long){

    }
    fun pause() {
        // Set drawing to false
        // Stopping the thread isn't
        // always instant
        drawing = false
        try {
            // Stop the thread
            thread.join()
            lastFrameMillis=0
        } catch (e: InterruptedException) {
            Log.e("Error:", "joining thread")
        }
        lastFrameMillis=0

    }
    fun resume() {
        drawing = true
        // Initialize the instance of Thread
        thread = Thread(this)
        // Start the thread
        thread.start()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(
        motionEvent: MotionEvent
    ): Boolean {
        if (motionEvent.action and MotionEvent.
            ACTION_MASK ==
            MotionEvent.ACTION_MOVE) {
            if (!paused){
                //probably no need for dragging
            }
        }
        if (motionEvent.action and MotionEvent.ACTION_MASK ==
            MotionEvent.ACTION_DOWN) {
            if (gameOver){
                gameTimer.set(0)
                gameOver= false
                paused=false
            }else {

            }
        }
        return true
    }

}