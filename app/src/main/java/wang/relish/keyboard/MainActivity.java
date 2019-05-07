package wang.relish.keyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import wang.relish.keyboard.util.GlobalLayoutListener;
import wang.relish.keyboard.util.OnKeyboardChangedListener;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "KeyboardListener";


    TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvResult = findViewById(R.id.tv_result);

        final View rootView = findViewById(R.id.ll_root);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(
                new GlobalLayoutListener(rootView, new OnKeyboardChangedListener() {
                    @Override
                    public void onChange(boolean isShow, int keyboardHeight, int screenWidth, int screenHeight) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("isShow: ").append(isShow).append("\n")
                                .append("keyboardHeight: ").append(keyboardHeight).append("\n")
                                .append("screenWidth: ").append(screenWidth).append("\n")
                                .append("screenHeight: ").append(screenHeight).append("\n");
                        mTvResult.append(sb.toString() + "\n");

                        Log.d(TAG, "键盘是否展开: " + isShow);
                        Log.d(TAG, "键盘高度(px): " + keyboardHeight);
                        Log.d(TAG, "屏幕宽度(px): " + screenWidth);
                        Log.d(TAG, "屏幕高度(px): " + screenHeight);
                    }
                }));
    }
}
