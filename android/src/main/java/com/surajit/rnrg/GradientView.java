package com.surajit.rnrg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.PixelUtil;

import java.text.DecimalFormat;

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
    float[] stops;

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
        centerX = PixelUtil.toPixelFromDIP(center.getDouble(0));
        centerY = PixelUtil.toPixelFromDIP(center.getDouble(1));
        drawGradient();
    }

    public void setRadius(float radius){
        if(radius<1)
            return;
        this.radius = PixelUtil.toPixelFromDIP(radius);
        drawGradient();
    }

    private void drawGradient(){
        if(centerX<0 || centerY<0 || radius <=0 || colors==null || colors.length <=0)
            return;
        if(stops!=null && stops.length != colors.length)
            stops = null;
        RadialGradient gradient = new RadialGradient(centerX,centerY,radius,colors,stops,Shader.TileMode.CLAMP);
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

    public void setStops(ReadableArray stops) {
        if(stops == null || stops.size() == 0)
            return;
        float[] _stops = new float[stops.size()];
        for (int i=0; i < _stops.length; i++)
        {
            _stops[i] = (float)stops.getDouble(i);
        }
        this.stops = _stops;
        drawGradient();
    }
}
