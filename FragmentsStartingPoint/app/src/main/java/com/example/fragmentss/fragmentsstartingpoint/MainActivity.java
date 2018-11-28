package com.example.fragmentss.fragmentsstartingpoint;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IFragmentCommunication {

    private static final String TAG = "MainActivity";

    private MyFragmentAdapter mMyFragmentAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        initFragmentTransactionComponents();
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




    /**
     * THIS IS THE TRANSACTIONAL PART
     * WHERE I ADD FRAGMENTS USING MY FRAGMENTS TRANSACTOR
     * AND THE INTERFACE FOR SENDING MESSAGES TO
     * THIS PARENT ACTIVITY
     */

    private TextView txtTextFromFragment;
    private EditText editMessageToFragment;
    private Button butSendTextToFragment;
    MyFragmentsTransactor transactor = null;


    private void initFragmentTransactionComponents() {
        txtTextFromFragment = (TextView) findViewById(R.id.textFromFragment);
        editMessageToFragment = (EditText) findViewById(R.id.editMessageToFragment);
        butSendTextToFragment = (Button) findViewById(R.id.butSendTextToFragment);
        butSendTextToFragment.setOnClickListener(new ViewPager.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragmentUsingMyFragmentsTransactor();
            }
        });
    }

    private void addFragmentUsingMyFragmentsTransactor() {

        // The fragment I want to insert
        FragmentAttachedUsingMyFragmentsTransactor f =
                new FragmentAttachedUsingMyFragmentsTransactor();

        // Create the object if null
        if (transactor == null) {
            transactor = new MyFragmentsTransactor(this, R.id.fragmentContainer);
        }

        String theMessage = editMessageToFragment.getText().toString();
        if (!theMessage .equals("")) {
            // Do the insert
            transactor.doFragmentTransaction(f, "My inserted fragment",
                    true, theMessage);
        } else {
            Toast.makeText(this, "Enter a message first to send to the Fragment",
                    Toast.LENGTH_LONG).show();
        }

    }

    /**
     * From IFragmentCommunication Interface
     * @param textFromFragment
     */
    @Override
    public void setMessage(String textFromFragment ) {

        Log.i(TAG, "Message received from Fragment");

        txtTextFromFragment.setText(textFromFragment);
    }

    @Override
    public void inflateFragment(Fragment f, String fragmentTitle, boolean addToBackStack, String message) {

        Log.i(TAG, "From some Fragment or Activity I can inflate another fragment");

        if (transactor == null) {
            transactor = new MyFragmentsTransactor(this, R.id.fragmentContainer);
        }
        transactor.doFragmentTransaction(f, message, addToBackStack, message);
    }



}
