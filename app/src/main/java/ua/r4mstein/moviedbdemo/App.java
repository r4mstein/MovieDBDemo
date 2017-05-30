package ua.r4mstein.moviedbdemo;

import android.app.Application;

public class App extends Application {

    private static App instance;
//    private static UserModel userModel;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initialGlobalComponents();
    }

    private void initialGlobalComponents() {

    }

//    public UserModel getUser() {
//        return userModel;
//    }
//
//    public void setUser(UserModel userModel) {
//        App.userModel = userModel;
//    }
}
