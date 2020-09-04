package com.example.demo;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    private int numoftab;
    PageAdapter(FragmentManager fn,int numoftab){
        super(fn);
        this.numoftab=numoftab;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new ECSFragment();
            case 1:
                return  new SEMFragment();
            case 2:
                return  new CNFragment();
            case 3:
                return new DSPFragment();
            case 4:
                return new SEMFragment();
                default:return null;
        }
    }
    @Override
    public int getCount(){
        return numoftab;
    }
}
