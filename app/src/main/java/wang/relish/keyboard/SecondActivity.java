package wang.relish.keyboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

import wang.relish.keyboard.util.DisplayMetricsHolder;

/**
 * @author relish <a href="mailto:relish.wang@gmail.com">Contact me.</a>
 * @since 20190719
 */
public class SecondActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, SecondActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        setContentView(tv);

        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(getApplicationContext());

        DisplayMetrics metrics = DisplayMetricsHolder.getScreenDisplayMetrics();
        if (metrics != null) {
            tv.setText("screenWidth:" + metrics.widthPixels + " px\nscreenHeight:" + metrics.heightPixels + " px");
        } else {
            tv.setText("cannot get screen height.");
        }
    }
}
