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

import com.facebook.react.bridge.ReadableArray;

/**
 * Created by Surajit Sarkar on 22/12/17.
 * Company : Bitcanny Technologies Pvt. Ltd.
 * Email   : surajit@bitcanny.com
 */

public class GradientView extends View {

    private RectF bounds;
    private Paint paint;
    private float centerX;
    private float centerY;
    private float radius;
    private int[] colors;
    float[] stops = new float[]{0.0f, 0.2f, 0.42f, 0.6f, 0.75f};

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
        centerX = -1;
        centerY = -1;
        radius = -1;

        colors = new int[]{Color.RED,Color.BLUE};
    }

    private void populateColors(int color){
        //make four shades for the color
        colors = new int[]{
                color,
                ColorUtils.setAlphaComponent(color, 0xA7),
                ColorUtils.setAlphaComponent(color, 0x60),
                ColorUtils.setAlphaComponent(color, 0x44),
                ColorUtils.setAlphaComponent(color, 0x00)
        };
    }

    private void populateDefaultCenterValue(float width, float height){
        if(centerX<0){
            centerX = width/2;
        }
		if(centerY<0){
            centerY = height/2;
        }
		if(radius<= 0 ){
            radius = Math.min(width,height)/2;
        }
    }

    public void setColors(ReadableArray colors) {
        if(colors == null || colors.size() == 0)
            return;
        if (colors.size() == 1){
            populateColors(colors.getInt(0));
        } else{
            int[] _colors = new int[colors.size()];
            for (int i=0; i < _colors.length; i++)
            {
                _colors[i] = colors.getInt(i);
            }
            this.colors = _colors;
        }
        drawGradient();
    }

    public void setCenter(ReadableArray center){
        if(center == null || center.size()!=2 )
            return;
        centerX = center.getInt(0);
        centerY = center.getInt(1);
        invalidate();
    }

    public void setRadius(int radius){
        if(radius<=2)
            return;
        this.radius = radius;
        invalidate();
    }

    private void drawGradient(){
        RadialGradient gradient = new RadialGradient(centerX,centerY,radius,colors,null,Shader.TileMode.CLAMP);
        paint.setShader(gradient);

        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bounds.set(0f, 0f, w, h);
        populateDefaultCenterValue(w,h);
        drawGradient();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(bounds,paint);
    }
}
