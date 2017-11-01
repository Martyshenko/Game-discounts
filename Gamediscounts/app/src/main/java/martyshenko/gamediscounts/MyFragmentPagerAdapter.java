package martyshenko.gamediscounts;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public MyFragmentPagerAdapter (FragmentManager fm, Context context){
        super (fm);
        mContext=context;}


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DealsFragment();
        } else
            return new WishlistFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getString(R.string.category_deals);
            case 1:
                return mContext.getString(R.string.category_wish_list);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

}

