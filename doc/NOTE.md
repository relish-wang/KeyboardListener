**隐藏底部虚拟键**

```java
protected void hideBottomUIMenu() {
    //隐藏虚拟按键，并且全屏
    if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
        View v = this.getWindow().getDecorView();
        v.setSystemUiVisibility(View.GONE);
    } else if (Build.VERSION.SDK_INT >= 19) {

        Window _window = getWindow();
        WindowManager.LayoutParams params = _window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        _window.setAttributes(params);
    }
}
```