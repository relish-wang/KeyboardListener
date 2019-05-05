package wang.relish.keyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

import wang.relish.keyboard.util.GlobalLayoutListener;
import wang.relish.keyboard.util.OnKeyboardChangedListener;
import wang.relish.keyboard.util.PixelUtil;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "KeyboardListener";


    TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvResult = findViewById(R.id.tv_result);

        final View rootView = findViewById(R.id.ll_root);
        rootView.post(new Runnable() {
            @Override
            public void run() {
                rootView.getViewTreeObserver().addOnGlobalLayoutListener(
                        new GlobalLayoutListener(rootView, new OnKeyboardChangedListener() {
                            @Override
                            public void onChange(boolean isShow, Map<String, Map<String, Object>> map) {
                                Log.d(TAG, "isShow: " + isShow);
                                if (isShow) {
                                    for (String key : map.keySet()) {
                                        Log.d(TAG, key + ": " + map.get(key));
                                    }
                                }
                            }
                        }));
            }
        });
    }


    float mKeyBoardHeight;
    float mScreenHeight;

    private void updateKeyboardHeight(boolean isShow, Map<String, Map<String, Object>> map) {
        if (isShow) {
            if (map != null) {
                Map<String, Object> endCoordinates = map.get("endCoordinates");
                if (endCoordinates != null) {
                    Object height = endCoordinates.get("height");
                    if (height != null && height instanceof Number) {
                        mKeyBoardHeight = PixelUtil.toPixelFromDIP(((Number) height).floatValue());
                    }
                    Object screenY = endCoordinates.get("screenY");
                    if (height != null && height instanceof Number) {
                        mScreenHeight = PixelUtil.toPixelFromDIP(((Number) screenY).floatValue());
                    }
                }
            }
        } else {
            mKeyBoardHeight = 0;
        }
    }
}
