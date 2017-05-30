package ua.r4mstein.moviedbdemo.modules.base;

import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxSearchView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ua.r4mstein.moviedbdemo.App;
import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.utills.KeyboardManager;

public class MenuController {

    private SearchView mSearchView;
    private ImageView clearButton;

    private SearchListener mListener;
    private String query = "";
    private boolean isSearchActivated;
    private Disposable mSearchListener;

    private List<Integer> items;

    private Menu mMenu;

    public MenuController() {
        items = new ArrayList<>();
    }

    public void onCreateOptionsMenu(Menu _menu, MenuInflater inflater) {
        mMenu = _menu;

        for (Integer item : items) {
            inflater.inflate(item, mMenu);
        }

//     MenuItem menuSearch = _menu.findItem(R.id.action_search);
//        if (menuSearch != null) {
//            setupSearchView(menuSearch);
//        }
    }


    public String getKeyWord() {
        return query;
    }

    private void styleSearchView() {
       ColorFilter filter = new PorterDuffColorFilter(ContextCompat.getColor(App.getInstance(), R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        int searchCloseButtonId = mSearchView.getContext().getResources()
                .getIdentifier("android:id/search_close_btn", null, null);
        clearButton = (ImageView) this.mSearchView.findViewById(searchCloseButtonId);

        int searchPlateId = mSearchView.getContext().getResources()
                .getIdentifier("android:id/search_plate", null, null);
        View searchPlate = mSearchView.findViewById(searchPlateId);

        int magId = mSearchView.getContext().getResources()
                .getIdentifier("android:id/search_mag_icon", null, null);
        ImageView magImage = (ImageView) mSearchView.findViewById(magId);

        int searchSrcTextId = mSearchView.getContext().getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = (EditText) mSearchView.findViewById(searchSrcTextId);

        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(searchEditText, R.drawable.cursor);
        } catch (Exception ignored) {
        }

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            clearButton.getDrawable().setColorFilter(filter);
            clearButton.setBackground(null);
            searchPlate.getBackground().setColorFilter(filter);
            magImage.getDrawable().setColorFilter(filter);
        }
    }

    private void setupSearchView(MenuItem menuSearch) {
        mSearchView = (SearchView) menuSearch.getActionView();
        mSearchView.setFocusable(true);

        styleSearchView();
        removeListener();

        clearButton.setOnClickListener(v -> mSearchView.setQuery("", true));

        MenuItemCompat.setOnActionExpandListener(menuSearch, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                isSearchActivated = true;
                setVisibleMenuItem(mMenu, false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                isSearchActivated = false;
                setVisibleMenuItem(mMenu, true);
                mSearchView.clearFocus();

                if (mSearchView.getQuery().length() > 0) {
                    mSearchView.setQuery("", false);
                }
                return true;
            }
        });

        if (isSearchActivated) {
            mSearchView.setQuery(query, false);
            mSearchView.setIconified(false);
            menuSearch.expandActionView();
        } else {
            mSearchView.setIconified(true);
        }
        addListener();
    }


    private void setVisibleMenuItem(Menu menu, boolean isVisible) {
        if (menu != null) {
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setVisible(isVisible);
            }
        }
    }

    public void closeSearch() {
        isSearchActivated = false;
        if (!mSearchView.getQuery().toString().isEmpty())
            mSearchView.setQuery("", true);
        setVisibleMenuItem(mMenu, true);
    }

    public void removeListener() {
        if (mSearchListener != null) {
            if (!mSearchListener.isDisposed()) mSearchListener.dispose();
        }
    }

    public void addListener() {
        mSearchListener = RxSearchView.queryTextChanges(mSearchView)
                .skip(1)
                .debounce(850, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> {
                    query = charSequence.toString();
                    if (mListener != null) mListener.onSearchQuery();
                    mSearchView.clearFocus();

//                    /*mSearchView.requestFocus(); todo activate for not hide keyboard
                    KeyboardManager.showKeyboard(mSearchView.getContext());
                });

    }

    public void addSearchListener(SearchListener listener) {
        mListener = listener;
    }

    public void add(@MenuRes int action) {
        items.add(action);
    }

    public void addMenuItem(@IdRes int action, @StringRes int titleRes, @DrawableRes int iconRes) {
        if (mMenu != null) {
            mMenu.add(0, action, 0, titleRes)
                    .setIcon(iconRes)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
    }

    public void removeMenuItem(@IdRes int action) {
        if (action != 0 && mMenu != null) {
            mMenu.removeItem(action);
        }
    }

    public MenuItem getMenuItem(@IdRes int id) {
        if (id != 0 && mMenu != null) {
            return mMenu.findItem(id);
        }
        return null;
    }

    public void setItemVisibility(@IdRes int id, boolean isVisible) {
        MenuItem item = getMenuItem(id);
        if (item != null) item.setVisible(isVisible);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_search:
//                mSearchView.setIconified(false);
//                mSearchView.requestFocusFromTouch();
//                return true;
        }
        return false;
    }


    public static class Builder {

        MenuController controller;

        public Builder() {
            controller = new MenuController();
        }

        public Builder addMenuItem(int action) {
            if (action != 0)
                controller.add(action);
            return this;
        }

        public Builder addSearchListener(SearchListener listener) {
            controller.addSearchListener(listener);
            return this;
        }

        public MenuController build() {
            return controller;
        }

    }

    public interface SearchListener {

        void onSearchQuery();

    }

}
