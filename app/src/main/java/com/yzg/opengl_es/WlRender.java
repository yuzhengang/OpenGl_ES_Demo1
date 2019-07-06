package com.yzg.opengl_es;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by yuzhengang on 2019/7/6.
 */

public class WlRender   implements GLSurfaceView.Renderer{
    private Context   context;

    /**
     *  1.绘制坐标范围
     */
    private final float[ ]  vertexData={
       -1f,0f,
       0f,1f,
       1f,0f
    };
    private FloatBuffer vertexBuffer;
    private int program;
    private int avPosition;
    private int afColor;

    public  WlRender(Context  context){
        this.context=context;
        /**
         *  2.为坐标分配本地内存地址
         */
        vertexBuffer= ByteBuffer.allocateDirect(vertexData.length*4)
                      .order(ByteOrder.nativeOrder())
                      .asFloatBuffer()
                      .put(vertexData);
        vertexBuffer.position(0);
    }
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        String   vertexSource=WlShaderUtil.readRawTxt(context,R.raw.vertex_shader);
        String  fragmentSource=WlShaderUtil.readRawTxt(context,R.raw.fragment_shader);
        program =WlShaderUtil.createProgram(vertexSource,fragmentSource);
        if(program>0){
            avPosition = GLES20.glGetAttribLocation(program, "av_Position");
            afColor = GLES20.glGetUniformLocation(program, "af_Color");
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glUseProgram(program);
        GLES20.glUniform4f(afColor, 1f, 0f, 0f, 1f);
        GLES20.glEnableVertexAttribArray(avPosition);
        GLES20.glVertexAttribPointer(avPosition, 2, GLES20.GL_FLOAT, false, 8, vertexBuffer);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);

    }
}
