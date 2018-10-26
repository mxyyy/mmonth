package widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bwie.mmonth.R;


/**
 * 自定义小球
 */
public class CircleView extends View {
    private static final String TAG = "CircleView";
    private Paint paint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        Log.i(TAG, "init: ");
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleViewStyle);
        // 小球的颜色,默认白色
        int color = a.getColor(R.styleable.CircleViewStyle_color, Color.WHITE);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw: ");
        super.onDraw(canvas);
        // 防止精度丢失，直径乘10之后再除20，计算出半径
        float radius = ((float) (getMeasuredWidth() * 10)) / 20;
        Log.i(TAG, "onDraw 半径: " + radius);
        canvas.drawCircle(radius, radius, radius, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 宽高的测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 宽高的测量大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 圆的直径，也就是测量的宽高
        int diameter = 0;
        // 宽高都是EXACTLY，取两个值的比较小的值
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            diameter = Math.min(widthSize, heightSize);
        } else if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.AT_MOST) {
            // 当宽和高有一个是EXACTLY，有一个是AT_MOST时，取EXACTLY精确值
            diameter = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.EXACTLY) {
            // 当宽和高有一个是EXACTLY，有一个是AT_MOST时，取EXACTLY精确值
            diameter = heightSize;
        } else {
            // 当宽和高都是AT_MOST时，设置为1
            diameter = 1;
        }
        // 当测量的值小于1时，设置为1
        if (diameter < 1) {
            diameter = 1;
        }

        // 宽和高都设置为测量的值
        setMeasuredDimension(diameter, diameter);
    }
}
