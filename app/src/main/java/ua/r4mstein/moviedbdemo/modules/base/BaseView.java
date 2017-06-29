package ua.r4mstein.moviedbdemo.modules.base;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import ua.r4mstein.moviedbdemo.modules.dialog.ChooseActionDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.DialogRating;
import ua.r4mstein.moviedbdemo.modules.dialog.listeners.ChooseActionClickListener;
import ua.r4mstein.moviedbdemo.modules.dialog.listeners.DialogRatingClickListener;

public interface BaseView {

    Bundle getViewArguments();

    Context getViewContext();

    Point getDisplaySize();

    FragmentManager getFragmManager();

    Resources getAppResources();

    ChooseActionClickListener getChooseActionClickListener(final long movieId, final ChooseActionDialog dialog);

    DialogRatingClickListener getDialogRatingClickListener(final long movieId, final DialogRating dialogRating);
}
