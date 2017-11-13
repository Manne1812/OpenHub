

package com.thirtydegreesray.openhub.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.thirtydegreesray.openhub.R;
import com.thirtydegreesray.openhub.inject.component.AppComponent;
import com.thirtydegreesray.openhub.ui.activity.base.PagerActivity;
import com.thirtydegreesray.openhub.ui.adapter.base.FragmentPagerModel;
import com.thirtydegreesray.openhub.ui.adapter.base.FragmentViewPagerAdapter;
import com.thirtydegreesray.openhub.ui.fragment.RepositoriesFragment;

/**
 * Created by ThirtyDegreesRay on 2017/8/26 16:56:35
 */

public class TrendingActivity extends PagerActivity {

    public static void show(@NonNull Context context){
        Intent intent = new Intent(context, TrendingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initActivity() {
        super.initActivity();
        pagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Nullable
    @Override
    protected int getContentView() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setToolbarScrollAble(true);
        setToolbarBackEnable();
        setToolbarTitle(getString(R.string.trending_repos));
        pagerAdapter.setPagerList(FragmentPagerModel.createTrendingPagerList(getActivity(), getFragments()));
        tabLayout.setVisibility(View.VISIBLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);
        showFirstPager();
    }

    @Override
    public int getPagerSize() {
        return 3;
    }

    @Override
    protected int getFragmentPosition(Fragment fragment) {
        if(fragment instanceof RepositoriesFragment){
            String since = fragment.getArguments().getString("since");
            if(since == null){
                return -1;
            }else if(since.equals("daily")){
                return 0;
            } else if(since.equals("weekly")){
                return 1;
            } else if(since.equals("monthly")){
                return 2;
            } else {
                return -1;
            }
        }else
            return -1;
    }

}
