package wang.relish.keyboard;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import wang.relish.keyboard.util.GlobalLayoutListener;
import wang.relish.keyboard.util.OnKeyboardChangedListener;

public class MainActivity extends AppCompatActivity implements OnKeyboardChangedListener, View.OnClickListener {

    public static final String TAG = "KeyboardListener";
    public static final String KEY_SHOW_NAVIGATION_BAR = "__showNavigationBar__";


    private static void startActivityWithoutNavigationBar(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(KEY_SHOW_NAVIGATION_BAR, false);
        context.startActivity(intent);
    }

    View mLlRoot;
    /**
     * 展示键盘改变相关数据
     */
    TextView mTvResult;
    /**
     * 打开隐藏底部NavigationBar的MainActivity
     */
    TextView mBtnDismissBar;
    /**
     * 收起键盘
     */
    TextView mDismissKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isShow = getIntent().getBooleanExtra(KEY_SHOW_NAVIGATION_BAR, true);
        if (!isShow) {
            hideBottomUIMenu();
        }

        mBtnDismissBar = findViewById(R.id.btn_dismiss_bar);
        mBtnDismissBar.setOnClickListener(this);
        mBtnDismissBar.setVisibility(isShow ? View.VISIBLE : View.GONE);

        mDismissKeyboard = findViewById(R.id.btn_dismiss_keyboard);
        mDismissKeyboard.setOnClickListener(this);
        mTvResult = findViewById(R.id.tv_result);

        mLlRoot = findViewById(R.id.ll_root);
        mLlRoot.getViewTreeObserver().addOnGlobalLayoutListener(new GlobalLayoutListener(mLlRoot, this));
    }

    /**
     * 键盘事件
     *
     * @param isShow         键盘是否展示
     * @param keyboardHeight 键盘高度(当isShow为false时,keyboardHeight=0)
     * @param screenWidth    屏幕宽度
     * @param screenHeight   屏幕可用高度(不包含底部虚拟键盘NavigationBar), 即屏幕高度-键盘高度(keyboardHeight)
     */
    @Override
    public void onChange(boolean isShow, int keyboardHeight, int screenWidth, int screenHeight) {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder();
        sb.append("键盘是否展开: ").append(isShow).append("\n")
                .append("键盘高度(px): ").append(keyboardHeight).append("\n")
                .append("屏幕宽度(px): ").append(screenWidth).append("\n")
                .append("屏幕可用高度(px): ").append(screenHeight).append("\n");
        mTvResult.append(sb.toString() + "\n");

        Log.d(TAG, "键盘是否展开: " + isShow);
        Log.d(TAG, "键盘高度(px): " + keyboardHeight);
        Log.d(TAG, "屏幕宽度(px): " + screenWidth);
        Log.d(TAG, "屏幕可用高度(px): " + screenHeight);
    }


    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {

        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            Window _window = getWindow();
            WindowManager.LayoutParams params = _window.getAttributes();
            params.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
            _window.setAttributes(params);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dismiss_bar:
                MainActivity.startActivityWithoutNavigationBar(this);
                break;
            case R.id.btn_dismiss_keyboard:
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (manager != null) {
                    manager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                break;
        }
    }
}
