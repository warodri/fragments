package com.example.fragmentss.fragmentsstartingpoint;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    *   My fragment adapter will handle all
    *   the fragments in this activity
    */
    private MyFragmentAdapter mMyFragmentAdapter;

    /**
     * view Page is for changing from fragment to
     * fragment as I click on buttons
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create my fragment adapter
        mMyFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());

        // Reference View Page with layout and init...
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        // Add all the fragments to my adapter
        mMyFragmentAdapter.addFragment(new Fragment1(), "Fragment1");
        mMyFragmentAdapter.addFragment(new Fragment2(), "Fragment2");
        mMyFragmentAdapter.addFragment(new Fragment3(), "Fragment3");
        viewPager.setAdapter(mMyFragmentAdapter);
    }

    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }

}
