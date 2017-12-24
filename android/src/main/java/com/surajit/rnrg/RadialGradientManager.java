package com.surajit.rnrg;


import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

/**
 * Created by Surajit on 12/23/2017.
 */

public class RadialGradientManager extends SimpleViewManager<GradientView> {

    public static final String REACT_CLASS = "SRSRadialGradient";
    public static final String PROP_COLORS = "colors";
    public static final String PROP_CENTER = "center";
    public static final String PROP_RADIUS = "radius";
    public static final String PROP_STOPS = "stops";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected GradientView createViewInstance(ThemedReactContext reactContext) {
        return new GradientView(reactContext);
    }

    @ReactProp(name=PROP_COLORS)
    public void setColors(GradientView gradientView, ReadableArray colors) {
        gradientView.setColors(colors);
    }

    @ReactProp(name=PROP_CENTER)
    public void setCenter(GradientView gradientView, ReadableArray center) {
        gradientView.setCenter(center);
    }

    @ReactProp(name=PROP_RADIUS)
    public void setRadius(GradientView gradientView, float radius) {
        gradientView.setRadius(radius);
    }

    @ReactProp(name=PROP_STOPS)
    public void setStops(GradientView gradientView, ReadableArray stops) {
        gradientView.setStops(stops);
    }
}
