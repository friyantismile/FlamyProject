package com.training.flamyproject;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FlameCalcuAdapter  extends FragmentStatePagerAdapter{
	List<Fragment> listFragments;
	public FlameCalcuAdapter(FragmentManager fragmentManager, List<Fragment> listFragments) {
		super(fragmentManager);
		this.listFragments= listFragments;
	}

	@Override
	public Fragment getItem(int location) {
		// TODO Auto-generated method stub
		return listFragments.get(location);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listFragments.size();
	}

}
