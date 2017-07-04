package ua.r4mstein.moviedbdemo.utills;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.base.Router;

public final class NetworkManager {

    private static final String TAG = "NetworkManager: ";

    public NetworkManager() {

    }

    public static boolean isOnline(final Context _context) {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            switch (networkInfo.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                case ConnectivityManager.TYPE_MOBILE:
                    Logger.d(TAG + "isOnline: true");
                    return true;
                default:
                    Logger.d(TAG + "isOnline: false");
                    return false;
            }
        } else {
            Logger.d(TAG + "isOnline: false");
            return false;
        }
    }

    public static void getInfoDialog(Router router) {
        router.showInfoDialog(R.string.app_name, R.string.without_internet_message, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public static void getInfoDialogWithFinish(Router router) {
        router.showInfoDialog(R.string.app_name, R.string.without_internet_message, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                router.finishActivity();
            }
        });
    }
}
