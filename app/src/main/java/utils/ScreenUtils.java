package utils;

import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 获取屏幕宽高的工具类
 */
public class ScreenUtils {
    private static volatile ScreenUtils instance;
    private DisplayMetrics dm;

    private ScreenUtils(WindowManager manager) {
        //1、通过WindowManager获取
        dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
    }

    public static ScreenUtils getInstance(WindowManager manager) {
        if (instance == null) {
            synchronized (ScreenUtils.class) {
                if (null == instance) {
                    instance = new ScreenUtils(manager);
                }
            }
        }
        return instance;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getWidth() {
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public int getHeight() {
        return dm.heightPixels;
    }
}
