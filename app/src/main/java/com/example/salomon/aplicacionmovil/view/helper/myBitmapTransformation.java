package com.example.salomon.aplicacionmovil.view.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class myBitmapTransformation extends BitmapTransformation {
    private Context aContext;

    public myBitmapTransformation(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return bitmapChanger(toTransform, outWidth, outHeight);
    }

    @Override public String getId() { return "some_id"; }

    private static Bitmap bitmapChanger(Bitmap bitmap, int desiredWidth, int desiredHeight) {
        float originalWidth = bitmap.getWidth();
        float originalHeight = bitmap.getHeight();

        float scaleX = desiredWidth / originalWidth;
        float scaleY = desiredHeight / originalHeight;

        //Use the larger of the two scales to maintain aspect ratio
        float scale = Math.max(scaleX, scaleY);

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitmap, 0, 0, (int) originalWidth, (int) originalHeight, matrix, true);
    }
}
