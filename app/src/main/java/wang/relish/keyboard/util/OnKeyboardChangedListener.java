package wang.relish.keyboard.util;

import java.util.Map;

/**
 * @author Relish Wang
 * @since 2018/12/12
 */
public interface OnKeyboardChangedListener {
    void onChange(boolean isShow, Map<String, Map<String, Object>> map);
}
