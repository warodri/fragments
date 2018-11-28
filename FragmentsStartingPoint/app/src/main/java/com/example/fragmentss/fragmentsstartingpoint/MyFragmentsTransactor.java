package com.example.fragmentss.fragmentsstartingpoint;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * This class will add and remove fragments
 * for this activity
 */
public class MyFragmentsTransactor {

    private AppCompatActivity mParent;
    private int mResIdFragmentContainer = -1;

    public MyFragmentsTransactor(AppCompatActivity parent, int resIdFragmentContainer) {
        mParent = parent;
        mResIdFragmentContainer = resIdFragmentContainer;
    }

    /**
     * Adds / Removes fragments to the parent Activity
     * @param fragment : The fragment
     * @param fragmentTitle : A Title for this fragment
     * @param addToBackStack: IF this fragment is going to be visible when users hit then BACK key
     * @param message: A message to pass to the parent Activity
     *
     *  Usage:
     *
     *  From your Activity, run:
     *  1) Instantiate your Fragment
     *      MyFragment f = new Fragment()
     *  2) Run this doFragmentTransaction
     *      doFragmentTransaction(f, "My First Fragment", false, "");
     *  3) To add a Fragment that will appear if we hit the BACK key:
     *      doFragmentTransaction(f, "My First Fragment", true, "");
     *
     */
    public void doFragmentTransaction(
            Fragment fragment, String fragmentTitle, boolean addToBackStack, String message) {

        FragmentTransaction transaction = mParent.getSupportFragmentManager().beginTransaction();

        // Pass message to Fragment using Bundle
        if (!message.equals("")) {
            Bundle bundle = new Bundle();
            bundle.putString(mParent.getString(R.string.intent_message), message);
            fragment.setArguments(bundle);
        }

        transaction.replace(mResIdFragmentContainer, fragment, fragmentTitle);

        if (addToBackStack) {
            transaction.addToBackStack(fragmentTitle);
        }
        transaction.commit();
    }



}
