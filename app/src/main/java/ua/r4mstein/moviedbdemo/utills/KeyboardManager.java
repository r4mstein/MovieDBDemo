package ua.r4mstein.moviedbdemo.utills;

import android.app.Activity;
import android.content.Context;
import android.os.ResultReceiver;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class KeyboardManager {

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText())
            try {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            } catch (Throwable t) {

            }
    }

    public static void showKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm != null) {
            Method showSoftInputUnchecked = null;
            try {
                showSoftInputUnchecked = imm.getClass()
                        .getMethod("showSoftInputUnchecked", int.class, ResultReceiver.class);
            } catch (NoSuchMethodException e) {
                // Log something
            }

            if (showSoftInputUnchecked != null) {
                try {
                    showSoftInputUnchecked.invoke(imm, 0, null);
                } catch (IllegalAccessException e) {
                    // Log something
                } catch (InvocationTargetException e) {
                    // Log something
                }
            }
        }
    }
}
