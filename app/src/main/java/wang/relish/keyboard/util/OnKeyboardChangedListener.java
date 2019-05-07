package wang.relish.keyboard.util;

/**
 * 键盘收起
 * @author Relish Wang
 * @since 2018/12/12
 */
public interface OnKeyboardChangedListener {
    /**
     * 键盘事件
     *
     * @param isShow         键盘是否展示
     * @param keyboardHeight 键盘高度(当isShow为false时,keyboardHeight=0)
     * @param screenWidth    屏幕宽度
     * @param screenHeight   屏幕可用高度(不包含底部虚拟键盘NavigationBar), 即屏幕高度-键盘高度(keyboardHeight)
     */
    void onChange(boolean isShow, int keyboardHeight, int screenWidth, int screenHeight);
}
