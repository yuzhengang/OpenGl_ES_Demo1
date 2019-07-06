package com.yzg.opengl_es;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by yuzhengang on 2019/7/6.
 */

public class WlGLSurfaceView extends GLSurfaceView {
    public WlGLSurfaceView(Context context) {
        super(context);
    }
    public WlGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
        setRenderer(new WlRender(context));
    }

}
