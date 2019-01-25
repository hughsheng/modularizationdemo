package com.tl.sztangli.modularizationdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * created by tl on 2019-1-25
 */
public class VpAdapter extends FragmentPagerAdapter {

  private List<Fragment> fragmentList;

  public VpAdapter(FragmentManager fm, List<Fragment> fragmentList) {
    super(fm);
    this.fragmentList = fragmentList;
  }

  @Override
  public Fragment getItem(int i) {
    return fragmentList.get(i);
  }

  @Override
  public int getCount() {
    return fragmentList.size();
  }
}
