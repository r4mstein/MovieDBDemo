package ua.r4mstein.moviedbdemo;

import android.app.Application;

import ua.r4mstein.moviedbdemo.data.models.response.UserModel;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

public class App extends Application {

    private static App instance;
    private static UserModel userModel;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initialGlobalComponents();
        checkUser();
    }

    private void initialGlobalComponents() {

    }

    private void checkUser() {
        UserModel model = SharedPrefManager.getInstance().getUser();
        if (model != null) {
            userModel = model;
            SharedPrefManager.getInstance().clearUser();
        }
    }

    public UserModel getUser() {
        return userModel;
    }

    public void setUser(UserModel userModel) {
        App.userModel = userModel;
    }
}
