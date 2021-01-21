package com.example.tiltsentor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

//커스텀뷰 제작(기본 제공 뷰=MainActivity 가 아닌 것)
class TiltView(context: Context?) : View(context) {
    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    private val blackTextPaint : Paint = Paint()
    private var cX:Float = 0f
    private var cY:Float = 0f

    private var cX2:Float = 760f
    private var cY2:Float = 500f

    private var cX3:Float = 40f
    private var cY3:Float = 300f

    private var xCoord:Float = 0f
    private var yCoord:Float = 0f
    private var xloc:String = ""
    private var yloc:String=""

    init{
        greenPaint.color = Color.GREEN //녹색 페인트
        blackPaint.style = Paint.Style.STROKE //검은색 테두리 페인트
        blackTextPaint.strokeWidth = 2f
        blackTextPaint.textSize = 50f
    }
    //뷰의 크기가 변경될 때 호출
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w / 2f //원중점좌표 계산
        cY = h / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(cX, cY, 100f, blackPaint) //바깥 원1
        canvas?.drawCircle(xCoord+cX, yCoord+cY, 100f, greenPaint) //내부 원1
        //가운데 십자가1
        canvas?.drawLine(cX-20, cY, cX+20, cY, blackPaint)
        canvas?.drawLine(cX,cY-20, cX,cY+20, blackPaint)

        canvas?.drawCircle(cX2, cY2, 80f, blackPaint) //바깥 원2
        canvas?.drawCircle(yCoord+cX2, xCoord+cY2, 80f, greenPaint) //내부 원2
        //가운데 십자가2
        canvas?.drawLine(cX2-5, cY2, cX2+5, cY2, blackPaint)
        canvas?.drawLine(cX2,cY2-5, cX2,cY2+5, blackPaint)

        canvas?.drawCircle(cX3, cY3, 50f, blackPaint) //바깥 원3
        canvas?.drawCircle(xCoord+cX3, xCoord+cY3, 50f, greenPaint) //내부 원3
        //가운데 십자가3
        canvas?.drawLine(cX3-20, cY3, cX3+15, cY3, blackPaint)
        canvas?.drawLine(cX3,cY3-20, cX3,cY3+15, blackPaint)

        canvas?.drawText("x : "+ "$xloc" + "y : "+"$yloc  ", 800f, 800f, blackTextPaint)
    }
    //센서값을 뷰에 반영하기
    fun onSensorEvent(event: SensorEvent){
        //화면을 가로로 돌렸으므로 x축과 y축 교환
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20
        yloc = event.values[0].toString()
        xloc= event.values[1].toString()

        invalidate() //onDraw()메서드 다시 호출
    }
}