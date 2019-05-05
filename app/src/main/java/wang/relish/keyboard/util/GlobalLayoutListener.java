package wang.relish.keyboard.util;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.HashMap;
import java.util.Map;

/**
 * 此文件代码来自com.facebook.react.ReactRootView#CustomGlobalLayoutListener
 *
 * @author Relish Wang
 * @since 2019/05/02
 */
public class GlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
    private final Rect mVisibleViewArea;
    private final int mMinKeyboardHeightDetected;

    private int mKeyboardHeight = 0;

    private View mView;
    private OnKeyboardChangedListener mListener;

    public GlobalLayoutListener(View v, OnKeyboardChangedListener l) {
        mView = v;
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(mView.getContext().getApplicationContext());
        mVisibleViewArea = new Rect();
        mMinKeyboardHeightDetected = (int) PixelUtil.toPixelFromDIP(60);
        mListener = l;
    }

    @Override
    public void onGlobalLayout() {
        if (mView == null) {
            return;
        }
        checkForKeyboardEvents();
    }

    private void checkForKeyboardEvents() {
        mView.getRootView().getWindowVisibleDisplayFrame(mVisibleViewArea);
        //noinspection ConstantConditions
        final int heightDiff =
                DisplayMetricsHolder.getWindowDisplayMetrics().heightPixels - mVisibleViewArea.bottom;
        if (mKeyboardHeight != heightDiff && heightDiff > mMinKeyboardHeightDetected) {
            // keyboard is now showing, or the keyboard height has changed
            mKeyboardHeight = heightDiff;
            Map<String, Map<String, Object>> params = new HashMap<>();
            Map<String, Object> coordinates = new HashMap<>();
            coordinates.put("screenY", PixelUtil.toDIPFromPixel(mVisibleViewArea.bottom));
            coordinates.put("screenX", PixelUtil.toDIPFromPixel(mVisibleViewArea.left));
            coordinates.put("width", PixelUtil.toDIPFromPixel(mVisibleViewArea.width()));
            coordinates.put("height", PixelUtil.toDIPFromPixel(mKeyboardHeight));
            params.put("endCoordinates", coordinates);
            if (mListener != null) mListener.onChange(true, params);
        } else if (mKeyboardHeight != 0 && heightDiff <= mMinKeyboardHeightDetected) {
            // keyboard is now hidden
            mKeyboardHeight = 0;
            if (mListener != null) mListener.onChange(false, null);
        }
    }
}