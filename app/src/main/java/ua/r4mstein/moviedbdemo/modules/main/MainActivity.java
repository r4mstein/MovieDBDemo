package ua.r4mstein.moviedbdemo.modules.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.DimenHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.base.BaseActivity;
import ua.r4mstein.moviedbdemo.utills.Logger;

public class MainActivity extends BaseActivity<MainPresenter> {

    private Toolbar mToolbar;

    private Drawer mDrawer;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public int getFragmentContainer() {
        return R.id.flFrameCont_AM;
    }

    @Override
    protected void findUI() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
    }

    @Override
    protected void setupUI() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportFragmentManager().addOnBackStackChangedListener(() ->
                getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount() > 0)
        );

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withHeader(R.layout.main_header)
                .withHeaderHeight(DimenHolder.fromDp(175))
                .addDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_genres).withIcon(R.drawable.ic_one)
                                .withSelectedIcon(R.drawable.ic_one_select),
                        new PrimaryDrawerItem().withIdentifier(2).withName(R.string.drawer_item_lists).withIcon(R.drawable.ic_two)
                                .withSelectedIcon(R.drawable.ic_two_select),
                        new PrimaryDrawerItem().withIdentifier(3).withName(R.string.drawer_item_favorite).withIcon(R.drawable.ic_three)
                                .withSelectedIcon(R.drawable.ic_three_select),
                        new PrimaryDrawerItem().withIdentifier(4).withName(R.string.drawer_item_watchlist).withIcon(R.drawable.ic_four)
                                .withSelectedIcon(R.drawable.ic_four_select)
                )
                .withActionBarDrawerToggleAnimated(true)
                .withOnDrawerItemClickListener(getOnDrawerItemClickListener())
                .build();

        mDrawer.openDrawer();

    }

    @NonNull
    private Drawer.OnDrawerItemClickListener getOnDrawerItemClickListener() {
        return (view, position, drawerItem) -> {
            switch ((int) drawerItem.getIdentifier()) {
                case 1:
                    getPresenter().addGenresFragment();
                    mDrawer.closeDrawer();
                    break;
                case 2:
                    getPresenter().addListsFragment();
                    mDrawer.closeDrawer();
                    break;
                case 3:
                    getPresenter().addFavoriteMoviesFragment();
                    mDrawer.closeDrawer();
                    break;
                case 4:
                    getPresenter().addWatchlistFragment();
                    mDrawer.closeDrawer();
                    break;
            }
            return true;
        };
    }
}
