package com.returnapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

	    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	    setSupportActionBar(toolbar);

	    ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
	    setupViewPager(viewPager);

	    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
	    tabLayout.setupWithViewPager(viewPager);
    }

	private void setupViewPager(ViewPager viewPager) {
		ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
		adapter.addFragment(new TabFragment(), "One");
		adapter.addFragment(new TabFragment(), "Two");
		adapter.addFragment(new TabFragment(), "Three");
		viewPager.setAdapter(adapter);
	}

	private class ViewPagerAdapter extends FragmentPagerAdapter {
		private final List<Fragment> tabFragments = new ArrayList<>();
		private final List<String> tabTitles = new ArrayList<>();

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public void addFragment(Fragment fragment, String title) {
			tabFragments.add(fragment);
			tabTitles.add(title);
		}

		@Override
		public Fragment getItem(int position) {
			return tabFragments.get(position);
		}

		@Override
		public int getCount() {
			return tabFragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return tabTitles.get(position);
		}
	}
}
