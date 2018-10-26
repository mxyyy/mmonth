package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        measureChildren(0, 0);
        int totalWidth = 0;
        int totalHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            // View添加点击事件
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(v, position);
                    }
                }
            });
            // 当前view的宽度
            int vw = view.getMeasuredWidth();
            // 当前view的高度
            int vh = view.getMeasuredHeight();
            // 如果总的View宽度加上当前View的宽度大于控件的宽度
            if (totalWidth + vw > getMeasuredWidth()) {
                // 执行换行操作，总宽度设置为0，高度加上当前view的高度
                totalWidth = 0;
                totalHeight += vh;
            }
            view.layout(totalWidth, totalHeight, totalWidth + vw,
                    totalHeight + vh);
            // 总的宽度在原有宽度的基础上加上当前view的宽度
            totalWidth += vw;
        }
    }
}
