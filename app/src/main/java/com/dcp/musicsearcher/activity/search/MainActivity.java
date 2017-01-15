package com.dcp.musicsearcher.activity.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dcp.musicsearcher.R;
import com.dcp.musicsearcher.activity.search.fragments.favorites.FavoritesFragment;
import com.dcp.musicsearcher.activity.search.fragments.search.SearchFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static ArrayList<Fragment> fragments;

    private boolean isLand = false;

    private TextView mViewSearch;
    private TextView mViewFavorites;

    private ViewPager mMainPager;
    private MainPagerAdapter mMainPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewSearch = (TextView) findViewById(R.id.view_search);
        mViewFavorites = (TextView) findViewById(R.id.view_favorites);

        mMainPager = (ViewPager) findViewById(R.id.main_pager);
        if(fragments==null){
            fragments = new ArrayList<>();
            fragments.add(new SearchFragment());
            fragments.add(new FavoritesFragment());
        }

        if (mMainPager != null) {

            isLand = false;
            mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
            mMainPager.setAdapter(mMainPagerAdapter);
            mMainPager.addOnPageChangeListener(this);

            mViewSearch.setVisibility(View.VISIBLE);
            mViewFavorites.setVisibility(View.INVISIBLE);

        } else {

            isLand = true;
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.search_fragment_container, fragments.get(0), SearchFragment.class.getName())
                    .commit();


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.favorites_fragment_container, fragments.get(1), FavoritesFragment.class.getName())
                    .commit();

        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (mMainPagerAdapter.getItem(position) instanceof FavoritesFragment) {
            mViewFavorites.setVisibility(View.VISIBLE);
            mViewSearch.setVisibility(View.INVISIBLE);
        } else if (mMainPagerAdapter.getItem(position) instanceof SearchFragment) {
            mViewFavorites.setVisibility(View.INVISIBLE);
            mViewSearch.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((FavoritesFragment) fragments.get(1)).updateFavoritesData();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private class MainPagerAdapter extends FragmentStatePagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
