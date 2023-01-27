package com.fonagyma.vlsg.logic

import android.content.Context
import android.graphics.*
import android.util.Log
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.pow



class Timer{
   private var millis : Long = 0
    constructor(){
        millis = 3000
    }
    constructor(_millis: Long)
    {
        millis = _millis
    }
    fun set(_millis: Long){
        millis = _millis
    }
    fun get():Long{
        return millis
    }
    fun decrease(_millis: Long) : Boolean
    {
        millis-=_millis
        return millis<1
    }
    fun increase(_millis: Long){
        millis+=_millis
    }
}

class Drawable(context: Context, resID: Int, var rotation: Float, var sizeX: Float, var sizeY: Float,
               centerXRatio: Float,centerYRatio:Float,var width : Float, var height : Float){
    private var imageBitmap: Bitmap = BitmapFactory.decodeResource(context.resources,resID)
    private var centerPointF = PointF(width*(centerXRatio)-width/2f,height*(centerYRatio)-height/2f)
    fun draw(canvas: Canvas,paint: Paint,position: PointF){
        val matrix = Matrix()
        matrix.preRotate(rotation)
        matrix.preScale(sizeX,sizeY)
        val adjustedBitmap = Bitmap.createBitmap(imageBitmap,0, 0, imageBitmap.width, imageBitmap.height, matrix, true)
        val c = rotateVector(PointF(centerPointF.x*sizeX,centerPointF.y*sizeY),-rotation/180f* Math.PI)
        //canvas.drawBitmap(adjustedBitmap,position.x-adjustedBitmap.width/2-c.x,position.y-adjustedBitmap.height/2-c.y,paint)
        canvas.drawBitmap(adjustedBitmap,null,RectF(position.x-width*(adjustedBitmap.width.toFloat()/imageBitmap.width.toFloat())/2f-c.x,
            position.y-height*(adjustedBitmap.height.toFloat()/imageBitmap.height.toFloat())/2f-c.y,position.x+width*(adjustedBitmap.width.toFloat()/imageBitmap.width.toFloat())/2f-c.x,
            position.y+height*(adjustedBitmap.height.toFloat()/imageBitmap.height.toFloat())/2f-c.y),paint)
    }
}

fun rotateVector(v : PointF, rad: Double): PointF{
    return PointF((kotlin.math.cos(rad) *v.x+ kotlin.math.sin(rad) *v.y).toFloat(),
        (kotlin.math.cos(rad) *v.y- kotlin.math.sin(rad) *v.x).toFloat())
}

//mirrors v to e
fun mirrorVectorToVector(v:PointF,e:PointF):PointF{
    val angle = atan(e.y.toDouble()/e.x.toDouble())
    val va= rotateVector(v,angle)
    va.y*=-1f
    return rotateVector(va,-angle)
}

fun directionToRotation(direction: PointF): Float{
    val rt = atan(direction.x/ abs(direction.y)) *180f/ PI

    if(rt==0.toDouble() && direction.y<0){
        return 180f
    }else if(rt<0 && direction.y>0){
         return rt.toFloat()
    }else if(rt>0 && direction.y>0){
        return rt.toFloat()
    }else if(rt<0 && direction.y<0){
        return -180f-rt.toFloat()
    }else if(rt>0 && direction.y<0){
        return 180f-rt.toFloat()
    }
    return 0f
}

