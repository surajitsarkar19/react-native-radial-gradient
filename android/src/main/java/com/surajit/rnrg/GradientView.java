package com.surajit.rnrg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Surajit Sarkar on 22/12/17.
 * Company : Bitcanny Technologies Pvt. Ltd.
 * Email   : surajit@bitcanny.com
 */

public class GradientView extends View {

    private RectF bounds;
    private Paint paint;
    private int defaultColor;

    public GradientView(Context context) {
        super(context);
        init();
    }

    public GradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        bounds  = new RectF();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        defaultColor = Color.BLUE;

    }

    private int[] populateColor(int color){
        int colors[] = new int[]{
                color,
                ColorUtils.setAlphaComponent(color,0xA7),
                ColorUtils.setAlphaComponent(color,0x60),
                ColorUtils.setAlphaComponent(color,0x44),
                ColorUtils.setAlphaComponent(color,0x00)
        };
        return colors;
    }

    private Shader shaderFactory (float width, float height){
        RadialGradient gradient;
        float centerX = width/2;
        float centerY = height/2;
        float radius = Math.min(width,height)/2;
        float[] stops = new float[]{0.0f, 0.2f, 0.42f, 0.6f, 0.75f};
        int[] color = populateColor(defaultColor);
        //gradient = new RadialGradient(centerX,centerY,radius,defaultColor,Color.GREEN,Shader.TileMode.CLAMP);
        gradient = new RadialGradient(centerX,centerY,radius,color,null,Shader.TileMode.CLAMP);
        return gradient;
    }

    private void adjustBounds(float width , float height) {
        bounds.set(0f, 0f, width, height);
        paint.setShader(shaderFactory(width,height));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        adjustBounds(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(bounds,paint);
    }
}
